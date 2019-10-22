package com.pinamar.api.negocio;

import java.util.Date;

public class EmpleadoPorHora extends Empleado{
	
	private double valorHora;
	private int horasTrabajadas;

	public EmpleadoPorHora(int dni, String nombre, String direccion, String puesto, Date fechaIngreso, double valorHora, int horasTrabajadas) {
		super(dni, nombre, direccion, puesto, fechaIngreso);
		this.valorHora = valorHora;
		this.horasTrabajadas = horasTrabajadas;
	}
	
	public double getValorHora() {
		return valorHora;
	}
	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}
	public int getHorasTrabajadas() {
		return horasTrabajadas;
	}
	public void setHorasTrabajadas(int horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}
	
}