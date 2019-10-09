package com.pinamar.api.negocio;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "cracks")
@JsonPropertyOrder({"_id", "name", "club", "age"})
public class Player implements Serializable{
	
	private static final long serialVersionUID = 6458521016596172276L;
	@Id
	private ObjectId _id;
	private String name;
	private String club;
	private int age;
	
	public Player(ObjectId _id, String name, String club, int age) {
		super();
		this._id = _id;
		this.name = name;
		this.club = club;
		this.age = age;
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
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Player id: " + _id + ", name: " + name + ", club: " + club + ", age: " + age;
	}
}