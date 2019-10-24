package com.pinamar.api.repositorios;

import java.util.List;
import java.util.Optional;

import com.pinamar.api.negocio.Cliente;
import com.pinamar.api.negocio.Empleado;
import com.pinamar.api.negocio.EmpleadoFijo;
import com.pinamar.api.negocio.EmpleadoPorHora;
import com.pinamar.api.negocio.EmpleadoView;
import com.pinamar.api.negocio.Factura;
import com.pinamar.api.negocio.Liquidacion;
import com.pinamar.api.negocio.Novedad;
import com.pinamar.api.negocio.Recibo;

public interface ClienteRepositorio{
	
	//para los find uso string porque asumo que desde el front mando un string que representa el id y lo convierto
	//lo mismo al reves, si bien recupero un objectId de mongo, al front le mando un string del id (pero no lo visualiza)
	
	Optional <List<Cliente>> getAllClientes();
	Optional<Cliente> findById(String id);
	Optional<Cliente> findByCuit(String cuit);
	public Cliente saveCliente(Cliente c);
	public void deleteCliente(String id);
	public void updateCliente(Cliente c);
	public Empleado saveEmpleadoFijo(EmpleadoFijo empF);
	public Empleado saveEmpleadoHora(EmpleadoPorHora empH);
	public Optional<EmpleadoView> findEmpleadoById(String _id);
	List<Empleado> getEmpleadosByCliente(Cliente c);
	List<EmpleadoFijo> getEmpleadosFijoByCliente(Cliente c);
	List<EmpleadoPorHora> getEmpleadosHoraByCliente(Cliente c);
	public void saveRecibo(Recibo r);
	public void saveLiquidacion(Liquidacion liq);
	public void updateEmpleadoFijo(EmpleadoFijo e);
	public void updateEmpleadoHora(EmpleadoPorHora e);
	public void saveNovedad(Novedad n);
	Optional<Liquidacion> findLiquidacionById(String _id);
	Optional<Recibo> findReciboById(String id);
	public void saveFactura(Factura f);
	String findNombreEmpleadoRecibo(String id);
	Optional <List<Factura>> findFacturasByCliente(String id);
	Optional<EmpleadoView> findEmpleadoByCuit(String cuit);
	List<Liquidacion> getLiquidacionesNoFacturadas();
	List<Recibo> getAllRecibos();
	List<Factura> gettAllFacturasPendientes();
	List<Empleado> gettAllEmpleados();
}
