package org.example.conversion.xml;

import javax.xml.bind.annotation.XmlElement;

import org.example.conversion.Car;

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
		return null;
	}

	public static Car toImpl() {
		return null;
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
