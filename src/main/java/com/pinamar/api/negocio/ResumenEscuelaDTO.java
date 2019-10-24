package com.pinamar.api.negocio;

import java.util.Date;

public class ResumenEscuelaDTO {

	private Date fecha;
	private float total;
	private boolean facturada;
	
	public ResumenEscuelaDTO(Date fecha, float total, boolean facturada) {
		super();
		this.fecha = fecha;
		this.total = total;
		this.facturada = facturada;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public boolean isFacturada() {
		return facturada;
	}

	public void setFacturada(boolean facturada) {
		this.facturada = facturada;
	}
}