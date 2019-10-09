package com.pinamar.api.services;

import java.util.List;

import com.pinamar.api.exceptions.PlayerException;
import com.pinamar.api.negocio.Player;

public interface PlayerService {
	
	List<Player> getAllPlayers();
	Player findById(String id) throws PlayerException;
	Player findByName(String name) throws PlayerException;
	List<Player> findByClub (String club) throws PlayerException;
	public Player savePlayer(Player p);
	public void deletePlayer(String id);
	public void updatePlayer(Player p);

}
