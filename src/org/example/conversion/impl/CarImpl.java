package org.example.conversion.impl;

import org.example.conversion.Car;

public class CarImpl implements Car {

	private String date;
	private String brand;
	private int price;

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public void setDate(String date) {
		this.date = date;
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
