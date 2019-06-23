package org.example.conversion.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.example.conversion.Car;

public class CarImpl implements Car {

	private Date date;
	private String brand;
	private int price;

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	public void setDate(String value) throws ParseException {
		this.date = new SimpleDateFormat("dd.MM.yyyy").parse(value);
	}

	@Override
	public String getBrandName() {
		return brand;
	}

	@Override
	public void setBrandName(String name) {
		this.brand = name;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void setPrice(int price) {
		this.price = price;
	}

}
