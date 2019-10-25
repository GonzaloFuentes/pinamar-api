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

	public String getOrigenCBU() {
		return origenCBU;
	}

	public void setOrigenCBU(String origenCBU) {
		this.origenCBU = origenCBU;
	}

	public String getDestinoCBU() {
		return destinoCBU;
	}

	public void setDestinoCBU(String destinoCBU) {
		this.destinoCBU = destinoCBU;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	

}