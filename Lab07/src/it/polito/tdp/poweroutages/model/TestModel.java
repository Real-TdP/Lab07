package it.polito.tdp.poweroutages.model;

import java.util.List;

import it.polito.tdp.poweroutages.bean.Nerc;
import it.polito.tdp.poweroutages.bean.PowerOutage;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		List<Nerc> x=model.getNercList();
		
		for(Nerc n:x) {
			if(n.getId()==3)
				for(PowerOutage p:n.getPowerOutage())
					System.out.println("ECC: "+p.getId());
		}
	}
}
