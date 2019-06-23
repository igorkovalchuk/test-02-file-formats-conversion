package org.example.conversion.xml;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.example.conversion.Car;
import org.example.conversion.Document;
import org.example.conversion.impl.DocumentImpl;

@XmlRootElement(name="Document")
public class XMLDocument {

	@XmlElement(name="Car")
	private List<XMLCar> cars = new ArrayList<>();

	public int getCarsSize() {
		return cars.size();
	}

	public XMLCar get(int index) {
		return cars.get(index);
	}

	public void add(XMLCar c) {
		this.cars.add(c);
	}

	public static XMLDocument fromImpl(Document d) {
		XMLDocument xd = new XMLDocument();
		for(Car c : d) {
			XMLCar xc = XMLCar.fromImpl(c);
			xd.add(xc);
		}
		return xd;
	}

	public Document toImpl() throws ParseException {
		DocumentImpl d = new DocumentImpl();
		for(XMLCar xc : this.cars) {
			Car c = xc.toImpl();
			d.add(c);
		}
		return d;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("XMLDocument:\n");
		for(XMLCar c : cars) {
			sb.append("  ").append(c).append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

}
