package org.example.conversion;

import java.io.IOException;
import java.util.List;

public class ConversionAPI {

	public static void registerProcessor(DocumentProcessor processor) {
		Processors.registerProcessor(processor);
	}

	public Document loadFile(String fileName) throws IOException {
		List<DocumentProcessor> processors = Processors.getProcessors();
		for(DocumentProcessor p : processors) {
			if (p.isSupportedFileFormat(fileName)) {
				return p.load(fileName);
			}
		}
		throw new IOException("Can't read this file: " + fileName);
	}

	public void save(String fileFormat, Document d, String fileName) throws IOException {
		List<DocumentProcessor> processors = Processors.getProcessors();
		for(DocumentProcessor p : processors) {
			if (p.getSupportedFileFormat().equalsIgnoreCase(fileFormat)) {
				p.save(d, fileName);
				return;
			}
		}
		throw new IOException("Can't save this file format: " + fileFormat);
	}

}
