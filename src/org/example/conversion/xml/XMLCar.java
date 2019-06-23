package org.example.conversion.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.example.conversion.Car;
import org.example.conversion.impl.CarImpl;

/*
<Car>
  <Date>10.10.2008</Date>
  <BrandName>Alpha Romeo Brera</BrandName>
  <Price>37000</Price>
</Car>
*/
public class XMLCar {

	private String date;

	private String brand;

	private Integer price;

	public String getDate() {
		return date;
	}

	@XmlElement(name = "Date", required = true)
	public void setDate(String value) {
		this.date = value;
	}

	@XmlTransient
	public void setDate(Date value) {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		setDate(df.format(value));
	}

	public String getBrandName() {
		return brand;
	}

	@XmlElement(name = "BrandName", required = true)
	public void setBrandName(String value) {
		this.brand = value;
	}

	public Integer getPrice() {
		return price;
	}

	@XmlElement(name = "Price", required = true)
	public void setPrice(Integer value) {
		this.price = value;
	}

	public static XMLCar fromImpl(Car c) {
		XMLCar xc = new XMLCar();
		xc.setDate(c.getDate());;
		xc.setBrandName(c.getBrandName());
		xc.setPrice(c.getPrice());
		return xc;
	}

	public Car toImpl() throws ParseException {
		CarImpl c = new CarImpl();
		c.setDate(getDate());
		c.setBrandName(getBrandName());
		c.setPrice(getPrice());
		return c;
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
