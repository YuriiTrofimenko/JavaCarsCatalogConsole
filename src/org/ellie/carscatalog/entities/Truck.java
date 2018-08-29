package org.ellie.carscatalog.entities;

import org.ellie.carscatalog.abstracts.AbstractCar;

public class Truck extends AbstractCar {

	private Float carrying;

	public Truck() {
		super();
	}
	
	public Truck(String producer, short year, String color, float carrying) {
		super(producer, year, color);
		this.carrying = carrying;
	}

	public Float getCarrying() {
		return carrying;
	}
	
	public void setCarrying(Float carrying) {
		this.carrying = carrying;
	}
	
	@Override
	public String toString() {
		return super.toString()
				+ String.format(" ;carrying: %f"
						, carrying);
	}
}
