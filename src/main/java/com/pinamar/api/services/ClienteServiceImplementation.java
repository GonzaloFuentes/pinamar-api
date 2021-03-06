package com.pinamar.api.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinamar.api.exceptions.ClienteException;
import com.pinamar.api.exceptions.EmpleadoException;
import com.pinamar.api.exceptions.LiquidacionException;
import com.pinamar.api.exceptions.ReciboException;
import com.pinamar.api.negocio.Cliente;
import com.pinamar.api.negocio.Empleado;
import com.pinamar.api.negocio.EmpleadoFijo;
import com.pinamar.api.negocio.EmpleadoPorHora;
import com.pinamar.api.negocio.EmpleadoView;
import com.pinamar.api.negocio.Factura;
import com.pinamar.api.negocio.Liquidacion;
import com.pinamar.api.negocio.Novedad;
import com.pinamar.api.negocio.Recibo;
import com.pinamar.api.repositorios.ClienteRepositorio;

@Service("clienteService")
@Transactional
public class ClienteServiceImplementation implements ClienteService{
	
	private ClienteRepositorio clienteRepo;
	
	@Autowired
	public ClienteServiceImplementation(ClienteRepositorio clienteRepo) {
		this.clienteRepo = clienteRepo;
	}

	public List<Cliente> getAllClientes() {
		Optional<List<Cliente>> clientes = clienteRepo.getAllClientes();
		return clientes.get();
	}

	public Cliente findById(String id) throws ClienteException {
		Optional<Cliente> cliente = clienteRepo.findById(id);
		if (cliente.isPresent())
			return cliente.get();
		else
			throw new ClienteException("Cliente con id: " + id + " no encontrado.");
	}

	public Cliente findByCuit(String cuit) throws ClienteException {
		Optional<Cliente> cliente = clienteRepo.findByCuit(cuit);
		if (cliente.isPresent())
			return cliente.get();
		else
			throw new ClienteException("Cliente con cuit: " + cuit + " no encontrado.");
	}

	public Cliente saveCliente(Cliente c) {
		return clienteRepo.saveCliente(c);
	}

	public void deleteCliente(String id) {
		clienteRepo.deleteCliente(id);
	}

	public void updateCliente(Cliente c) {
		clienteRepo.updateCliente(c);
	}
	
	public List<Empleado> getEmpleadosByCliente(Cliente c) {
		return clienteRepo.getEmpleadosByCliente(c);
	}
	
	public EmpleadoView findEmpleadoById(String _id) {
		Optional<EmpleadoView> e = clienteRepo.findEmpleadoById(_id);
		if(e.isPresent())
			return e.get();
		else
			throw new EmpleadoException("Empleado con el id: " + _id + " no encontrado.");
	}

	public Empleado saveEmpleado(Empleado e, String tipo, double valor, int diasContratados) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); 
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		if(tipo.equalsIgnoreCase("FIJO")) {
			EmpleadoFijo empF = new EmpleadoFijo(new ObjectId(), e.getDni(), e.getCuit(), e.getNombre(), e.getDireccion(), e.getPuesto(), e.getFechaIngreso(), 
					e.getTipoLiquidacion(), valor, 0, 0, 0, 0, 0, 0, e.getConceptos(), e.getCbu(), new ArrayList<ObjectId>(), calendar.getTime(), diasContratados);
			return clienteRepo.saveEmpleadoFijo(empF);
		}
		else { //Si no es es fijo, es por hora
			EmpleadoPorHora empH = new EmpleadoPorHora(new ObjectId(), e.getDni(), e.getCuit(), e.getNombre(), e.getDireccion(), e.getPuesto(), 
					e.getFechaIngreso(), e.getTipoLiquidacion(), valor, 0, e.getConceptos(), e.getCbu(), new ArrayList<ObjectId>(), calendar.getTime());
			return clienteRepo.saveEmpleadoHora(empH);
		}
	}

	public List<EmpleadoFijo> getEmpleadosFijoByCliente(Cliente c) {
		return clienteRepo.getEmpleadosFijoByCliente(c);
	}

	public List<EmpleadoPorHora> getEmpleadosHoraByCliente(Cliente c) {
		return clienteRepo.getEmpleadosHoraByCliente(c);
	}

	public void saveRecibo(Recibo r) {
		clienteRepo.saveRecibo(r);
		
	}

	public void saveLiquidacion(Liquidacion liq) {
		clienteRepo.saveLiquidacion(liq);
	}

	public void updateEmpleadoFijo(EmpleadoFijo e) {
		clienteRepo.updateEmpleadoFijo(e);
	}

	public void updateEmpleadoHora(EmpleadoPorHora e) {
		clienteRepo.updateEmpleadoHora(e);
	}

	public void saveNovedad(Novedad n) {
		clienteRepo.saveNovedad(n);
	}

	public Liquidacion liquidacionMensual(List<EmpleadoFijo> empleadosFijos, List<EmpleadoPorHora> empleadosPorHora, Cliente c) {
		Recibo rec;
		Liquidacion liq = null;
		List<Recibo> recibos = new ArrayList<Recibo>();
		for (EmpleadoFijo e : empleadosFijos) {
			if(e.getTipoLiquidacion().equalsIgnoreCase("MENSUAL")) {
				rec = e.liquidarSueldo();
				if(rec != null) {
					recibos.add(rec);
					clienteRepo.updateEmpleadoFijo(e); //deberia actualizar al empleado y agregarle un item al array de recibos, nada mas. el add recibo lo hace dentro del liquidar sueldo
				}
			}
		}
		for (EmpleadoPorHora e : empleadosPorHora) {
			if(e.getTipoLiquidacion().equalsIgnoreCase("MENSUAL")) {
				rec = e.liquidarSueldo();
				if(rec != null) {
					recibos.add(rec);
					clienteRepo.updateEmpleadoHora(e); //deberia actualizar al empleado y agregarle un item al array de recibos, nada mas. el add recibo lo hace dentro del liquidar sueldo
				}
			}
		}
		if(!recibos.isEmpty()) {
			List<ObjectId> rs = new ArrayList<ObjectId>();
			double total = 0;
			for (Recibo r : recibos) {
				rs.add(new ObjectId(r.getId()));
				total += r.getSueldoNeto();
				clienteRepo.saveRecibo(r);
			}
			liq = new Liquidacion(new ObjectId(), rs, "MENSUAL", new Date(), total, false);
			c.addLiquidacion(new ObjectId(liq.getId()));
			clienteRepo.saveLiquidacion(liq);
			clienteRepo.updateCliente(c); //deberia actualizar al cliente y agregarle un item al array de liquidaciones, nada mas
			
			Factura f = new Factura(new ObjectId(), c.getId(), liq.getId(), new Date(), (recibos.size() * 200), true);
			clienteRepo.saveFactura(f);
			return liq;
		}
		return liq;
	}

	public Liquidacion liquidacionQuincenal(List<EmpleadoFijo> empleadosFijos, List<EmpleadoPorHora> empleadosPorHora, Cliente c) {
		Recibo rec;
		Liquidacion liq = null;
		List<Recibo> recibos = new ArrayList<Recibo>();
		for (EmpleadoFijo e : empleadosFijos) {
			if(e.getTipoLiquidacion().equalsIgnoreCase("QUINCENAL")) {
				rec = e.liquidarSueldo();
				if(rec != null) {
					recibos.add(rec);
					clienteRepo.updateEmpleadoFijo(e); //deberia actualizar al empleado y agregarle un item al array de recibos, nada mas. el add recibo lo hace dentro del liquidar sueldo
				}
			}
		}
		for (EmpleadoPorHora e : empleadosPorHora) {
			if(e.getTipoLiquidacion().equalsIgnoreCase("QUINCENAL")) {
				rec = e.liquidarSueldo();
				if(rec != null) {
					recibos.add(rec);
					clienteRepo.updateEmpleadoHora(e); //deberia actualizar al empleado y agregarle un item al array de recibos, nada mas. el add recibo lo hace dentro del liquidar sueldo
				}
			}
		}
		if(!recibos.isEmpty()) {
			List<ObjectId> rs = new ArrayList<ObjectId>();
			double total = 0;
			for (Recibo r : recibos) {
				rs.add(new ObjectId(r.getId()));
				total += r.getSueldoNeto();
				clienteRepo.saveRecibo(r);
			}
			liq = new Liquidacion(new ObjectId(), rs, "QUINCENAL", new Date(), total, false);
			c.addLiquidacion(new ObjectId(liq.getId()));
			clienteRepo.saveLiquidacion(liq);
			clienteRepo.updateCliente(c); //deberia actualizar al cliente y agregarle un item al array de liquidaciones, nada mas
			
			Factura f = new Factura(new ObjectId(), c.getId(), liq.getId(), new Date(), (recibos.size() * 200), true);
			clienteRepo.saveFactura(f);
			return liq;
		}
		return liq;
	}

	public Liquidacion liquidacionSemanal(List<EmpleadoFijo> empleadosFijos, List<EmpleadoPorHora> empleadosPorHora, Cliente c) {
		Recibo rec;
		Liquidacion liq = null;
		List<Recibo> recibos = new ArrayList<Recibo>();
		for (EmpleadoFijo e : empleadosFijos) {
			if(e.getTipoLiquidacion().equalsIgnoreCase("SEMANAL")) {
				rec = e.liquidarSueldo();
				if(rec != null) {
					recibos.add(rec);
					clienteRepo.updateEmpleadoFijo(e); //deberia actualizar al empleado y agregarle un item al array de recibos, nada mas. el add recibo lo hace dentro del liquidar sueldo
				}
			}
		}
		for (EmpleadoPorHora e : empleadosPorHora) {
			if(e.getTipoLiquidacion().equalsIgnoreCase("SEMANAL")) {
				rec = e.liquidarSueldo();
				if(rec != null) {
					recibos.add(rec);
					clienteRepo.updateEmpleadoHora(e); //deberia actualizar al empleado y agregarle un item al array de recibos, nada mas. el add recibo lo hace dentro del liquidar sueldo
				}
			}
		}
		if(!recibos.isEmpty()) {
			List<ObjectId> rs = new ArrayList<ObjectId>();
			double total = 0;
			for (Recibo r : recibos) {
				rs.add(new ObjectId(r.getId()));
				total += r.getSueldoNeto();
				clienteRepo.saveRecibo(r);
			}
			liq = new Liquidacion(new ObjectId(), rs, "SEMANAL", new Date(), total, false);
			c.addLiquidacion(new ObjectId(liq.getId()));
			clienteRepo.saveLiquidacion(liq);
			clienteRepo.updateCliente(c); //deberia actualizar al cliente y agregarle un item al array de liquidaciones, nada mas
			
			Factura f = new Factura(new ObjectId(), c.getId(), liq.getId(), new Date(), (recibos.size() * 200), true);
			clienteRepo.saveFactura(f);
			return liq;
		}
		return liq;
	}

	public Liquidacion liquidacionDiaria(List<EmpleadoFijo> empleadosFijos, List<EmpleadoPorHora> empleadosPorHora, Cliente c) {
		Recibo rec;
		Liquidacion liq = null;
		List<Recibo> recibos = new ArrayList<Recibo>();
		for (EmpleadoFijo e : empleadosFijos) {
			if(e.getTipoLiquidacion().equalsIgnoreCase("DIARIA")) {
				rec = e.liquidarSueldo();
				if(rec != null) {
					recibos.add(rec);
					clienteRepo.updateEmpleadoFijo(e); //deberia actualizar al empleado y agregarle un item al array de recibos, nada mas. el add recibo lo hace dentro del liquidar sueldo
				}
			}
		}
		for (EmpleadoPorHora e : empleadosPorHora) {
			if(e.getTipoLiquidacion().equalsIgnoreCase("DIARIA")) {
				rec = e.liquidarSueldo();
				if(rec != null) {
					recibos.add(rec);
					clienteRepo.updateEmpleadoHora(e); //deberia actualizar al empleado y agregarle un item al array de recibos, nada mas. el add recibo lo hace dentro del liquidar sueldo
				}
			}
		}
		if(!recibos.isEmpty()) {
			List<ObjectId> rs = new ArrayList<ObjectId>();
			double total = 0;
			for (Recibo r : recibos) {
				rs.add(new ObjectId(r.getId()));
				total += r.getSueldoNeto();
				clienteRepo.saveRecibo(r);
			}
			liq = new Liquidacion(new ObjectId(), rs, "DIARIA", new Date(), total, false);
			c.addLiquidacion(new ObjectId(liq.getId()));
			clienteRepo.saveLiquidacion(liq);
			clienteRepo.updateCliente(c); //deberia actualizar al cliente y agregarle un item al array de liquidaciones, nada mas
			
			Factura f = new Factura(new ObjectId(), c.getId(), liq.getId(), new Date(), (recibos.size() * 200), true);
			clienteRepo.saveFactura(f);
			return liq;
		}
		return liq;
	}

	public Liquidacion findLiquidacionById(String _id) throws LiquidacionException{
		Optional<Liquidacion> liq = clienteRepo.findLiquidacionById(_id);
		if (liq.isPresent())
			return liq.get();
		else
			throw new LiquidacionException("Liquidacion con id: " + _id + " no encontrada.");
	}

	public Recibo findReciboById(String id) throws ReciboException {
		Optional<Recibo> r = clienteRepo.findReciboById(id);
		if(r.isPresent())
			return r.get();
		else
			throw new ReciboException("Recibo con el id: " + id + " no encontrado.");
	}

	public String findNombreEmpleadoRecibo(String id) {
		return clienteRepo.findNombreEmpleadoRecibo(id);
	}

	public List<Factura> findFacturasByCliente(String id) {
		Optional<List<Factura>> facturas = clienteRepo.findFacturasByCliente(id);
		return facturas.get();
	}

	public EmpleadoView findEmpleadoByCuit(String cuit) throws EmpleadoException {
		Optional<EmpleadoView> e = clienteRepo.findEmpleadoByCuit(cuit);
		if (e.isPresent())
			return e.get();
		else
			throw new EmpleadoException("Empleado con cuit: " + cuit + " no encontrado.");
	}

	public List<Liquidacion> getLiquidacionesNoFacturadas() {
		return clienteRepo.getLiquidacionesNoFacturadas();
	}

	public List<Recibo> getAllRecibos() {
		return clienteRepo.getAllRecibos();
	}

	public List<Factura> getAllFacturasPendientes() {
		return clienteRepo.gettAllFacturasPendientes();
	}

	public List<Empleado> getAllEmpleados() {
		return clienteRepo.gettAllEmpleados();
	}

	public List<Liquidacion> getLiquidacionesByCliente(Cliente c) {
		List<Liquidacion> liqs = new ArrayList<Liquidacion>();
		Liquidacion l = null;
		for (ObjectId id : c.getLiquidaciones()) {
			l = this.findLiquidacionById(id.toHexString());
			liqs.add(l);
		}
		return liqs;
	}

	public void updateLiquidacion(Liquidacion liq) {
		this.clienteRepo.updateLiquidacion(liq);
		
	}

	public void updateFactura(Factura f) {
		this.clienteRepo.updateFactura(f);
	}

}