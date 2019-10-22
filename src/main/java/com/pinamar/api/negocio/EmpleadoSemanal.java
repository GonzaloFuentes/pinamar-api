package com.pinamar.api.negocio;

import java.util.Date;

public class EmpleadoSemanal extends Empleado {

	private double valorSemana;
	private int diasAusentes; //sin justificar
	private int diasEnfermedad; //justificados
	private int diasVacaciones;
	private int horasExtra;
	
	public EmpleadoSemanal(int dni, String nombre, String direccion, String puesto, Date fechaIngreso, double valorSemana, int diasAusentes, int diasEnfermedad, int diasVacaciones, int horasExtra) {
		super(dni, nombre, direccion, puesto, fechaIngreso);
		this.valorSemana = valorSemana;
		this.diasAusentes = diasAusentes;
		this.diasEnfermedad = diasEnfermedad;
		this.diasVacaciones = diasVacaciones;
		this.horasExtra = horasExtra;
	}

	public double getValorSemana() {
		return valorSemana;
	}
	public void setValorSemana(double valorSemana) {
		this.valorSemana = valorSemana;
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