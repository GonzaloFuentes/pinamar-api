package com.pinamar.api.negocio;

import java.io.Serializable;

public class Concepto implements Serializable{

	private static final long serialVersionUID = -3856943891380286353L;

	private String nombre;
	private double valor;
	
	public Concepto(String nombre, double valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
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
}