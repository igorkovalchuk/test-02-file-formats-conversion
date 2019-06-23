package org.example.conversion;

import java.util.Date;
import java.util.List;

public interface Document extends List<Car> {

	void addCar(Date date, String brandName, int price);

}
