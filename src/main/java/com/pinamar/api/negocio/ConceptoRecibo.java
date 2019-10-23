package com.pinamar.api.negocio;

public class ConceptoRecibo {

	//dentro de recibo, misma coleccion
	private String nombre;
	private double valor;
	private double monto;
	
	public ConceptoRecibo(String nombre, double valor, double monto) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.monto = monto;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
}