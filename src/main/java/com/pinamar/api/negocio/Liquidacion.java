package com.pinamar.api.negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "liquidaciones")
@JsonPropertyOrder({"_id", "recibos", "tipo", "fecha", "total"})
public class Liquidacion implements Serializable{
	
	private static final long serialVersionUID = -5011038249169885993L;
	
	@Id
	private ObjectId _id;
	private List<ObjectId> recibos;
	private String tipo;
	private Date fecha;
	private double total;
	
	public Liquidacion(ObjectId _id, List<ObjectId> recibos, String tipo, Date fecha, double total) {
		super();
		this._id = _id;
		this.recibos = recibos;
		this.tipo = tipo;
		this.fecha = fecha;
		this.total = total;
	}

	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	public List<ObjectId> getRecibos() {
		return recibos;
	}
	public void setRecibos(List<ObjectId> recibos) {
		this.recibos = recibos;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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