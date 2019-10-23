package com.pinamar.api.negocio;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class EmpleadoFijo extends Empleado {
	
	private double sueldoBase;
	private int diasAusentes; //resta valor dia que es sueldobase / 30 y eso por la cantidad dias
	private int diasEnfermedad; //no resta y se calcula por dia como arriba
	private int diasVacaciones; //sueldo base dividido 25 por cantidad de dias vacaciones
	private int horasExtra; //sueldo sobre 160 horas por hora extra por 50% de la hora
	private int feriados; //sueldo base / 30 * 2 * dia
	private int diasTrabajados; //ademas de para hacer la cuenta, se quiere guardar
	
	public EmpleadoFijo(ObjectId _id, int dni, String nombre, String direccion, String puesto, Date fechaIngreso, String tipoLiquidacion, double sueldoBase, int diasAusentes, int diasEnfermedad, int diasVacaciones, int horasExtra, int feriados, int diasTrabajados, List<Concepto> conceptos, String cbu) {
		super(_id, dni, nombre, direccion, puesto, fechaIngreso, tipoLiquidacion, conceptos, cbu);
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
		int diasTotales;
		int horasTotales;
		if(this.getTipoLiquidacion().equalsIgnoreCase("MENSUAL")) {
			diasTotales = 30;
			horasTotales = 160;
			diasTrabajados = diasTotales - feriados - diasVacaciones - diasEnfermedad - diasAusentes;
			double montoDiasNormales = (sueldoBase/diasTotales) * diasTrabajados;
			double montoFeriados = (sueldoBase/diasTotales) * feriados * 2;
			double montoVacaciones = (sueldoBase/25) * diasVacaciones;
			double montoEnfermedad = (sueldoBase/diasTotales) * diasEnfermedad;
			double montoAusentes = (sueldoBase/diasTotales) * diasAusentes;
			double montoHorasExtra = (sueldoBase/horasTotales) * 1.5 * horasExtra;
			double montoBonos = 0;
			for (Concepto c : this.getConceptos())
				if(c.getTipo().equalsIgnoreCase("BONIFICACION"))
					montoBonos += (c.getValor()*sueldoBase); //siempre es un porcentaje
			double sueldoBruto = montoDiasNormales + montoFeriados + montoVacaciones + montoEnfermedad - montoAusentes + montoHorasExtra + montoBonos;
			return sueldoBruto;
		}
		else if (this.getTipoLiquidacion().equalsIgnoreCase("QUINCENAL")) {
			diasTotales = 15;
			horasTotales = 80;
			diasTrabajados = diasTotales - feriados - diasVacaciones - diasEnfermedad - diasAusentes;
		}
		else if (this.getTipoLiquidacion().equalsIgnoreCase("SEMANAL")) {
			diasTotales = 7;
			horasTotales = 40;
			diasTrabajados = diasTotales - feriados - diasVacaciones - diasEnfermedad - diasAusentes;
		}
		else { //queda solo la opcion diaria
			diasTotales = 1;
			horasTotales = 8;
		}
		return this.sueldoBase;
	}
	
	
	/*
		 Recibo liquidarSueldo(){
		Recibo r = new Recibo()
		base = this.calcularSueldoBase()	// Hecho en el codigo
		// CALCULO EN HOJA. VER SI SE PERSISTEN COMO CONCEPTOS O SE GUARDAN LOCAL
		Para cada concepto, agregarlo a la lista de conceptos del recibo
		Al obtener el bruto => r.setBruto
		Al obtener el neto => r.setNeto
		this.recibos.addRecibo(r)
		return r
}
	 */
	
}