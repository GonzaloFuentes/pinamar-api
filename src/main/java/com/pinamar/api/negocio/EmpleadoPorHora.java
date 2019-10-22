package com.pinamar.api.negocio;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class EmpleadoPorHora extends Empleado{
	
	private double valorHora;
	private int horasTrabajadas;

	public EmpleadoPorHora(ObjectId _id, int dni, String nombre, String direccion, String puesto, Date fechaIngreso, String tipoLiquidacion, double valorHora, int horasTrabajadas, List<Concepto> conceptos, String cbu) {
		super(_id, dni, nombre, direccion, puesto, fechaIngreso, tipoLiquidacion, conceptos, cbu);
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
	
	public double calcularSueldoBruto () {
		return this.getValorHora() * this.getHorasTrabajadas();
	}
	
}