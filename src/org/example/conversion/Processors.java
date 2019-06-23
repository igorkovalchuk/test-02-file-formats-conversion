package org.example.conversion;

import java.util.ArrayList;
import java.util.List;

import org.example.conversion.binary.BinaryDocumentProcessor;
import org.example.conversion.xml.XMLDocumentProcessor;

class Processors {

	private static List<DocumentProcessor> processors = new ArrayList<>();

	static void registerProcessor(DocumentProcessor processor) {
		processors.add(processor);
	}

	static {
		registerProcessor(new XMLDocumentProcessor());
		registerProcessor(new BinaryDocumentProcessor());
	}

	static List<DocumentProcessor> getProcessors() {
		return processors;
	}

}
