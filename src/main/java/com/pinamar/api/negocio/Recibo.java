package com.pinamar.api.negocio;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "recibos")
@JsonPropertyOrder({"_id", "conceptos", "sueldoBruto", "sueldoNeto"})
public class Recibo implements Serializable{
	
	private static final long serialVersionUID = 7508203458041462729L;
	
	@Id
	private ObjectId _id;
	private List<ConceptoRecibo> conceptos;
	private double sueldoBruto;
	private double sueldoNeto;
	
	public Recibo(ObjectId _id, List<ConceptoRecibo> conceptos, double sueldoBruto, double sueldoNeto) {
		super();
		this._id = _id;
		this.conceptos = conceptos;
		this.sueldoBruto = sueldoBruto;
		this.sueldoNeto = sueldoNeto;
	}

	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	public List<ConceptoRecibo> getConceptos() {
		return conceptos;
	}
	public void setConceptos(List<ConceptoRecibo> conceptos) {
		this.conceptos = conceptos;
	}
	public double getSueldoBruto() {
		return sueldoBruto;
	}
	public void setSueldoBruto(double sueldoBruto) {
		this.sueldoBruto = sueldoBruto;
	}
	public double getSueldoNeto() {
		return sueldoNeto;
	}
	public void setSueldoNeto(double sueldoNeto) {
		this.sueldoNeto = sueldoNeto;
	}
}
