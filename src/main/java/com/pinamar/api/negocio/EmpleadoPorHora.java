package com.pinamar.api.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class EmpleadoPorHora extends Empleado{
	
	private double valorHora;
	private int horasTrabajadas;

	public EmpleadoPorHora(ObjectId _id, int dni, int cuit, String nombre, String direccion, String puesto, Date fechaIngreso, String tipoLiquidacion, 
			double valorHora, int horasTrabajadas, List<Concepto> conceptos, String cbu, List<ObjectId> recibos, Date ultimaLiquidacion) {
		super(_id, dni, cuit, nombre, direccion, puesto, fechaIngreso, tipoLiquidacion, conceptos, cbu, recibos, ultimaLiquidacion);
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
	
	private double calcularSueldoBruto () {
		double sueldoBruto = valorHora * horasTrabajadas;
		double montoBonos = 0;
		for (Concepto c: this.getConceptos()) {
			if(c.getTipo().equalsIgnoreCase("BONIFICACION"))
				montoBonos += (c.getValor()*sueldoBruto); //siempre es un porcentaje
		}
		sueldoBruto += montoBonos;
		return sueldoBruto;
	}

	private double calcularSueldoNeto() {
		double montoDeducciones = 0;
		double sueldoBruto = this.calcularSueldoBruto();
		for (Concepto c : this.getConceptos()) {
			if(c.getTipo().equalsIgnoreCase("DEDUCCION")) {
				montoDeducciones += (sueldoBruto * c.getValor());
			}
		}
		double sueldoNeto = sueldoBruto - montoDeducciones;
		return sueldoNeto;
	}

	public Recibo liquidarSueldo() {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(this.getUltimaLiquidacion());
		cal2.setTime(new Date());
		boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		Recibo r = null;
		if(!sameDay) {
			double sueldoBruto = this.calcularSueldoBruto();
			double sueldoNeto = this.calcularSueldoNeto();
			List<ConceptoRecibo> crs = new ArrayList<ConceptoRecibo>();
			for(Concepto c : this.getConceptos()) {
				ConceptoRecibo cr;
				if(c.getTipo().equalsIgnoreCase("BONIFICACION"))
					cr = new ConceptoRecibo(c.getNombre(), c.getValor(), (valorHora * horasTrabajadas)*c.getValor());
				else
					cr = new ConceptoRecibo(c.getNombre(), c.getValor(), sueldoBruto*(-1)*c.getValor()); //si es deduccion resta
				crs.add(cr);
			}
			r = new Recibo(new ObjectId(), crs, sueldoBruto, sueldoNeto);
			this.addRecibo(new ObjectId(r.getId()));
			this.setUltimaLiquidacion(new Date());
			return r;
		}
		else return r;
	}
}