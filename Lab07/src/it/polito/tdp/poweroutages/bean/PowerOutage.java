package it.polito.tdp.poweroutages.bean;

import java.time.LocalDateTime;

public class PowerOutage {
	private int id;
	private Nerc Nerc;
	private int customAffect;
	private LocalDateTime dataS;
	private LocalDateTime dataF;
	private int demandLoss;
	
	public PowerOutage(int id, Nerc Nerc, int customAffect, LocalDateTime dataS, LocalDateTime dataF, int demandLoss) {
		this.id = id;
		this.Nerc = Nerc;
		this.customAffect = customAffect;
		this.dataS = dataS;
		this.dataF = dataF;
		this.demandLoss = demandLoss;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nerc getNerc() {
		return Nerc;
	}

	public void setIdNerc(Nerc idNerc) {
		this.Nerc = idNerc;
	}

	public int getCustomAffect() {
		return customAffect;
	}

	public void setCustomAffect(int customAffect) {
		this.customAffect = customAffect;
	}

	public LocalDateTime getDataS() {
		return dataS;
	}

	public void setDataS(LocalDateTime dataS) {
		this.dataS = dataS;
	}

	public LocalDateTime getDataF() {
		return dataF;
	}

	public void setDataF(LocalDateTime dataF) {
		this.dataF = dataF;
	}

	public int getDemandLoss() {
		return demandLoss;
	}

	public void setDemandLoss(int demandLoss) {
		this.demandLoss = demandLoss;
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
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

	
	
	
	
	
	

}
