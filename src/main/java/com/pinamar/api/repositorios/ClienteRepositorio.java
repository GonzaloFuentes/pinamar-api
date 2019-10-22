package com.pinamar.api.repositorios;

import java.util.List;
import java.util.Optional;

import com.pinamar.api.negocio.Cliente;

public interface ClienteRepositorio{
	
	//para los find uso string porque asumo que desde el front mando un string que representa el id y lo convierto
	//lo mismo al reves, si bien recupero un objectId de mongo, al front le mando un string del id (pero no lo visualiza)
	
	Optional <List<Cliente>> getAllClientes();
	Optional<Cliente> findById(String id);
	Optional<Cliente> findByCuit(String cuit);
	public Cliente saveCliente(Cliente c);
	public void deleteCliente(String id);
	public void updateCliente(Cliente c);
}
