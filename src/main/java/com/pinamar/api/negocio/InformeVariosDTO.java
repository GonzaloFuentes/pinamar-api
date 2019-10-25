package com.pinamar.api.negocio;

import java.util.List;

public class InformeVariosDTO {
	
	private String cbuDestino;
	private List<Transferencia> tranferenciasUnicas;
	
	public InformeVariosDTO(String cbuDestino, List<Transferencia> tranferenciasUnicas) {
		super();
		this.cbuDestino = cbuDestino;
		this.tranferenciasUnicas = tranferenciasUnicas;
	}

	public String getCbuDestino() {
		return cbuDestino;
	}
	public void setCbuDestino(String cbuDestino) {
		this.cbuDestino = cbuDestino;
	}
	public List<Transferencia> getTranferenciasUnicas() {
		return tranferenciasUnicas;
	}
	public void setTranferenciasUnicas(List<Transferencia> tranferenciasUnicas) {
		this.tranferenciasUnicas = tranferenciasUnicas;
	}

	public Transferencia getTransferenciaByDestino(String destino) {
		Transferencia aux = null;
		for(Transferencia t : this.tranferenciasUnicas) {
			if(t.getCbuOrigen().equalsIgnoreCase(destino)) {
				aux = t;
			}
		}
		return aux;
	}
	
}