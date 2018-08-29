package org.ellie.carscatalog.abstracts;

public abstract class AbstractCar {

	private String producer;
	private short year;
	private String color;
	
	public AbstractCar() {}
	
	public AbstractCar(String producer, short year, String color) {
		super();
		this.producer = producer;
		this.year = year;
		this.color = color;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public short getYear() {
		return year;
	}
	public void setYear(short year) {
		this.year = year;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return String.format("producer: %s; year: %d; color: %s"
				, producer, year, color);
	}
}
