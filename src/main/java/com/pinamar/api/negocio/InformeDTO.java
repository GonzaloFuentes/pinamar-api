package com.pinamar.api.negocio;

public class InformeDTO {

	private String cbuOrigen;
	private String cbuDestino;
	private double monto;
	
	public InformeDTO(String cbuOrigen, String cbuDestino, double monto) {
		super();
		this.cbuOrigen = cbuOrigen;
		this.cbuDestino = cbuDestino;
		this.monto = monto;
	}

	public String getCbuOrigen() {
		return cbuOrigen;
	}
	public void setCbuOrigen(String cbuOrigen) {
		this.cbuOrigen = cbuOrigen;
	}
	public String getCbuDestino() {
		return cbuDestino;
	}
	public void setCbuDestino(String cbuDestino) {
		this.cbuDestino = cbuDestino;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}

}