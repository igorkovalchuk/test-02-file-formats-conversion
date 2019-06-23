package org.example.conversion;

import java.util.Date;

import org.example.conversion.impl.CarImpl;

public interface Car {

	Date getDate();

	void setDate(Date date);

	String getBrandName();

	void setBrandName(String name);

	int getPrice();

	void setPrice(int price);

	static Car newInstance() {
		return new CarImpl();
	}
}
