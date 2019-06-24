package org.example.conversion.binary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.example.conversion.Car;
import org.example.conversion.ConversionAPI;
import org.example.conversion.Document;
import org.example.conversion.DocumentProcessor;
import org.example.conversion.FileUtils;

public class BinaryDocumentProcessor implements DocumentProcessor {

	@Override
	public String getSupportedFileFormat() {
		return ConversionAPI.FORMAT_BINARY;
	}

	@Override
	public boolean isSupportedFileFormat(String fileName) {
		// Normally we should read the content and try to detect automatically;
		if (fileName != null && fileName.endsWith(".bin"))
			return true;

		return false;
	}

	@Override
	public Document load(String fileName) throws FileNotFoundException, IOException {

		// TODO: P.S. it may be much easier to use the DataInputStream + ByteArrayInputSteam to read the data.

		File f = new File(fileName);
		try {

			ByteBuffer bb = FileUtils.loadBinaryFile(f).order(ByteOrder.LITTLE_ENDIAN);
			return load(bb);

		} catch (BufferUnderflowException ex) {
			throw new IOException(ex);
		} catch (ParseException ex) {
			throw new IOException(ex);
		}
	}

	// for the testing purposes;
	public Document load(ByteBuffer bb) throws IOException, ParseException {
		// Header, 2 bytes, 0x2526
		byte b1 = bb.get();
		byte b2 = bb.get();
		if (b1 != 0x26 || b2 != 0x25)
			throw new IOException("Header must be equal to 0x2526");

		// Records count, 4 bytes, Integer specifying number of records
		IntBuffer ib = bb.asIntBuffer();
		int count = ib.get();

		bb.position(2 + 4);

		Document d = Document.newInstance();
		for (int i = 0; i < count; i++) {
			Car c = loadCar(bb);
			d.add(c);
		}
		return d;
	}

	private Car loadCar(ByteBuffer bb) throws ParseException {
		Car c = Car.newInstance();

		// Date, 8 bytes, DDMMYYYY
		// Brand Name length, 2 bytes, Length of the following string
		// Brand Name, 0..<Brand Name length * 2> bytes, Unicode String
		// Price, 4 bytes, Integer

		byte d1 = bb.get();
		byte d2 = bb.get();
		byte m1 = bb.get();
		byte m2 = bb.get();
		byte y1 = bb.get();
		byte y2 = bb.get();
		byte y3 = bb.get();
		byte y4 = bb.get();
		Date d = convert(new int[] { d1, d2, m1, m2, y1, y2, y3, y4 });
		c.setDate(d);

		short signedNameLength = bb.asShortBuffer().get();
		int nameLength = unsigned(signedNameLength);

		bb.position(bb.position() + 2);

		CharBuffer cb = bb.asCharBuffer();
		char[] chars = new char[nameLength];
		cb.get(chars);
		c.setBrandName(new String(chars));

		bb.position(bb.position() + nameLength * 2);

		int price = bb.asIntBuffer().get();
		c.setPrice(price);

		bb.position(bb.position() + 4);

		return c;
	}

	@Override
	public void save(Document d, String fileName) throws FileNotFoundException, IOException {

		// TODO: P.S. it looks much easier to write data directly
		// using the DataOutputStream + ByteArrayOutputSteam, without the ByteBuffer;

		ByteBuffer bb = save(d);

		// while (bb.capacity() > bb.position()) {
		// System.out.println("Result: " + bb.position() + " = " + unsigned(bb.get()));
		// }

		byte[] result = new byte[bb.capacity()];
		bb.rewind();
		bb.get(result);

		File f = new File(fileName);
		FileUtils.writeToBinaryFile(f, result);
	}

	public ByteBuffer save(Document d) throws IOException {
		int size = getSize(d);

		ByteBuffer bb = ByteBuffer.allocate(size).order(ByteOrder.LITTLE_ENDIAN);
		short header = 0x2526;
		bb.asShortBuffer().put(header);

		bb.position(2);
		bb.asIntBuffer().put(d.size());

		bb.position(bb.position() + 4);

		for (Car c : d) {
			// Date, 8 bytes, DDMMYYYY
			// Brand Name length, 2 bytes, Length of the following string
			// Brand Name, 0..<Brand Name length * 2> bytes, Unicode String
			// Price, 4 bytes, Integer
			byte date[] = convert(c.getDate());
			bb.put(date);

			int length = c.getBrandName().length();
			if ((length & 0xffff) != length)
				throw new IOException("Name is too long: " + length);
			bb.asShortBuffer().put((short) length);
			bb.position(bb.position() + 2);

			char[] name = c.getBrandName().toCharArray();
			bb.asCharBuffer().put(name);
			bb.position(bb.position() + length * 2);

			bb.asIntBuffer().put(c.getPrice());
			bb.position(bb.position() + 4);
		}

		bb.rewind();
		return bb;
	}

	private int getSize(Document d) {
		int size = 0;
		size += 2; // Header, 2 bytes, 0x2526
		size += 4; // Records count, 4 bytes, Integer specifying number of records

		for (Car c : d) {
			// Date, 8 bytes, DDMMYYYY
			// Brand Name length, 2 bytes, Length of the following string
			// Brand Name, 0..<Brand Name length * 2> bytes, Unicode String
			// Price, 4 bytes, Integer
			size += 8;
			size += 2;
			size += c.getBrandName().length() * 2;
			size += 4;
		}
		return size;
	}

	// DDMMYYYY
	private static Date convert(int[] date) throws ParseException {
		for (int i = 0; i < date.length; i++) {
			if (date[i] < 0 || date[i] > 9) {
				throw new ParseException("DDMMYYYY", i);
			}
		}

		int dd = date[0] * 10 + date[1];
		int mm = date[2] * 10 + date[3];
		int yyyy = date[4] * 1000 + date[5] * 100 + date[6] * 10 + date[7];

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeZone(TimeZone.getTimeZone("UTC"));
		gc.set(Calendar.YEAR, yyyy);
		gc.set(Calendar.MONTH, mm - 1);
		gc.set(Calendar.DAY_OF_MONTH, dd);
		return gc.getTime();
	}

	// DDMMYYYY
	private static byte[] convert(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeZone(TimeZone.getTimeZone("UTC"));
		gc.setTime(date);

		int yyyy = gc.get(Calendar.YEAR);
		int mm = gc.get(Calendar.MONTH) + 1;
		int dd = gc.get(Calendar.DAY_OF_MONTH);

		byte[] b1 = twoDigitsToTwoBytes(dd);
		byte[] b2 = twoDigitsToTwoBytes(mm);
		byte[] b3 = fourDigitsToFourBytes(yyyy);

		byte b[] = new byte[] { b1[0], b1[1], b2[0], b2[1], b3[0], b3[1], b3[2], b3[3] };
		return b;
	}

	private static byte[] fourDigitsToFourBytes(int value) {
		int value1000 = (value % 10000) / 1000;
		int value100 = (value % 1000) / 100;
		int value10 = (value % 100) / 10;
		int value1 = value % 10;
		return new byte[] { (byte) value1000, (byte) value100, (byte) value10, (byte) value1 };
	}

	private static byte[] twoDigitsToTwoBytes(int value) {
		int value10 = (value % 100) / 10;
		int value1 = value % 10;
		return new byte[] { (byte) value10, (byte) value1 };
	}

	private static int unsigned(short s) {
		return (int) s & 0xffff;
	}

	//private static int unsigned(byte s) {
		//return (int) s & 0xff;
	//}

}
