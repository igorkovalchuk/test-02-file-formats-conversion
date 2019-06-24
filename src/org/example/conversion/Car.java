package org.example.conversion;

import java.text.ParseException;
import java.util.Date;

import org.example.conversion.impl.CarImpl;

public interface Car {

	Date getDate();

	void setDate(Date date);

	void setDate(String value) throws ParseException;

	String getBrandName();

	void setBrandName(String name) throws ParseException;

	int getPrice();

	void setPrice(int price) throws ParseException;

	static Car newInstance() {
		return new CarImpl();
	}
}
