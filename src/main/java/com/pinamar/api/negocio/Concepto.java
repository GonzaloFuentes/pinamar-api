package com.pinamar.api.negocio;

public class Concepto{

	private String nombre;
	private double valor;
	private String tipo;
	
	public Concepto(String nombre, double valor, String tipo) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getValor() {
		return valor/100;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}