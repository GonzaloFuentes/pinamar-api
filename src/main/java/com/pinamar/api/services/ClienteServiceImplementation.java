package com.pinamar.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinamar.api.exceptions.ClienteException;
import com.pinamar.api.negocio.Cliente;
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

}
