package org.example.conversion.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.example.conversion.Car;
import org.example.conversion.Document;
import org.example.conversion.binary.BinaryDocumentProcessor;
import org.example.conversion.xml.XMLCar;
import org.example.conversion.xml.XMLDocument;
import org.example.conversion.xml.XMLDocumentProcessor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test1 {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testXML() {
		XMLCar c1 = new XMLCar();
		c1.setDate("10.10.2008");
		c1.setBrandName("Alpha Romeo Brera");
		c1.setPrice(37000);

		XMLCar c2 = new XMLCar();
		c2.setDate("01.06.2000");
		c2.setBrandName("ZAZ");
		c2.setPrice(1500);

		XMLDocument d1 = new XMLDocument();
		d1.add(c1);
		d1.add(c2);

		String xml = XMLDocumentProcessor.objectToXmlString(d1);

		XMLDocument d2 = XMLDocumentProcessor.xmlStringToObject(xml);
		assertEquals(2, d2.getCarsSize());
		XMLCar c1b = d2.get(0);
		XMLCar c2b = d2.get(1);

		assertEquals("10.10.2008", c1b.getDate());
		assertEquals("Alpha Romeo Brera", c1b.getBrandName());
		assertEquals(37000, c1b.getPrice());

		assertEquals("01.06.2000", c2b.getDate());
		assertEquals("ZAZ", c2b.getBrandName());
		assertEquals(1500, c2b.getPrice());
	}

	@Test
	void testBinary() throws ParseException, IOException {
		Car c1 = Car.newInstance();
		c1.setDate("10.10.2008");
		c1.setBrandName("Alpha Romeo Brera");
		c1.setPrice(37000);

		Car c2 = Car.newInstance();
		c2.setDate("01.06.2000");
		c2.setBrandName("ZAZ");
		c2.setPrice(1500);

		Document d1 = Document.newInstance();
		d1.add(c1);
		d1.add(c2);

		BinaryDocumentProcessor processor = new BinaryDocumentProcessor();
		ByteBuffer bb1 = processor.save(d1);

		Document d2 = processor.load(bb1);

		assertEquals(2, d2.size());
		Car c1b = d2.get(0);
		Car c2b = d2.get(1);

		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));

		assertEquals("10.10.2008", df.format(c1b.getDate()));
		assertEquals("Alpha Romeo Brera", c1b.getBrandName());
		assertEquals(37000, c1b.getPrice());

		assertEquals("01.06.2000", df.format(c2b.getDate()));
		assertEquals("ZAZ", c2b.getBrandName());
		assertEquals(1500, c2b.getPrice());
	}

}
