package com.pinamar.api.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinamar.api.exceptions.ClienteException;
import com.pinamar.api.exceptions.EmpleadoException;
import com.pinamar.api.negocio.Cliente;
import com.pinamar.api.negocio.Empleado;
import com.pinamar.api.negocio.EmpleadoFijo;
import com.pinamar.api.negocio.EmpleadoPorHora;
import com.pinamar.api.negocio.EmpleadoView;
import com.pinamar.api.negocio.Liquidacion;
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

	public Empleado saveEmpleado(Empleado e, String tipo, double valor) {
		if(tipo.equalsIgnoreCase("FIJO")) {
			EmpleadoFijo empF = new EmpleadoFijo(new ObjectId(), e.getDni(), e.getNombre(), e.getDireccion(), e.getPuesto(), e.getFechaIngreso(), e.getTipoLiquidacion(), valor, 0, 0, 0, 0, 0, 0, e.getConceptos(), e.getCbu());
			return clienteRepo.saveEmpleadoFijo(empF);
		}
		else { //Si no es es fijo, es por hora
			EmpleadoPorHora empH = new EmpleadoPorHora(new ObjectId(), e.getDni(), e.getNombre(), e.getDireccion(), e.getPuesto(), e.getFechaIngreso(), e.getTipoLiquidacion(), valor, 0, e.getConceptos(), e.getCbu());
			return clienteRepo.saveEmpleadoHora(empH);
		}
	}

	public List<EmpleadoFijo> getEmpleadosFijoByClienteAndTipo(Cliente c, String tipo) {
		return clienteRepo.getEmpleadosFijoByClienteAndTipo(c,tipo);
	}

	public List<EmpleadoPorHora> getEmpleadosHoraByClienteAndTipo(Cliente c, String tipo) {
		return clienteRepo.getEmpleadosHoraByClienteAndTipo(c,tipo);
	}

	public void saveRecibo(Recibo r) {
		clienteRepo.saveRecibo(r);
		
	}

	public void saveLiquidacion(Liquidacion liq) {
		clienteRepo.saveLiquidacion(liq);
	}

}