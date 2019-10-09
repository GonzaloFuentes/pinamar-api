package com.pinamar.api.repositorios;

import java.util.List;
import java.util.Optional;

import com.pinamar.api.negocio.Player;

public interface PlayerRepositorio{
	
	//para los find uso string porque asumo que desde el front mando un string que representa el id y lo convierto
	//lo mismo al reves, si bien recupero un objectId de mongo, al front le mando un string del id (pero no lo visualiza)
	
	Optional <List<Player>> getAllPlayers();
	Optional<Player> findById(String id);
	Optional<Player> findByName(String name);
	Optional<List<Player>> findByClub (String club);
	public Player savePlayer(Player p);
	public void deletePlayer(String id);
	public void updatePlayer(Player p);
}
