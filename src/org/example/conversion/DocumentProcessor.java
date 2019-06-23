package org.example.conversion;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DocumentProcessor {

	public final static String FORMAT_XML = "XML";
	public final static String FORMAT_BINARY = "BINARY";  

	String getSupportedFileFormat();

	boolean isSupportedFileFormat(String fileName);

	public Document load(String fileName) throws FileNotFoundException, IOException;

	public void save(Document d, String fileName) throws FileNotFoundException, IOException;

}
