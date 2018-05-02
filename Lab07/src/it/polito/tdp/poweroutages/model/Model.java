package it.polito.tdp.poweroutages.model;



import java.util.List;
import it.polito.tdp.poweroutages.bean.IdMapNerc;
import it.polito.tdp.poweroutages.bean.IdMapPowerOutage;
import it.polito.tdp.poweroutages.bean.Nerc;
import it.polito.tdp.poweroutages.bean.PowerOutage;
import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	IdMapPowerOutage pOmap;
	IdMapNerc nERCmap;
	List<PowerOutage> powerOutage;
	
	
	public Model() {
		podao = new PowerOutageDAO();
		pOmap = new IdMapPowerOutage();
		nERCmap = new IdMapNerc();
		
		powerOutage = podao.getPowerOutages(pOmap,nERCmap);
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList(nERCmap);
	}

	public String getWorstCase(Nerc nerc, int hours, int years) {
		
		
		
		return "fine";
	}
	
	

}
