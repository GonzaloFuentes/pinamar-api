package com.pinamar.api.negocio;

import java.util.Date;

public class EmpleadoQuincenal extends Empleado {

	private double valorQuincena;
	private int diasAusentes; //sin justificar
	private int diasEnfermedad; //justificados
	private int diasVacaciones;
	private int horasExtra;
	
	public EmpleadoQuincenal(int dni, String nombre, String direccion, String puesto, Date fechaIngreso, double valorQuincena, int diasAusentes, int diasEnfermedad, int diasVacaciones, int horasExtra) {
		super(dni, nombre, direccion, puesto, fechaIngreso);
		this.valorQuincena = valorQuincena;
		this.diasAusentes = diasAusentes;
		this.diasEnfermedad = diasEnfermedad;
		this.diasVacaciones = diasVacaciones;
		this.horasExtra = horasExtra;
	}

	public double getValorQuincena() {
		return valorQuincena;
	}
	public void setValorQuincena(double valorQuincena) {
		this.valorQuincena = valorQuincena;
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