package org.ellie.carscatalog.console;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.ellie.carscatalog.abstracts.AbstractCar;
import org.ellie.carscatalog.collections.Vector;
import org.ellie.carscatalog.entities.Car;
import org.ellie.carscatalog.entities.Truck;

public class MainConsoleIOCar {
	
	private static Map<Integer, String> actionsMap =
			new HashMap<Integer, String>();
	private static Map<Integer, String> carTypeMap =
			new HashMap<Integer, String>();
	
	static {
		actionsMap.put(1, "Show all");
		actionsMap.put(2, "New");
		actionsMap.put(3, "Delete");
		actionsMap.put(4, "Edit");
		actionsMap.put(5, "Find by producer");
		actionsMap.put(6, "Find by carrying");
		actionsMap.put(7, "Find by body type");
		actionsMap.put(8, "Exit");
		
		carTypeMap.put(1, "truck");
		carTypeMap.put(2, "car");
	}

	public static void startMainLoop() {
		
		Scanner scanner = new Scanner(System.in);
		Vector<AbstractCar> carsVector = new Vector<>();
		
		MAIN_LOOP : while(true) {
			
			System.out.println("Cars catalog options:");
			for (Map.Entry<Integer, String> actionsOption : actionsMap.entrySet()) {
				System.out.printf("%d. %s\n", actionsOption.getKey(), actionsOption.getValue());
			}
			
			Integer actionsOption = scanner.nextInt();
			
			switch (actionsOption) {
			case 1:
				System.out.println(actionsMap.get(1) + ":");
				Enumeration<AbstractCar> cars = carsVector.elements();
				int idx = 0;
				while (cars.hasMoreElements()) {
					AbstractCar abstractCar =
							cars.nextElement();
					System.out.println("Car " + idx + ": " + abstractCar);
					idx++;
				}
				break;
			case 2:
				AbstractCar newCar = null;
				System.out.println(actionsMap.get(2) + ":");
				
				//input type
				System.out.println("Car types options:");
				for (Map.Entry<Integer, String> carTypeOption : carTypeMap.entrySet()) {
					System.out.printf("%d. %s\n", carTypeOption.getKey(), carTypeOption.getValue());
				}
				Integer carTypeOption = scanner.nextInt();
				switch (carTypeOption) {
				case 1:
					newCar = new Truck();
					edit(scanner, newCar);
					ConsoleIOTruck.edit(scanner, newCar);
					break;
				case 2:
					newCar = new Car();
					edit(scanner, newCar);
					ConsoleIOCar.edit(scanner, newCar);
					break;
				default:
					break;
				}
				if (newCar != null) {
					//
					System.out.println("Input an index or -1 to add to the end:");
					Integer index = scanner.nextInt();
					if (index != -1) {
						carsVector.insertAt(newCar, index);
					} else {
						carsVector.append(newCar);
					}
					System.out.println("New car added to the vector");
				}
				break;
			case 3:
				System.out.println(actionsMap.get(3) + ":");
				System.out.println("Input an index:");
				Integer index = scanner.nextInt();
				if (index >= 0) {
					carsVector.removeAt(index);
				}
				System.out.println("Selected car deleted");
				break;
			case 4:
				System.out.println(actionsMap.get(4) + ":");
				System.out.println("Input an index:");
				Integer indexForEdit = scanner.nextInt();
				if (indexForEdit >= 0) {
					AbstractCar carForEdit =
							carsVector.elementAt(indexForEdit);
					edit(scanner, carForEdit);
					if (carForEdit instanceof Truck) {
						ConsoleIOTruck.edit(scanner, carForEdit);
					} else if (carForEdit instanceof Car) {
						ConsoleIOCar.edit(scanner, carForEdit);
					}
				}
				System.out.println("Selected car edited");
				break;
			case 5:
				System.out.println(actionsMap.get(5) + ":");
				String producer = scanner.next();
				Enumeration<AbstractCar> carsByProducer = carsVector.elements();
				int idxByProducer = 0;
				while (carsByProducer.hasMoreElements()) {
					AbstractCar abstractCar =
							carsByProducer.nextElement();
					if (abstractCar.getProducer().equals(producer)) {
						System.out.println("Car " + idxByProducer + ": " + abstractCar);
					}
					idxByProducer++;
				}
				break;
			case 6:
				System.out.println(actionsMap.get(6) + ":");
				ConsoleIOTruck.findByCarring(scanner, carsVector);
				break;
			case 7:
				System.out.println(actionsMap.get(7) + ":");
				ConsoleIOCar.findByBodyType(scanner, carsVector);
				break;
			case 8:
				break MAIN_LOOP;
			default:
				break;
			}
			
			if (!continueRequest(scanner)) {
				break;
			}
		}
	}
	
	private static void edit(Scanner scanner, AbstractCar car) {
		
		System.out.println("Input producer:");
		String producer = scanner.next();
		System.out.println("Input year:");
		Short year = scanner.nextShort();
		System.out.println("Input color:");
		String color = scanner.next();
		
		car.setProducer(producer);
		car.setYear(year);
		car.setColor(color);
	}
	
	private static boolean continueRequest(Scanner scanner) {
		
		System.out.println("Continue? (y/n)");
		String response = scanner.next();
		if (response.equals("n")) {
			return false;
		} else {
			return true;
		}
	}
}
