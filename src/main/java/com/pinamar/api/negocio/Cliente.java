package com.pinamar.api.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "clientes")
@JsonPropertyOrder({"_id", "cuit", "cbu", "nombre", "fisico", "empleados_id", "password", "liquidaciones", "diaMesLiquidacionMensual", "diaPrimerQuincena", "diaSegundaQuincena", "diaSemana"})
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 6458521016596172276L;
	@Id
	private ObjectId _id;
	private int cuit;
	private String cbu;
	private String nombre;
	private boolean fisico;
	private List<ObjectId> empleados_id;
	private String password;
	private List<ObjectId> liquidaciones;
	private int diaMesLiquidacionMensual;
	private int diaPrimerQuincena;
	private int diaSegundaQuincena;
	private String diaSemana;
	
	public Cliente(ObjectId _id, int cuit, String cbu, String nombre, boolean fisico, String password,
			int diaMesLiquidacionMensual, int diaPrimerQuincena, int diaSegundaQuincena, String diaSemana) {
		super();
		this._id = _id;
		this.cuit = cuit;
		this.cbu = cbu;
		this.nombre = nombre;
		this.fisico = fisico;
		this.password = password;
		this.diaMesLiquidacionMensual = diaMesLiquidacionMensual;
		this.diaPrimerQuincena = diaPrimerQuincena;
		this.diaSegundaQuincena = diaSegundaQuincena;
		this.diaSemana = diaSemana;
		this.empleados_id = new ArrayList<ObjectId>();
		this.liquidaciones = new ArrayList<ObjectId>();
	}
	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	public int getCuit() {
		return cuit;
	}
	public void setCuit(int cuit) {
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
	public void addLiquidacion(ObjectId _id) {
		this.liquidaciones.add(_id);
	}
	public void removeLiquidacion(ObjectId _id) {
		this.liquidaciones.remove(_id);
	}
	public int getDiaMesLiquidacionMensual() {
		return diaMesLiquidacionMensual;
	}
	public void setDiaMesLiquidacionMensual(int diaMesLiquidacionMensual) {
		this.diaMesLiquidacionMensual = diaMesLiquidacionMensual;
	}
	public int getDiaPrimerQuincena() {
		return diaPrimerQuincena;
	}
	public void setDiaPrimerQuincena(int diaPrimerQuincena) {
		this.diaPrimerQuincena = diaPrimerQuincena;
	}
	public int getDiaSegundaQuincena() {
		return diaSegundaQuincena;
	}
	public void setDiaSegundaQuincena(int diaSegundaQuincena) {
		this.diaSegundaQuincena = diaSegundaQuincena;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	
}