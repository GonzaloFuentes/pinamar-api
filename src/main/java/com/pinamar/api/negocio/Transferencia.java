package com.pinamar.api.negocio;

public class Transferencia {

	private String origenCBU;
	private float amount;
	
	public Transferencia(String origenCBU, float amount) {
		super();
		this.origenCBU = origenCBU;
		this.amount = amount;
	}

	public String getOrigenCBU() {
		return origenCBU;
	}

	public void setOrigenCBU(String origenCBU) {
		this.origenCBU = origenCBU;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
	
}
