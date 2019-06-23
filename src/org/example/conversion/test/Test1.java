package org.example.conversion.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
	void testXMLbaseClasses() {
		XMLCar c1 = new XMLCar();
		c1.setDate("10.10.2008");
		c1.setBrandName("Alpha Romeo Brera");
		c1.setPrice(37000);

		XMLCar c2 = new XMLCar();
		c2.setDate("01.06.2000");
		c2.setBrandName("ZAZ");
		c2.setPrice(1500);

		List<XMLCar> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);

		XMLDocument d1 = new XMLDocument();
		d1.setCars(list);

		String xml = XMLDocumentProcessor.objectToXmlString(d1);

		XMLDocument d2 = XMLDocumentProcessor.xmlStringToObject(xml);
		assertEquals(2, d2.getCarsSize());
		XMLCar c1b = d2.get(0);
		XMLCar c2b = d2.get(1);

		assertEquals(c1b.getDate(), "10.10.2008");
		assertEquals(c1b.getBrandName(), "Alpha Romeo Brera");
		assertEquals(c1b.getPrice(), 37000);

		assertEquals(c2b.getDate(), "01.06.2000");
		assertEquals(c2b.getBrandName(), "ZAZ");
		assertEquals(c2b.getPrice(), 1500);
	}

}
