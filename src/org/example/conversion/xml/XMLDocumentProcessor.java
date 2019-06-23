package org.example.conversion.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.example.conversion.ConversionAPI;
import org.example.conversion.Document;
import org.example.conversion.DocumentProcessor;
import org.example.conversion.FileUtils;

public class XMLDocumentProcessor implements DocumentProcessor {

	@Override
	public String getSupportedFileFormat() {
		return ConversionAPI.FORMAT_XML;
	}

	@Override
	public boolean isSupportedFileFormat(String fileName) {
		if (fileName != null && fileName.endsWith(".xml"))
			return true;

		return false;
	}

	@Override
	public Document load(String fileName) throws FileNotFoundException, IOException {
		File f = new File(fileName);
		String text = FileUtils.loadTextFile(f);
		XMLDocument xd = xmlStringToObject(text);
		try {
			return xd.toImpl();
		}
		catch (ParseException ex) {
			throw new IOException(ex);
		}
	}

	@Override
	public void save(Document d, String fileName) throws FileNotFoundException, IOException {
		XMLDocument xd = XMLDocument.fromImpl(d);
		String data = objectToXmlString(xd);
		File f = new File(fileName);
		FileUtils.writeToTextFile(f, data);
	}

	public static XMLDocument xmlStringToObject(String xml) {
		try {
			JAXBContext jc = JAXBContext.newInstance(XMLDocument.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			//System.out.println(xml);
			XMLDocument d = (XMLDocument) unmarshaller.unmarshal(new StringReader(xml));
			return d;
		}
		catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}

	public static String objectToXmlString(XMLDocument d) {
		try {
			JAXBContext jc = JAXBContext.newInstance(XMLDocument.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter out = new StringWriter();
			marshaller.marshal(d, out);
			out.flush();
			return out.toString();
		}
		catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}

}
