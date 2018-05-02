package it.polito.tdp.poweroutages.bean;
import java.util.HashMap;
import java.util.Map;

public class IdMapNerc {

	private Map<Integer, Nerc> map;
	
	public IdMapNerc() {
		map = new HashMap<>();
	}
	
	public Nerc get(int id) {
		return map.get(id);
	}
	
	public Nerc get(Nerc nerc) {
		Nerc old = map.get(nerc.getId());
		if (old == null) {
			// nella mappa non c'è questo corso!
			map.put(nerc.getId(), nerc);
			return nerc;
		}
		return old;
	}
	
	public void put(int id, Nerc nerc) {
		map.put(id, nerc);
	}
}