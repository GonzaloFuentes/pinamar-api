package com.pinamar.api.negocio;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class LiquidacionDTO {

	@Id
	private ObjectId _id;
	private List<Recibo> recibos;
	private String tipo;
	private Date fecha;
	private double total;
	
	public LiquidacionDTO(ObjectId _id, List<Recibo> recibos, String tipo, Date fecha, double total) {
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
	public List<Recibo> getRecibos() {
		return recibos;
	}
	public void setRecibos(List<Recibo> recibos) {
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