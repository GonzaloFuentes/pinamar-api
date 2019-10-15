package com.pinamar.api.repositorios;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.pinamar.api.negocio.Club;

@Repository
public class ClubRepositorioImplem implements ClubRepositorio {

	private final MongoOperations mongoOp;
	
	@Autowired
	public ClubRepositorioImplem(MongoOperations mongoOperations) {
		this.mongoOp = mongoOperations;
	}

	public Optional<List<Club>> getAllClubs() {
		List<Club> clubs = this.mongoOp.find(new Query(), Club.class);
		return Optional.ofNullable(clubs);
	}

	public Optional<Club> findById(String id) {
		ObjectId _id = new ObjectId(id);
		Club c = this.mongoOp.findOne(new Query(Criteria.where("_id").is(_id)), Club.class);
		return Optional.ofNullable(c);
	}

	public Optional<Club> findByName(String name) {
		Club c = this.mongoOp.findOne(new Query(Criteria.where("name").is(name)), Club.class);
		return Optional.ofNullable(c);
	}

	public Optional<List<Club>> findByCountry(String country) {
		List<Club> cs = this.mongoOp.find(new Query(Criteria.where("country").is(country)), Club.class);
		return Optional.ofNullable(cs);
	}

	public Club saveClub(Club c) {
		this.mongoOp.save(c);
		return findById(c.getId()).get();
	}

	public void deleteClub(String id) {
		ObjectId _id = new ObjectId(id);
		this.mongoOp.findAndRemove(new Query(Criteria.where("_id").is(_id)), Club.class);
	}

	public void updateClub(Club c) {
		this.mongoOp.save(c);
	}
}