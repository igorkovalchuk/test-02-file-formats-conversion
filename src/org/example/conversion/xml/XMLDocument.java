package org.example.conversion.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.example.conversion.Document;

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

	public void setCars(List<XMLCar> values) {
		this.cars = values;
	}

	public static XMLDocument fromImpl(Document d) {
		return null;
	}

	public Document toImpl() {
		//DocumentImpl d = new DocumentImpl();
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for(XMLCar c : cars) {
			sb.append("  ").append(c).append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

}
