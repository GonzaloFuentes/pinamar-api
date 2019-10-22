package com.pinamar.api.services;

import java.util.List;

import com.pinamar.api.exceptions.ClienteException;
import com.pinamar.api.negocio.Cliente;

public interface ClienteService {
	
	List<Cliente> getAllClientes();
	Cliente findById(String id) throws ClienteException;
	Cliente findByCuit(String cuit) throws ClienteException;
	public Cliente saveCliente(Cliente c);
	public void deleteCliente(String id);
	public void updateCliente(Cliente c);

}
