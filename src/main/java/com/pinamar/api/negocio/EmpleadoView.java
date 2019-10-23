package com.pinamar.api.negocio;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "empleados")
@JsonPropertyOrder({"_id", "dni", "nombre", "direccion", "puesto", "fechaIngreso", "tipo", "tipoLiquidacion", "valorHora", "horasTrabajadas", "sueldoBase", "horasExtras", "diasAusentes", "diasEnfermedad", "diasVacaciones", "feriados", "diasTrabajados", "conceptos", "cbu", "recibos"})
public class EmpleadoView {

	@Id
	private ObjectId _id;
	private int dni;
	private String nombre;
	private String direccion;
	private String puesto;
	private Date fechaIngreso;
	private String tipo;
	private String tipoLiquidacion;
	private double valorHora;
	private int horasTrabajadas;
	private double sueldoBase;
	private int horasExtras;
	private int diasAusentes;
	private int diasEnfermedad;
	private int diasVacaciones;
	private int feriados;
	private int diasTrabajados;
	private List<Concepto> conceptos;
	private String cbu;
	private List<ObjectId> recibos;
	
	public EmpleadoView(ObjectId _id, int dni, String nombre, String direccion, String puesto,
			Date fechaIngreso, String tipo, String tipoLiquidacion, double valorHora, int horasTrabajadas, double sueldoBase, int horasExtras, int diasAusentes, int diasEnfermedad, int diasVacaciones, int feriados, int diasTrabajados, List<Concepto> conceptos, String cbu, List<ObjectId> recibos) {
		super();
		this._id = _id;
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.puesto = puesto;
		this.fechaIngreso = fechaIngreso;
		this.tipo = tipo;
		this.tipoLiquidacion = tipoLiquidacion;
		this.valorHora = valorHora;
		this.horasTrabajadas = horasTrabajadas;
		this.sueldoBase = sueldoBase;
		this.horasExtras = horasExtras;
		this.diasAusentes = diasAusentes;
		this.diasEnfermedad = diasEnfermedad;
		this.diasVacaciones = diasVacaciones;
		this.feriados = feriados;
		this.diasTrabajados = diasTrabajados;
		this.conceptos = conceptos;
		this.cbu = cbu;
		this.recibos = recibos;
	}

	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public String getTipoLiquidacion() {
		return tipoLiquidacion;
	}
	public void setTipoLiquidacion(String tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
	}
	public double getSueldoBase() {
		return sueldoBase;
	}
	public void setSueldoBase(double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}
	public int getHorasExtras() {
		return horasExtras;
	}
	public void setHorasExtras(int horasExtras) {
		this.horasExtras = horasExtras;
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
	public List<Concepto> getConceptos() {
		return conceptos;
	}
	public void setConceptos(List<Concepto> conceptos) {
		this.conceptos = conceptos;
	}
	public void addConcepto(Concepto c) {
		this.conceptos.add(c);
	}
	public void removeConcepto(Concepto c) {
		this.conceptos.remove(c);
	}
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	public List<ObjectId> getRecibos() {
		return recibos;
	}
	public void setRecibos(List<ObjectId> recibos) {
		this.recibos = recibos;
	}
	public void addRecibo(ObjectId id) {
		this.recibos.add(id);
	}
	public void removeRecibo(ObjectId id) {
		this.recibos.remove(id);
	}
}