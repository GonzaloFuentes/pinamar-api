package com.pinamar.api.negocio;

import java.util.Date;

import org.bson.types.ObjectId;

public class EmpleadoFijo extends Empleado {
	
	private double sueldoBase;
	private int diasAusentes; //resta valor dia que es sueldobase / 30 y eso por la cantidad dias
	private int diasEnfermedad; //no resta y se calcula por dia como arriba
	private int diasVacaciones; //sueldo base dividido 25 por cantidad de dias vacaciones
	private int horasExtra; //sueldo sobre 160 horas por hora extra por 50% de la hora
	private int feriados; //sueldo base / 30 * 2 * dia
	private int diasTrabajados; //si es mensual 30-ausentes-enfermedad-vacaciones
	
	public EmpleadoFijo(ObjectId _id, int dni, String nombre, String direccion, String puesto, Date fechaIngreso, String tipoLiquidacion, double sueldoBase, int diasAusentes, int diasEnfermedad, int diasVacaciones, int horasExtra, int feriados, int diasTrabajados) {
		super(_id, dni, nombre, direccion, puesto, fechaIngreso, tipoLiquidacion);
		this.sueldoBase = sueldoBase;
		this.diasAusentes = diasAusentes;
		this.diasEnfermedad = diasEnfermedad;
		this.diasVacaciones = diasVacaciones;
		this.horasExtra = horasExtra;
		this.feriados = feriados;
		this.diasTrabajados = diasTrabajados;
	}

	public double getSueldoBase() {
		return sueldoBase;
	}
	public void setSueldoBase(double sueldoBase) {
		this.sueldoBase = sueldoBase;
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
	public int getFeriados() {
		return feriados;
	}
	public void setFeriados(int feriados) {
		this.feriados = feriados;
	}
	public int getDiasTrabajados() {
		return diasTrabajados;
	}
	public void setDiasTrabajados(int diasTrabajados) {
		this.diasTrabajados = diasTrabajados;
	}

	public double calcularSueldoBruto () {
		return this.sueldoBase;
	}
	
}