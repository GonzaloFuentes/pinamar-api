package com.pinamar.api.negocio;

public class Transferencia {

	private String cbuOrigen;
	private float amount;
	
	public Transferencia(String cbuOrigen, float amount) {
		super();
		this.cbuOrigen = cbuOrigen;
		this.amount = amount;
	}

	public String getCbuOrigen() {
		return cbuOrigen;
	}
	public void setCbuOrigen(String cbuOrigen) {
		this.cbuOrigen = cbuOrigen;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
}
