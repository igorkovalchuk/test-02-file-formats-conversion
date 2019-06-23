package org.example.conversion.impl;

import java.util.Date;
import java.util.LinkedList;

import org.example.conversion.Car;
import org.example.conversion.Document;

public class DocumentImpl extends LinkedList<Car> implements Document {

	private static final long serialVersionUID = 1L;

	@Override
	public void addCar(Date date, String brandName, int price) {
		CarImpl c = new CarImpl();
		c.setDate(date);
		c.setBrandName(brandName);
		c.setPrice(price);
		this.add(c);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Document:\n");
		for(Car c : this) {
			sb.append("  ").append(c).append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

}
