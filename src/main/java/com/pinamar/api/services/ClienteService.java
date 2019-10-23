package com.pinamar.api.services;

import java.util.List;

import com.pinamar.api.exceptions.ClienteException;
import com.pinamar.api.negocio.Cliente;
import com.pinamar.api.negocio.Empleado;
import com.pinamar.api.negocio.EmpleadoFijo;
import com.pinamar.api.negocio.EmpleadoPorHora;
import com.pinamar.api.negocio.EmpleadoView;
import com.pinamar.api.negocio.Liquidacion;
import com.pinamar.api.negocio.Recibo;

public interface ClienteService {
	
	List<Cliente> getAllClientes();
	Cliente findById(String id) throws ClienteException;
	Cliente findByCuit(String cuit) throws ClienteException;
	public Cliente saveCliente(Cliente c);
	public void deleteCliente(String id);
	public void updateCliente(Cliente c);
	public Empleado saveEmpleado(Empleado e, String tipo, double valor);
	public EmpleadoView findEmpleadoById(String _id);
	public List<Empleado> getEmpleadosByCliente(Cliente c);
	List<EmpleadoFijo> getEmpleadosFijoByClienteAndTipo(Cliente c, String tipo);
	List<EmpleadoPorHora> getEmpleadosHoraByClienteAndTipo(Cliente c, String tipo);
	public void saveRecibo(Recibo r);
	public void saveLiquidacion(Liquidacion liq);

}
