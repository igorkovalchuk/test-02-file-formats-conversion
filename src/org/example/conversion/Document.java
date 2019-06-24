package org.example.conversion;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.example.conversion.impl.DocumentImpl;

public interface Document extends List<Car> {

	void addCar(Date date, String brandName, int price) throws ParseException;

	static Document newInstance() {
		return new DocumentImpl();
	}

}
