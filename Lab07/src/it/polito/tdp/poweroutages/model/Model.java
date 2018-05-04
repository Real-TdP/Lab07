package it.polito.tdp.poweroutages.model;



import java.util.ArrayList;
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
	List<PowerOutage> soluzione=new ArrayList<PowerOutage>();
	List<Nerc> nercList;
	
	
	public Model() {
		podao = new PowerOutageDAO();
		pOmap = new IdMapPowerOutage();
		nERCmap = new IdMapNerc();
		nercList=podao.getNercList(nERCmap);
		podao.getPowerOutages(pOmap,nERCmap);
	}
	
	public List<Nerc> getNercList() {
		return nercList;
	}

	public String getWorstCase(Nerc nerc, int hours, int years) {
		soluzione.clear();
		List<PowerOutage> powOut=nerc.getPowerOutage();
		List<PowerOutage> soluzioneP=new ArrayList<PowerOutage>();
		
		for(PowerOutage p:powOut) {
			soluzioneP.add(p);
			this.ricorsiva(1,soluzioneP,powOut,hours,years);
			soluzioneP.remove(p);
		}
		StringBuilder sb= new StringBuilder();
		sb.append("Tot people affected:  "+this.verificaPeople(soluzione)
					+"\nTot hours of outage:  "+this.sumHours(soluzione)+"\n");
		for(PowerOutage p:soluzione)
			sb.append(p.toString()+"\n");
		return sb.toString();
	}



	private void ricorsiva(int step, List<PowerOutage> soluzioneP,List<PowerOutage> powOut,int hours,int years) {
		
		if(!this.checkValid(soluzioneP,hours)||!this.verificaYears(soluzioneP,years)) //CONTROLLO SULLE ORE E SULL'ANNO
			return;
		if(step==powOut.size()) {
			if(this.verificaPeople(soluzioneP)>this.verificaPeople(soluzione))//CONTROLLO SULLA SOLUZIONE OTTIMA
				soluzione = new ArrayList<PowerOutage>(soluzioneP);
			return;
		}
		for(PowerOutage p:powOut)
			if(!soluzioneP.contains(p)) {
				soluzioneP.add(p);
				this.ricorsiva(step+1,soluzioneP,powOut,hours,years);
				soluzioneP.remove(step);
				if(this.verificaPeople(soluzioneP)>this.verificaPeople(soluzione))//CONTROLLO SULLA SOLUZIONE OTTIMA
					soluzione = new ArrayList<PowerOutage>(soluzioneP);
			}

		return;
	}

	private boolean verificaYears(List<PowerOutage> soluzioneP, int years) {
		int earliest=2020;
		int latest=0;
		if(soluzioneP.size()<=1)
			return true;
		for(PowerOutage p:soluzioneP) {
			if(earliest>p.getYear())
				earliest=p.getYear();
			if(latest<p.getYear())
				latest=p.getYear();
		}
			
		if(latest-earliest>years)
			return false;
		return true;
	}

	private boolean checkValid(List<PowerOutage> soluzioneP, int hours) {
		int sum=0;
		for(PowerOutage p:soluzioneP)
			sum+=p.getDifH();
		if(sum>hours)
			return false;
		return true;
	}
	
	private int sumHours(List<PowerOutage> soluzioneP) {
		int sum=0;
		for(PowerOutage p:soluzioneP)
			sum+=p.getDifH();
		return sum;
	}

	private int verificaPeople(List<PowerOutage> soluzioneP) {
		int sum=0;
		for(PowerOutage p:soluzioneP)
			sum+=p.getCustomAffect();
		return sum;
	}


	
	

}
