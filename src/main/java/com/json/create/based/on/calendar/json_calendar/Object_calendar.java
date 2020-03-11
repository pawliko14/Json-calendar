package com.json.create.based.on.calendar.json_calendar;

public class Object_calendar {
	
	private String Maszyna;
	private String DataProdukcji;
	private String DataKoniecMontazu;
	
	public Object_calendar(String maszyna, String dataProdukcji, String dataKoniecMontazu) {
		Maszyna = maszyna;
		DataProdukcji = dataProdukcji;
		DataKoniecMontazu = dataKoniecMontazu;
	}
	
	public String getMaszyna() {
		return Maszyna;
	}
	public void setMaszyna(String maszyna) {
		Maszyna = maszyna;
	}
	public String getDataProdukcji() {
		return DataProdukcji;
	}
	public void setDataProdukcji(String dataProdukcji) {
		DataProdukcji = dataProdukcji;
	}
	public String getDataKoniecMontazu() {
		return DataKoniecMontazu;
	}
	public void setDataKoniecMontazu(String dataKoniecMontazu) {
		DataKoniecMontazu = dataKoniecMontazu;
	}
	
	
	@Override
	public String toString() {
		return "Object_calendar [Maszyna=" + Maszyna + ", DataProdukcji=" + DataProdukcji + ", DataKoniecMontazu="
				+ DataKoniecMontazu + "]";
	}

	
	

}
