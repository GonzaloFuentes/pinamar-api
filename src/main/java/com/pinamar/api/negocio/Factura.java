package com.pinamar.api.negocio;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "facturas")
@JsonPropertyOrder({"_id", "id_cliente", "id_liquidacion", "fecha", "total"})
public class Factura implements Serializable{

	private static final long serialVersionUID = 2717923572881417585L;
	
	@Id
	private ObjectId _id;
	private String id_cliente;
	private String id_liquidacion;
	private Date fecha;
	private double total;
	
	public Factura(ObjectId _id, String id_cliente, String id_liquidacion, Date fecha, double total) {
		super();
		this._id = _id;
		this.id_cliente = id_cliente;
		this.id_liquidacion = id_liquidacion;
		this.fecha = fecha;
		this.total = total;
	}

	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	public String getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getId_liquidacion() {
		return id_liquidacion;
	}
	public void setId_liquidacion(String id_liquidacion) {
		this.id_liquidacion = id_liquidacion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

}