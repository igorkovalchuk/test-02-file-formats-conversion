package org.example;

import java.io.IOException;
import java.util.Date;

import org.example.conversion.ConversionAPI;
import org.example.conversion.Document;

public class Main {

	public static void main(String[] args) throws IOException {
		Document d = ConversionAPI.loadFile("fixtures/test.xml");

		d.addCar(new Date(), "КАВЗ 3278 4×4 1992", 3800);
		d.addCar(new Date(), "Газ-21", 5500);
		System.out.println(d.toString());

		d.remove(2);
		d.remove(1);
		System.out.println(d.toString());

		ConversionAPI.save(ConversionAPI.FORMAT_XML, d, "fixtures/out.xml");
	}

}
