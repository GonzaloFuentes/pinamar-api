package com.pinamar.api.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.pinamar.api.negocio.Cliente;
import com.pinamar.api.negocio.Empleado;
import com.pinamar.api.negocio.EmpleadoFijo;
import com.pinamar.api.negocio.EmpleadoPorHora;
import com.pinamar.api.negocio.EmpleadoView;
import com.pinamar.api.negocio.Factura;
import com.pinamar.api.negocio.Liquidacion;
import com.pinamar.api.negocio.Novedad;
import com.pinamar.api.negocio.Recibo;

@Repository
public class ClienteRepoImplementation implements ClienteRepositorio{

	private final MongoOperations mongoOp;
	
	@Autowired
	public ClienteRepoImplementation(MongoOperations mongoOperations) {
		this.mongoOp = mongoOperations;
	}
	
	public Optional<List<Cliente>> getAllClientes() {
		List<Cliente> clientes = this.mongoOp.find(new Query(), Cliente.class);
		return Optional.ofNullable(clientes);
	}
	public Optional<Cliente> findById(String id) {
		ObjectId _id = new ObjectId(id);
		Cliente c = this.mongoOp.findOne(new Query(Criteria.where("_id").is(_id)), Cliente.class);
		return Optional.ofNullable(c);
	}
	public Optional<Cliente> findByCuit(String cuit) {
		Cliente c = this.mongoOp.findOne(new Query(Criteria.where("cuit").is(cuit)), Cliente.class);
		return Optional.ofNullable(c);
	}
	public Cliente saveCliente(Cliente c) {
		this.mongoOp.save(c);
		return findById(c.getId()).get();
	}
	public void deleteCliente(String id) {
		ObjectId _id = new ObjectId(id);
		this.mongoOp.findAndRemove(new Query(Criteria.where("_id").is(_id)), Cliente.class);
	}
	public void updateCliente(Cliente c) {
		this.mongoOp.save(c);
	}
	
	public Optional<EmpleadoView> findEmpleadoById(String id) {
		ObjectId _id = new ObjectId(id);
		EmpleadoView e = this.mongoOp.findOne(new Query(Criteria.where("_id").is(_id)), EmpleadoView.class);
		return Optional.ofNullable(e);
	}
	public Empleado saveEmpleadoFijo(EmpleadoFijo e) {
		EmpleadoView aux = new EmpleadoView(new ObjectId(e.getId()), e.getDni(), e.getCuit(), e.getNombre(), e.getDireccion(), e.getPuesto(), e.getFechaIngreso(), 
				"FIJO", e.getTipoLiquidacion(), 0, 0, e.getSueldoBase(), e.getHorasExtra(), e.getDiasAusentes(), e.getDiasEnfermedad(), e.getDiasVacaciones(), 
				e.getFeriados(), e.getDiasTrabajados(), e.getConceptos(), e.getCbu(), e.getRecibos(), e.getUltimaLiquidacion(), e.getDiasContratados());
		this.mongoOp.save(aux);
		EmpleadoView emp = findEmpleadoById(aux.getId()).get();
		e.setId(new ObjectId(emp.getId()));
		return e;
	}

	public Empleado saveEmpleadoHora(EmpleadoPorHora e) {
		EmpleadoView aux = new EmpleadoView(new ObjectId(e.getId()), e.getDni(), e.getCuit(), e.getNombre(), e.getDireccion(), e.getPuesto(), e.getFechaIngreso(), 
				"POR HORA", e.getTipoLiquidacion(), e.getValorHora(), e.getHorasTrabajadas(), 0, 0, 0, 0, 0, 0, 0, e.getConceptos(), e.getCbu(), e.getRecibos(), e.getUltimaLiquidacion(), 0);
		this.mongoOp.save(aux);
		EmpleadoView emp = findEmpleadoById(aux.getId()).get();
		e.setId(new ObjectId(emp.getId()));
		return e;
	}

	public List<Empleado> getEmpleadosByCliente(Cliente c) {
		List<ObjectId> empIds = c.getEmpleados_id();
		List<Empleado> empleados = new ArrayList<Empleado>();
		for (ObjectId id : empIds) {
			Optional<EmpleadoView> evo = this.findEmpleadoById(id.toHexString());
			EmpleadoView ev = evo.get();
			EmpleadoFijo ef;
			EmpleadoPorHora eh;
			if(ev.getTipo().equalsIgnoreCase("FIJO")) {
				ef = new EmpleadoFijo(new ObjectId(ev.getId()), ev.getDni(), ev.getCuit(), ev.getNombre(), ev.getDireccion(), ev.getPuesto(), ev.getFechaIngreso(), 
						ev.getTipoLiquidacion(), ev.getSueldoBase(), ev.getDiasAusentes(), ev.getDiasEnfermedad(), ev.getDiasVacaciones(), ev.getHorasExtras(), ev.getFeriados(), 
						ev.getDiasTrabajados(), ev.getConceptos(), ev.getCbu(), ev.getRecibos(), ev.getUltimaLiquidacion(), ev.getDiasContratados());
				empleados.add(ef);
			}
			else {
				eh = new EmpleadoPorHora(new ObjectId(ev.getId()), ev.getDni(), ev.getCuit(), ev.getNombre(), ev.getDireccion(), ev.getPuesto(), ev.getFechaIngreso(), 
						ev.getTipoLiquidacion(), ev.getValorHora(), ev.getHorasTrabajadas(), ev.getConceptos(), ev.getCbu(), ev.getRecibos(), ev.getUltimaLiquidacion());
				empleados.add(eh);
			}
		}
		return empleados;
	}

	public List<EmpleadoFijo> getEmpleadosFijoByCliente(Cliente c) {
		List<ObjectId> empIds = c.getEmpleados_id();
		List<EmpleadoFijo> empleados = new ArrayList<EmpleadoFijo>();
		for (ObjectId id : empIds) {
			Optional<EmpleadoView> evo = this.findEmpleadoById(id.toHexString());
			EmpleadoView ev = evo.get();
			EmpleadoFijo ef;
			if(ev.getTipo().equalsIgnoreCase("FIJO")) {
				ef = new EmpleadoFijo(new ObjectId(ev.getId()), ev.getDni(), ev.getCuit(), ev.getNombre(), ev.getDireccion(), ev.getPuesto(), ev.getFechaIngreso(), ev.getTipoLiquidacion(), 
						ev.getSueldoBase(), ev.getDiasAusentes(), ev.getDiasEnfermedad(), ev.getDiasVacaciones(), ev.getHorasExtras(), ev.getFeriados(), ev.getDiasTrabajados(), 
						ev.getConceptos(), ev.getCbu(), ev.getRecibos(), ev.getUltimaLiquidacion(), ev.getDiasContratados());
				empleados.add(ef);
			}
		}
		return empleados;
	}

	public List<EmpleadoPorHora> getEmpleadosHoraByCliente(Cliente c) {
		List<ObjectId> empIds = c.getEmpleados_id();
		List<EmpleadoPorHora> empleados = new ArrayList<EmpleadoPorHora>();
		for (ObjectId id : empIds) {
			Optional<EmpleadoView> evo = this.findEmpleadoById(id.toHexString());
			EmpleadoView ev = evo.get();
			EmpleadoPorHora eh;
			if(ev.getTipo().equalsIgnoreCase("POR HORA")) {
				eh = new EmpleadoPorHora(new ObjectId(ev.getId()), ev.getDni(), ev.getCuit(), ev.getNombre(), ev.getDireccion(), ev.getPuesto(), ev.getFechaIngreso(), 
						ev.getTipoLiquidacion(), ev.getValorHora(), ev.getHorasTrabajadas(), ev.getConceptos(), ev.getCbu(), ev.getRecibos(), ev.getUltimaLiquidacion());
				empleados.add(eh);
			}
		}
		return empleados;
	}

	public void saveRecibo(Recibo r) {
		this.mongoOp.save(r);
	}

	public void saveLiquidacion(Liquidacion liq) {
		this.mongoOp.save(liq);
	}

	public void updateEmpleadoFijo(EmpleadoFijo e) {
		EmpleadoView aux = new EmpleadoView(new ObjectId(e.getId()), e.getDni(), e.getCuit(), e.getNombre(), e.getDireccion(), e.getPuesto(), e.getFechaIngreso(), "FIJO", 
				e.getTipoLiquidacion(), 0, 0, e.getSueldoBase(), e.getHorasExtra(), e.getDiasAusentes(), e.getDiasEnfermedad(), e.getDiasVacaciones(), e.getFeriados(), 
				e.getDiasTrabajados(), e.getConceptos(), e.getCbu(), e.getRecibos(), e.getUltimaLiquidacion(), e.getDiasContratados());
		this.mongoOp.save(aux);
	}

	public void updateEmpleadoHora(EmpleadoPorHora e) {
		EmpleadoView aux = new EmpleadoView(new ObjectId(e.getId()), e.getDni(), e.getCuit(), e.getNombre(), e.getDireccion(), e.getPuesto(), e.getFechaIngreso(), "POR HORA", 
				e.getTipoLiquidacion(), e.getValorHora(), e.getHorasTrabajadas(), 0, 0, 0, 0, 0, 0, 0, e.getConceptos(), e.getCbu(), e.getRecibos(), e.getUltimaLiquidacion(), 0);
		this.mongoOp.save(aux);
	}

	public void saveNovedad(Novedad n) {
		this.mongoOp.save(n);
	}

	public Optional<Liquidacion> findLiquidacionById(String id) {
		ObjectId _id = new ObjectId(id);
		Liquidacion liq = this.mongoOp.findOne(new Query(Criteria.where("_id").is(_id)), Liquidacion.class);
		return Optional.ofNullable(liq);
	}

	public Optional<Recibo> findReciboById(String id) {
		ObjectId _id = new ObjectId(id);
		Recibo r = this.mongoOp.findOne(new Query(Criteria.where("_id").is(_id)), Recibo.class);
		return Optional.ofNullable(r);
	}

	public void saveFactura(Factura f) {
		this.mongoOp.save(f);
	}

	public String findNombreEmpleadoRecibo(String id) {
		List<EmpleadoView> emps = this.getAllEmpleados();
		for (EmpleadoView e : emps) {
			for(ObjectId r : e.getRecibos()) {
				if(r.toHexString().equalsIgnoreCase(id))
					return e.getNombre();
			}
		}
		return null;
	}

	public Optional<List<Factura>> findFacturasByCliente(String id) {
		List<Factura> facturas = this.mongoOp.find(new Query(Criteria.where("id_cliente").is(id)), Factura.class);
		return Optional.ofNullable(facturas);
	}
	
	private List<EmpleadoView> getAllEmpleados(){
		return this.mongoOp.find(new Query(), EmpleadoView.class);
	}

	public Optional<EmpleadoView> findEmpleadoByCuit(String cuit) {
		EmpleadoView e = this.mongoOp.findOne(new Query(Criteria.where("cuit").is(cuit)), EmpleadoView.class);
		return Optional.ofNullable(e);
	}

	public List<Liquidacion> getLiquidacionesNoFacturadas() {
		return this.mongoOp.find(new Query(Criteria.where("facturada").is(false)), Liquidacion.class);
	}

	@Override
	public List<Recibo> getAllRecibos() {
		return this.mongoOp.find(new Query(), Recibo.class);
	}

	@Override
	public List<Factura> gettAllFacturasPendientes() {
		return this.mongoOp.find(new Query(Criteria.where("pendiente").is(true)), Factura.class);
	}

	public List<Empleado> gettAllEmpleados() {
		List<EmpleadoView> emps =  this.mongoOp.find(new Query(), EmpleadoView.class);
		List<Empleado> aux = new ArrayList<Empleado>();
		for (EmpleadoView ev : emps) {
			if(ev.getTipo().equalsIgnoreCase("FIJO")) {
				EmpleadoFijo ef = new EmpleadoFijo(new ObjectId(ev.getId()), ev.getDni(), ev.getCuit(), ev.getNombre(), ev.getDireccion(), ev.getPuesto(), ev.getFechaIngreso(), ev.getTipoLiquidacion(), 
						ev.getSueldoBase(), ev.getDiasAusentes(), ev.getDiasEnfermedad(), ev.getDiasVacaciones(), ev.getHorasExtras(), ev.getFeriados(), ev.getDiasTrabajados(), 
						ev.getConceptos(), ev.getCbu(), ev.getRecibos(), ev.getUltimaLiquidacion(), ev.getDiasContratados());
				aux.add(ef);
			}
			else {
				EmpleadoPorHora eh = new EmpleadoPorHora(new ObjectId(ev.getId()), ev.getDni(), ev.getCuit(), ev.getNombre(), ev.getDireccion(), ev.getPuesto(), ev.getFechaIngreso(), 
						ev.getTipoLiquidacion(), ev.getValorHora(), ev.getHorasTrabajadas(), ev.getConceptos(), ev.getCbu(), ev.getRecibos(), ev.getUltimaLiquidacion());
				aux.add(eh);
			}
		}
		return aux;
	}
	
}