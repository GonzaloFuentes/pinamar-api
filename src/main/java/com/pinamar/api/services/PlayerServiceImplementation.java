package com.pinamar.api.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinamar.api.exceptions.PlayerException;
import com.pinamar.api.negocio.Player;
import com.pinamar.api.repositorios.PlayerRepositorio;

@Service("playerService")
@Transactional

public class PlayerServiceImplementation implements PlayerService{
	
	private static final Log log = LogFactory.getLog(PlayerServiceImplementation.class);
	
	private PlayerRepositorio playerRepo;
	
	@Autowired
	public PlayerServiceImplementation(PlayerRepositorio playerRepo) {
		this.playerRepo = playerRepo;
	}

	public List<Player> getAllPlayers() {
		Optional<List<Player>> players = playerRepo.getAllPlayers();
		return players.get();
	}

	public Player findById(String id) throws PlayerException {
		Optional<Player> player = playerRepo.findById(id);
		if (player.isPresent())
			return player.get();
		else
			throw new PlayerException("Player with id: " + id + " not found. Please use another id idiot");
	}

	public Player findByName(String name) throws PlayerException {
		Optional<Player> player = playerRepo.findByName(name);
		if (player.isPresent())
			return player.get();
		else
			throw new PlayerException("Player with name: " + name + " not found. Please use another name idiot");
	}

	public List<Player> findByClub(String club) throws PlayerException {
		Optional<List<Player>> players = playerRepo.findByClub(club);
		if (players.isPresent())
			return players.get();
		else
			throw new PlayerException("Players with club: " + club + " not found. Please use another club like boquita el mas grande");
	}

	public Player savePlayer(Player p) {
		return playerRepo.savePlayer(p);
	}

	public void deletePlayer(String id) {
		playerRepo.deletePlayer(id);
	}

	public void updatePlayer(Player p) {
		playerRepo.updatePlayer(p);
	}

}
