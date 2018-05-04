package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.bean.IdMapNerc;
import it.polito.tdp.poweroutages.bean.IdMapPowerOutage;
import it.polito.tdp.poweroutages.bean.Nerc;
import it.polito.tdp.poweroutages.bean.PowerOutage;

public class PowerOutageDAO {

	public List<Nerc> getNercList(IdMapNerc nERCmap) {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(nERCmap.get(n));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public void getPowerOutages(IdMapPowerOutage pOmap,IdMapNerc nERCmap) {
		String sql = "SELECT id,nerc_id,customers_affected,date_event_began,date_event_finished FROM poweroutages";
		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				int id= res.getInt("id");
				Nerc nerc=nERCmap.get(res.getInt("nerc_id"));
				int cust_aff=res.getInt("customers_affected");
				LocalDateTime dataB= res.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime dataF= res.getTimestamp("date_event_finished").toLocalDateTime();
				PowerOutage n = new PowerOutage(id,nerc,cust_aff,dataB,dataF);
				nerc.addPowerOutage(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return;
	}

}
