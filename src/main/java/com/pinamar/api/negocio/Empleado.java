package com.pinamar.api.negocio;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Empleado {
	
	@Id
	private ObjectId _id;
	private String dni;
	private String cuit;
	private String nombre;
	private String direccion;
	private String puesto;
	private Date fechaIngreso;
	private String tipoLiquidacion;
	private List<Concepto> conceptos;
	private String cbu;
	private List<ObjectId> recibos;
	private Date ultimaLiquidacion;

	public Empleado(ObjectId _id, String dni, String cuit, String nombre, String direccion, String puesto, Date fechaIngreso, String tipoLiquidacion, List<Concepto> conceptos, String cbu, List<ObjectId> recibos, Date ultimaLiquidacion) {
		super();
		this._id = _id;
		this.dni = dni;
		this.cuit = cuit;
		this.nombre = nombre;
		this.direccion = direccion;
		this.puesto = puesto;
		this.fechaIngreso = fechaIngreso;
		this.tipoLiquidacion = tipoLiquidacion;
		this.conceptos = conceptos;
		this.cbu = cbu;
		this.recibos = recibos;
		this.ultimaLiquidacion = ultimaLiquidacion;
	}

	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
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
	public String getTipoLiquidacion() {
		return tipoLiquidacion;
	}
	public void setTipoLiquidacion(String tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
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
	public Date getUltimaLiquidacion() {
		return ultimaLiquidacion;
	}
	public void setUltimaLiquidacion(Date ultimaLiquidacion) {
		this.ultimaLiquidacion = ultimaLiquidacion;
	}
	
}