package it.polito.tdp.poweroutages.bean;
import java.util.HashMap;
import java.util.Map;

public class IdMapPowerOutage {

	private Map<Integer, PowerOutage> map;
	
	public IdMapPowerOutage() {
		map = new HashMap<>();
	}
	
	public PowerOutage get(int id) {
		return map.get(id);
	}
	
	public PowerOutage get(PowerOutage poweroutage) {
		PowerOutage old = map.get(poweroutage.getId());
		if (old == null) {
			// nella mappa non c'è questo corso!
			map.put(poweroutage.getId(), poweroutage);
			return poweroutage;
		}
		return old;
	}
	
	public void put(int id, PowerOutage poweroutage) {
		map.put(id, poweroutage);
	}
}