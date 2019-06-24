package org.example.conversion.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.example.conversion.Car;

public class CarImpl implements Car {

	private Date date;
	private String brand;
	private int price;

	@Override
	public Date getDate() {
		// TODO: properly support null values in this library;
		if (date == null)
			return new Date(0);
		return date;
	}

	@Override
	public void setDate(Date date) {
		// TODO: properly support null values in this library;
		if (date == null)
			date = new Date(0);
		this.date = date;
	}

	@Override
	public void setDate(String value) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		this.date = df.parse(value);
	}

	@Override
	public String getBrandName() {
		return brand;
	}

	@Override
	public void setBrandName(String name) throws ParseException {
		if (name == null || name.length() == 0)
			throw new ParseException("Brand Name", 0);
		this.brand = name;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void setPrice(int price) throws ParseException {
		if (price <= 0)
			throw new ParseException("Price", 0);
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Car={")
			.append("Date=").append(date)
			.append(", BrandName=").append(brand)
			.append(", Price=").append(price)
			.append("}");
		return sb.toString();
	}

}
