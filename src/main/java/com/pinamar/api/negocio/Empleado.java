package com.pinamar.api.negocio;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class Empleado {
	
	@Id
	private ObjectId _id;
	private int dni;
	private String nombre;
	private String direccion;
	private String puesto;
	private Date fechaIngreso;
	
	public Empleado(int dni, String nombre, String direccion, String puesto, Date fechaIngreso) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.puesto = puesto;
		this.fechaIngreso = fechaIngreso;
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
	
}