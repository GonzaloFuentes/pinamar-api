package com.pinamar.api.negocio;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "novedades")
@JsonPropertyOrder({"_id", "diasAusentes", "diasEnfermedad", "diasVacaciones", "horasExtra", "feriados", "id_empleado", "horasTrabajadas"})
public class Novedad implements Serializable {

	private static final long serialVersionUID = 2816905778683760567L;
	
	@Id
	private ObjectId _id;
	private int diasAusentes;
	private int diasEnfermedad;
	private int diasVacaciones;
	private int horasExtra;
	private int feriados;
	private ObjectId id_empleado;
	private int horasTrabajadas;
	
	public Novedad(ObjectId _id, int diasAusentes, int diasEnfermedad, int diasVacaciones, int horasExtra, int feriados, ObjectId id_empleado, int horasTrabajadas) {
		super();
		this._id = _id;
		this.diasAusentes = diasAusentes;
		this.diasEnfermedad = diasEnfermedad;
		this.diasVacaciones = diasVacaciones;
		this.horasExtra = horasExtra;
		this.feriados = feriados;
		this.id_empleado = id_empleado;
		this.horasTrabajadas = horasTrabajadas;
	}

	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId _id) {
		this._id = _id;
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
	public String getIdEmpleado() {
		return id_empleado.toHexString();
	}
	public void setIdEmpleado(ObjectId _id) {
		this.id_empleado = _id;
	}
	public int getHorasTrabajadas() {
		return horasTrabajadas;
	}
	public void setHorasTrabajadas(int horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}
	
}