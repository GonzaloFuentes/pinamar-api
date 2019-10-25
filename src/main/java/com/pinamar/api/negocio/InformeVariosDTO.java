package com.pinamar.api.negocio;

import java.util.List;

public class InformeVariosDTO {
	
	private String destinoCBU;
	private List<Transferencia> tranferenciasUnicas;
	
	public InformeVariosDTO(String destinoCBU, List<Transferencia> tranferenciasUnicas) {
		super();
		this.destinoCBU = destinoCBU;
		this.tranferenciasUnicas = tranferenciasUnicas;
	}
	
	public String getDestinoCBU() {
		return destinoCBU;
	}

	public void setDestinoCBU(String destinoCBU) {
		this.destinoCBU = destinoCBU;
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
			if(t.getOrigenCBU().equalsIgnoreCase(destino)) {
				aux = t;
			}
		}
		return aux;
	}
	
}