package com.pinamar.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinamar.api.exceptions.PlayerException;
import com.pinamar.api.negocio.Player;
import com.pinamar.api.services.PlayerService;

@RestController
@RequestMapping("/pinamar")
public class PlayerController {
	
	private final PlayerService playersServ;
	private Player p;
	
	@Autowired
	public PlayerController (PlayerService playServ) {
		this.playersServ = playServ;
	}

	@GetMapping("")
	public ResponseEntity<List<Player>> getAllPlayers() {
		return ResponseEntity.ok(playersServ.getAllPlayers());
	}
	
	@GetMapping("/{_id}")
	public ResponseEntity<Player> getPlayerById(@PathVariable("_id") String _id) throws PlayerException{
		try {
			p = playersServ.findById(_id);
		}
		catch(PlayerException e) {
			p = null;
		}
		return ResponseEntity.ok(p);
	}
	
	@GetMapping("/club/{club}")
	public ResponseEntity<List<Player>> getPlayerByClub(@PathVariable("club") String club) throws PlayerException{
		List<Player> ps = new ArrayList<Player>();
		try {
			ps = playersServ.findByClub(club);
		}
		catch(PlayerException e) {
			ps = null;
		}
		return ResponseEntity.ok(ps);
	}
	
	@PostMapping("/")
	public ResponseEntity<Player> savePlayer(@RequestBody @Valid Player p){
		// no hace falta pasar el id, mongo lo asigna solo y lo devuelve solo
		return ResponseEntity.ok(playersServ.savePlayer(p));
	}
	
	@PutMapping("/")
	public ResponseEntity<Player> updatePlayer(@RequestBody @Valid Player p){
		//hay que mandarle el id, si no te crea uno nuevo con otro id
		return ResponseEntity.ok(playersServ.savePlayer(p)); //el metodo en el repo del save y update hacen lo mismo, un save
	}
	
	@DeleteMapping("/{_id}")
	public ResponseEntity<Void> deletePlayer(@PathVariable String _id){
		playersServ.deletePlayer(_id);
		return ResponseEntity.noContent().build();
	}

}