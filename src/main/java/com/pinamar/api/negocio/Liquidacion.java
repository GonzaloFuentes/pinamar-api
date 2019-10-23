package com.pinamar.api.negocio;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class Liquidacion {
	
	//colecion aparte
	private List<ObjectId> recibos;
	private String tipo;
	private Date fecha;
	private double total;

}
