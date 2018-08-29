package org.ellie.carscatalog.entities;

import org.ellie.carscatalog.abstracts.AbstractCar;

public class Car extends AbstractCar {

	private String bodyType;

	public Car() {
		super();
	}

	public Car(String producer, short year, String color, String bodyType) {
		super(producer, year, color);
		this.bodyType = bodyType;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	
	@Override
	public String toString() {
		return super.toString()
				+ String.format(" ;body type: %s"
						, bodyType);
	}
}
