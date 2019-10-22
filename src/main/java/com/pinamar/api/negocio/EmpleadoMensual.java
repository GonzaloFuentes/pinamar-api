package com.pinamar.api.negocio;

import java.util.Date;

public class EmpleadoMensual extends Empleado {
	
	private double valorMes;
	private int diasAusentes; //sin justificar
	private int diasEnfermedad; //justificados
	private int diasVacaciones;
	private int horasExtra;
	
	public EmpleadoMensual(int dni, String nombre, String direccion, String puesto, Date fechaIngreso, double valorMes, int diasAusentes, int diasEnfermedad, int diasVacaciones, int horasExtra) {
		super(dni, nombre, direccion, puesto, fechaIngreso);
		this.valorMes = valorMes;
		this.diasAusentes = diasAusentes;
		this.diasEnfermedad = diasEnfermedad;
		this.diasVacaciones = diasVacaciones;
		this.horasExtra = horasExtra;
	}

	public double getValorMes() {
		return valorMes;
	}
	public void setValorMes(double valorMes) {
		this.valorMes = valorMes;
	}
	public int getDiasAusentes() {
		return diasAusentes;
	}
	public void setDiasAusentes(int diasAusentes) {
		this.diasAusentes = diasAusentes;
	}
	public int getDiasEnfermedad() {
		return diasEnfermedad;
	}
	public void setDiasEnfermedad(int diasEnfermedad) {
		this.diasEnfermedad = diasEnfermedad;
	}
	public int getDiasVacaciones() {
		return diasVacaciones;
	}
	public void setDiasVacaciones(int diasVacaciones) {
		this.diasVacaciones = diasVacaciones;
	}
	public int getHorasExtra() {
		return horasExtra;
	}
	public void setHorasExtra(int horasExtra) {
		this.horasExtra = horasExtra;
	}
	
}