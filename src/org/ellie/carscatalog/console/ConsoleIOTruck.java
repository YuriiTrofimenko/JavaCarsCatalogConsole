package org.ellie.carscatalog.console;

import java.util.Enumeration;
import java.util.Scanner;

import org.ellie.carscatalog.abstracts.AbstractCar;
import org.ellie.carscatalog.collections.Vector;
import org.ellie.carscatalog.entities.Truck;

public class ConsoleIOTruck {

	public static void edit(Scanner scanner, AbstractCar truck) {
		
		System.out.println("Input carrying (format: ##,0):");
		Float carrying = scanner.nextFloat();
		((Truck)truck).setCarrying(carrying);
	}
	
	public static void findByCarring(Scanner scanner, Vector<AbstractCar> carsVector) {
		
		try {
			Float carring = scanner.nextFloat();
			Enumeration<AbstractCar> carsByCarring = carsVector.elements();
			
			int idx = 0;
			while (carsByCarring.hasMoreElements()) {
				AbstractCar abstractCar =
						carsByCarring.nextElement();
				if (abstractCar instanceof Truck
						&& ((Truck)abstractCar).getCarrying().equals(carring)) {
					System.out.println("Car " + idx + ": " + abstractCar);
				}
				idx++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
