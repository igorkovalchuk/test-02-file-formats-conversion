package org.example.conversion.binary;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.example.conversion.Document;
import org.example.conversion.DocumentProcessor;

public class BinaryDocumentProcessor implements DocumentProcessor {

	@Override
	public String getSupportedFileFormat() {
		return FORMAT_BINARY;
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
		throw new RuntimeException();
	}

	@Override
	public void save(Document d, String fileName) throws FileNotFoundException, IOException {
		throw new RuntimeException();
	}

}
