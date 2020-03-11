package com.json.create.based.on.calendar.json_calendar;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class main {
	
	static List<Object_calendar> Object_list;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws SQLException {

		Connection connection = RCPdatabaseConnection.dbConnector("tosia", "1234");
		Object_list = new ArrayList<Object_calendar>();
		
		getDataFromCalendar(connection);
		
		for(Object_calendar o : Object_list)
		{
			System.out.println(o.toString());
		}
		
		JSONArray MachineList = new JSONArray();
		for(Object_calendar o : Object_list)
		{
			JSONObject MachineDetail = new JSONObject();
			MachineDetail.put("ProductionDate", o.getDataProdukcji());
			MachineDetail.put("Machine", o.getMaszyna());
			MachineDetail.put("MontageDate", o.getDataKoniecMontazu());
			JSONObject MachineObject = new JSONObject(); 
			MachineObject.put("Machine", MachineDetail);
			
			MachineList.add(MachineDetail);
		}


         
        //Write JSON file
        try (FileWriter file = new FileWriter(Parameters.getPathToSaveJSON() + "\\" + Parameters.getJsonName())) {
 
            file.write(MachineList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private static void getDataFromCalendar(Connection connection) throws SQLException 
	{
		

			
			/* query ktore wybiera wszystkich ludzi ktorzy wchodzili od ostatnich 40 - rzeczywisci pracownicy */
			
			String query = "select concat(NrMaszyny, ' - ', Opis, ' - ', Typ) as Maszyna,\r\n" + 
					"DataProdukcji, DataKoniecMontazu\r\n" + 
					"from calendar\r\n" + 
					"where Wyslano = 0\r\n" + 
					"and Zakonczone = 0\r\n" + 
					"and NrMaszyny not like '0/%'\r\n" + 
					"and nrmaszyny like '2/%'\r\n" + 
					"and length(NrMaszyny) = '8'\r\n" + 
					"and nrmaszyny not like '%170520%'\r\n" + 
					"order by DataProdukcji";
			
			
			
			//String query="SELECT cards_name_surname_nrhacosoft.nazwisko_imie, access.id_karty, access.akcja, access.data FROM access LEFT JOIN cards_name_surname_nrhacosoft ON access.id_karty=cards_name_surname_nrhacosoft.id_karty WHERE access.data BETWEEN '"+dataODString+"' AND '"+dataDOString+"' AND access.akcja<>'nowy_pracownik'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{							
				String Maszyna = rs.getString(1);
				String DataProdukcji = rs.getString(2);
				String DataKoniecMontazu = rs.getString(3);
				
				Object_calendar object = new Object_calendar(Maszyna,DataProdukcji,DataKoniecMontazu);
				
				Object_list.add(object);
				
			}
			  
		
	

	}
}


