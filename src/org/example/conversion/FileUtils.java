package org.example.conversion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;

public class FileUtils {

	public static void writeToTextFile(File file, String data) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"))) {
			writer.write(data);
		}
	}

	public static String loadTextFile(File file) throws IOException {
		StringBuilder sb = new StringBuilder();
		try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"))) {
			String line = in.readLine();
			while (line != null) {
				sb.append(line);
				line = in.readLine();
			}
		}
		return sb.toString();
	}

	public static void writeToBinaryFile(File file, byte[] bytes) throws IOException {
		try(DataOutputStream os = new DataOutputStream(new FileOutputStream(file))) {
			os.write(bytes);
		}
	}

	public static ByteBuffer loadBinaryFile(File file) throws IOException {
		try(InputStream is = new FileInputStream(file)) {
			long length = file.length();
			if (length > Integer.MAX_VALUE)
				throw new IOException("File size exceeds " + Integer.MAX_VALUE);
			byte[] bytes = new byte[(int)length];
			is.read(bytes);
			return ByteBuffer.wrap(bytes);
		}
	}

}
