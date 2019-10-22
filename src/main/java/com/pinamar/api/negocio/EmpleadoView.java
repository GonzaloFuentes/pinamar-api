package com.pinamar.api.negocio;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "empleados")
@JsonPropertyOrder({"_id", "dni", "nombre", "direccion", "puesto", "fechaIngreso", "valorHora", "horasTrabajadas", "valorMes", "valorQuincena", "valorMes", "horasExtras", "diasAusentes", "diasEnfermedad", "diasVacaciones"})
public class EmpleadoView {

	@Id
	private ObjectId _id;
	private int dni;
	private String nombre;
	private String direccion;
	private String puesto;
	private Date fechaIngreso;
	private double valorHora;
	private int horasTrabajadas;
	private double valorMes;
	private double valorQuincena;
	private double valorSemana;
	private int horasExtras;
	private int diasAusentes;
	private int diasEnfermedad;
	private int diasVacaciones;
	
	public EmpleadoView(ObjectId _id, int dni, String nombre, String direccion, String puesto,
			Date fechaIngreso, double valorHora, int horasTrabajadas, double valorMes, double valorQuincena,
			double valorSemana, int horasExtras, int diasAusentes, int diasEnfermedad, int diasVacaciones) {
		super();
		this._id = _id;
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.puesto = puesto;
		this.fechaIngreso = fechaIngreso;
		this.valorHora = valorHora;
		this.horasTrabajadas = horasTrabajadas;
		this.valorMes = valorMes;
		this.valorQuincena = valorQuincena;
		this.valorSemana = valorSemana;
		this.horasExtras = horasExtras;
		this.diasAusentes = diasAusentes;
		this.diasEnfermedad = diasEnfermedad;
		this.diasVacaciones = diasVacaciones;
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
	public double getValorMes() {
		return valorMes;
	}
	public void setValorMes(double valorMes) {
		this.valorMes = valorMes;
	}
	public double getValorQuincena() {
		return valorQuincena;
	}
	public void setValorQuincena(double valorQuincena) {
		this.valorQuincena = valorQuincena;
	}
	public double getValorSemana() {
		return valorSemana;
	}
	public void setValorSemana(double valorSemana) {
		this.valorSemana = valorSemana;
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
	
}