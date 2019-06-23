package org.example.conversion;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DocumentProcessor {

	String getSupportedFileFormat();

	boolean isSupportedFileFormat(String fileName);

	public Document load(String fileName) throws FileNotFoundException, IOException;

	public void save(Document d, String fileName) throws FileNotFoundException, IOException;

}
