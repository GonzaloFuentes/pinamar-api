package com.pinamar.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
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
import com.pinamar.api.negocio.Club;
import com.pinamar.api.negocio.Player;
import com.pinamar.api.services.PlayerService;

@RestController
@RequestMapping("/players")
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
		//si necesito una coleccion de clubes, tengo que recibir el id como parametro y buscarlo
		//no existe un join en mongo ya que son colecciones y no tablas
		//si al club accedo a traves del jugador, el id seria al pedo
		Club c = new Club(new ObjectId(), "La Pachorra", 2008, "Argentina", "Interno");
		p.setClub(c);
		// no hace falta pasar el id al jugador, mongo lo asigna solo y lo devuelve solo
		return ResponseEntity.ok(playersServ.savePlayer(p));
	}
	
	@PutMapping("/")
	public ResponseEntity<Player> updatePlayer(@RequestBody @Valid Player p){
		//hay que mandarle el id, si no te crea uno nuevo con otro id
		//hay que pasar el objeto completo con sus colecciones dentro tambien, si no lo borra. Por ejemplo, si no mando el club lo pone null y lo borra de la base
		//sino se puede hacer un get por el id y a ese setear los nuevos valores recibidos y guardar el buscado actualizado, como se hace aca
		//otra es recibir el id y los campos a cambiar, hacer el find y setear los nuevos valores
		Player aux = playersServ.findById(p.getId());
		System.out.println("la edad si no la paso es " + p.getAge()); //devuelve 0
		System.out.println("el club si no lo paso es " + p.getClub()); //devuelve null
		System.out.println("el nombre si no lo paso es " + p.getName()); //devuelve null
		if(p.getAge() != 0)
			aux.setAge(p.getAge());
		if(p.getClub() != null)
			aux.setClub(p.getClub());
		if(p.getName() != null)
			aux.setName(p.getName());
		return ResponseEntity.ok(playersServ.savePlayer(aux)); //el metodo en el repo del save y update hacen lo mismo -> un save
	}
	
	@DeleteMapping("/{_id}")
	public ResponseEntity<Void> deletePlayer(@PathVariable String _id){
		playersServ.deletePlayer(_id);
		return ResponseEntity.noContent().build();
	}

}