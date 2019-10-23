package com.pinamar.api.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "clientes")
@JsonPropertyOrder({"_id", "cuit", "nombre", "fisico", "empleados_id", "password", "liquidaciones"})
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 6458521016596172276L;
	@Id
	private ObjectId _id;
	private String cuit;
	private String nombre;
	private boolean fisico;
	private List<ObjectId> empleados_id;
	private String password;
	private List<ObjectId> liquidaciones;

	public Cliente(ObjectId _id, String cuit, String nombre, boolean fisico, String password) {
		super();
		this._id = _id;
		this.cuit = cuit;
		this.nombre = nombre;
		this.fisico = fisico;
		this.password = password;
		this.empleados_id = new ArrayList<ObjectId>();
		this.liquidaciones = new ArrayList<ObjectId>();
	}
	
	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId _id) {
		this._id = _id;
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
	public boolean isFisico() {
		return fisico;
	}
	public void setFisico(boolean fisico) {
		this.fisico = fisico;
	}
	public List<ObjectId> getEmpleados_id() {
		return empleados_id;
	}
	public void setEmpleados_id(List<ObjectId> empleados_id) {
		this.empleados_id = empleados_id;
	}
	public void addEmpleado(ObjectId _id) {
		this.empleados_id.add(_id);
	}
	public void removeEmpleado(ObjectId _id) {
		this.empleados_id.remove(_id);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<ObjectId> getLiquidaciones() {
		return liquidaciones;
	}
	public void setLiquidaciones(List<ObjectId> liquidaciones) {
		this.liquidaciones = liquidaciones;
	}
	/*
		 int liquidarSueldosMensuales(){
		List<Empleado> lista = this.getEmpleadosMensuales()
		Liquidacion l = new Liquidacion()
		int totale = 0
		Para cada empleado de la lista{
			Recibo rec = empleado.liquidarSueldo()
			l.addRecibo(rec)
			totale ++
		}
		this.liquidaciones.add(l)
		return totale
		} 
	 */
}