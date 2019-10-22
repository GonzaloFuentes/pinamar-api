package com.pinamar.api.repositorios;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.pinamar.api.negocio.Cliente;

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
	
}