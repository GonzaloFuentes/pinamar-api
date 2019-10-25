package com.pinamar.api.negocio;

import java.util.List;

public class MovimientoDTO {
	
	private InformeVariosDTO cobro;
	private List<InformeVariosDTO> pago;
	
	public MovimientoDTO(InformeVariosDTO cobro, List<InformeVariosDTO> pago) {
		super();
		this.cobro = cobro;
		this.pago = pago;
	}

	public InformeVariosDTO getCobro() {
		return cobro;
	}

	public void setCobro(InformeVariosDTO cobro) {
		this.cobro = cobro;
	}

	public List<InformeVariosDTO> getPago() {
		return pago;
	}

	public void setPago(List<InformeVariosDTO> pago) {
		this.pago = pago;
	}
	
}