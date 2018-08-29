package org.ellie.carscatalog.console;

import java.util.Enumeration;
import java.util.Scanner;

import org.ellie.carscatalog.abstracts.AbstractCar;
import org.ellie.carscatalog.collections.Vector;
import org.ellie.carscatalog.entities.Car;

public class ConsoleIOCar {

	public static void edit(Scanner scanner, AbstractCar car) {
		
		System.out.println("Input body type:");
		String bodyType = scanner.next();
		((Car)car).setBodyType(bodyType);
	}
	
	public static void findByBodyType(Scanner scanner, Vector<AbstractCar> carsVector) {
		
		String bodyType = scanner.next();
		Enumeration<AbstractCar> carsByBodyType = carsVector.elements();
		
		int idx = 0;
		while (carsByBodyType.hasMoreElements()) {
			AbstractCar abstractCar =
					carsByBodyType.nextElement();
			if (abstractCar instanceof Car
					&& ((Car)abstractCar).getBodyType().equals(bodyType)) {
				System.out.println("Car " + idx + ": " + abstractCar);
			}
			idx++;
		}
	}
}
