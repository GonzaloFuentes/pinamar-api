package com.pinamar.api.negocio;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "club")
@JsonPropertyOrder({"_id", "name", "fundation", "country", "division"})
public class Club implements Serializable {

	private static final long serialVersionUID = 433297102895978515L;
	
	@Id
	private ObjectId _id;
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
	public Club(ObjectId _id, String name, int fundation, String country, String division) {
		super();
		this._id = _id;
		this.name = name;
		this.fundation = fundation;
		this.country = country;
		this.division = division;
	}

	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId _id) {
		this._id = _id;
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
		return "Club id: " + _id + ", name: " + name + ", fundation: " + fundation + ", country: " + country + ", division: " + division;
	}
}