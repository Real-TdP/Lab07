package it.polito.tdp.poweroutages.bean;

import java.util.ArrayList;
import java.util.List;

public class Nerc {

	private int id;
	private String value;
	private List<PowerOutage> powerOutage;

	public Nerc(int id, String value) {
		this.id = id;
		this.value = value;
		powerOutage= new ArrayList<PowerOutage>();
	}

	public List<PowerOutage> getPowerOutage() {
		return powerOutage;
	}

	public void addPowerOutage(PowerOutage powerOutage) {
		this.powerOutage.add(powerOutage);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nerc other = (Nerc) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(value);
		return builder.toString();
	}
}
