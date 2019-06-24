package org.example;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.example.conversion.Car;
import org.example.conversion.ConversionAPI;
import org.example.conversion.Document;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		Document d = ConversionAPI.loadFile("fixtures/test.xml");

		// Modify the document:
		d.addCar(new Date(), "КАВЗ 3278 4×4 1992", 3800);
		d.addCar(new Date(), "Газ-21", 5500);
		System.out.println(d.toString());

		d.remove(2);
		d.remove(1);
		System.out.println(d.toString());

		Car c1 = d.get(0);
		System.out.println("Brand name [0]: " + c1.getBrandName());
		Car c2 = d.get(1);
		System.out.println("Brand name [1]: " + c2.getBrandName());

		ConversionAPI.save(ConversionAPI.FORMAT_XML, d, "fixtures/out.xml");
		ConversionAPI.save(ConversionAPI.FORMAT_BINARY, d, "fixtures/out.bin");

		// Compose a new document:
		Document d2 = Document.newInstance();

		Car c22 = Car.newInstance();
		c22.setBrandName("A new brand car");
		c22.setDate(new Date());
		c22.setPrice(1000);
		System.out.println("A new car: " + c22);

		d2.add(c22);
		ConversionAPI.save(ConversionAPI.FORMAT_XML, d2, "fixtures/out2.xml");
	}

}
