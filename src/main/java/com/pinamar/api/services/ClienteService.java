package com.pinamar.api.services;

import java.util.List;

import javax.validation.Valid;

import com.pinamar.api.exceptions.ClienteException;
import com.pinamar.api.exceptions.LiquidacionException;
import com.pinamar.api.negocio.Cliente;
import com.pinamar.api.negocio.Empleado;
import com.pinamar.api.negocio.EmpleadoFijo;
import com.pinamar.api.negocio.EmpleadoPorHora;
import com.pinamar.api.negocio.EmpleadoView;
import com.pinamar.api.negocio.Factura;
import com.pinamar.api.negocio.Liquidacion;
import com.pinamar.api.negocio.Novedad;
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
	List<EmpleadoFijo> getEmpleadosFijoByCliente(Cliente c);
	List<EmpleadoPorHora> getEmpleadosHoraByCliente(Cliente c);
	public void saveRecibo(Recibo r);
	public void saveLiquidacion(Liquidacion liq);
	public void updateEmpleadoFijo(EmpleadoFijo e);
	public void updateEmpleadoHora(EmpleadoPorHora e);
	public void saveNovedad(@Valid Novedad n);
	public Liquidacion liquidacionMensual(List<EmpleadoFijo> empleadosFijos, List<EmpleadoPorHora> empleadosPorHora, Cliente c);
	public Liquidacion liquidacionQuincenal(List<EmpleadoFijo> empleadosFijos, List<EmpleadoPorHora> empleadosPorHora, Cliente c);
	public Liquidacion liquidacionSemanal(List<EmpleadoFijo> empleadosFijos, List<EmpleadoPorHora> empleadosPorHora, Cliente c);
	public Liquidacion liquidacionDiaria(List<EmpleadoFijo> empleadosFijos, List<EmpleadoPorHora> empleadosPorHora, Cliente c);
	public Liquidacion findLiquidacionById(String _id) throws LiquidacionException;
	public Recibo findReciboById(String hexString);
	String findNombreEmpleadoRecibo(String id);
	List<Factura> findFacturasByCliente(String id);
	EmpleadoView findEmpleadoByCuit(String cuit);
	List<Liquidacion> getLiquidacionesNoFacturadas();
	List<Recibo> getAllRecibos();
	List<Factura> getAllFacturasPendientes();
	List<Empleado> getAllEmpleados();

}
