package com.pinamar.api.negocio;

import java.util.List;

public class InformeVariosDTO {
	
	private String destinoCBU;
	private List<Transferencia> transferenciasUnicas;
	
	public InformeVariosDTO(String destinoCBU, List<Transferencia> transferenciasUnicas) {
		super();
		this.destinoCBU = destinoCBU;
		this.transferenciasUnicas = transferenciasUnicas;
	}

	public String getDestinoCBU() {
		return destinoCBU;
	}

	public void setDestinoCBU(String destinoCBU) {
		this.destinoCBU = destinoCBU;
	}

	public List<Transferencia> getTransferenciasUnicas() {
		return transferenciasUnicas;
	}

	public void setTransferenciasUnicas(List<Transferencia> transferenciasUnicas) {
		this.transferenciasUnicas = transferenciasUnicas;
	}
	
	public Transferencia getTransferenciaByDestino(String destino) {
		Transferencia aux = null;
		for(Transferencia t : this.transferenciasUnicas) {
			if(t.getOrigenCBU().equalsIgnoreCase(destino)) {
				aux = t;
			}
		}
		return aux;
	}
	
}
