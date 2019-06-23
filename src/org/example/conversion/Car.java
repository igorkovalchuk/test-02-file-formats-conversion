package org.example.conversion;

import org.example.conversion.impl.CarImpl;

public interface Car {

	String getDate();

	void setDate(String date);

	String getBrandName();

	void setBrandName(String name);

	int getPrice();

	void setPrice(int price);

	static Car newInstance() {
		return new CarImpl();
	}
}
