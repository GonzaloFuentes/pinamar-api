package com.pinamar.api.negocio;

public class InformeDTO {

	private String origenCBU;
	private String destinoCBU;
	private double amount;
	
	public InformeDTO(String origenCBU, String destinoCBU, double amount) {
		super();
		this.origenCBU = origenCBU;
		this.destinoCBU = destinoCBU;
		this.amount = amount;
	}

	public String getCbuOrigen() {
		return origenCBU;
	}
	public void setCbuOrigen(String origenCBU) {
		this.origenCBU = origenCBU;
	}
	public String getCbuDestino() {
		return destinoCBU;
	}
	public void setCbuDestino(String destinoCBU) {
		this.destinoCBU = destinoCBU;
	}
	public double getMonto() {
		return amount;
	}
	public void setMonto(double amount) {
		this.amount = amount;
	}

}