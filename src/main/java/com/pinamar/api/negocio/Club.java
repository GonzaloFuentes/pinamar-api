package com.pinamar.api.negocio;

import java.io.Serializable;

public class Club implements Serializable {

	private static final long serialVersionUID = 433297102895978515L;
	
	private String name;
	private int fundation;
	private String country;
	private String division;
	
	/*
	 segun como acceda al club, puedo no necesitar una coleccion de mongo, por lo cual tampoco necesito su id
	 si quiero acceder a un getclub como tal y hacer algo, si necesito esto
	 pero si accedo al club a traves de un jugador, y lo que quiero es un jugador, el club es solo un objeto dentro del jugador y no otra coleccion
	 puede ser que haya clubes varios, pueden definir como fijas tipo enumeracion o crearlos a mano siempre
	*/
	public Club(String name, int fundation, String country, String division) {
		super();
		this.name = name;
		this.fundation = fundation;
		this.country = country;
		this.division = division;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFundation() {
		return fundation;
	}
	public void setFundation(int fundation) {
		this.fundation = fundation;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}

	@Override
	public String toString() {
		return "Club name: " + name + ", fundation: " + fundation + ", country: " + country + ", division: " + division;
	}
}