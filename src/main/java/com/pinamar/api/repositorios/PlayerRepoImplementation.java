package com.pinamar.api.repositorios;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.pinamar.api.negocio.Player;

@Repository
public class PlayerRepoImplementation implements PlayerRepositorio{

	private final MongoOperations mongoOp;
	
	@Autowired
	public PlayerRepoImplementation(MongoOperations mongoOperations) {
		this.mongoOp = mongoOperations;
	}
	
	public Optional<List<Player>> getAllPlayers() {
		List<Player> players = this.mongoOp.find(new Query(), Player.class);
		return Optional.ofNullable(players);
	}
	public Optional<Player> findById(String id) {
		ObjectId _id = new ObjectId(id);
		Player p = this.mongoOp.findOne(new Query(Criteria.where("_id").is(_id)), Player.class);
		return Optional.ofNullable(p);
	}
	public Optional<Player> findByName(String name) {
		Player p = this.mongoOp.findOne(new Query(Criteria.where("name").is(name)), Player.class);
		return Optional.ofNullable(p);
	}
	public Optional<List<Player>> findByClub(String club) {
		List<Player> ps = this.mongoOp.find(new Query(Criteria.where("club.name").is(club)), Player.class);
		return Optional.ofNullable(ps);
	}
	public Player savePlayer(Player p) {
		this.mongoOp.save(p);
		return findById(p.getId()).get();
	}
	public void deletePlayer(String id) {
		ObjectId _id = new ObjectId(id);
		this.mongoOp.findAndRemove(new Query(Criteria.where("_id").is(_id)), Player.class);
	}
	public void updatePlayer(Player p) {
		this.mongoOp.save(p);
	}
}