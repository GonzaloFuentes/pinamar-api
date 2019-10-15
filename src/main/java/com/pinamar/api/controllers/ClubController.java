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

import com.pinamar.api.exceptions.ClubException;
import com.pinamar.api.exceptions.PlayerException;
import com.pinamar.api.negocio.Club;
import com.pinamar.api.services.ClubService;

@RestController
@RequestMapping("/clubs")
public class ClubController {
	
	private final ClubService clubService;
	private Club c;

	@Autowired
	public ClubController(ClubService cluServ) {
		this.clubService = cluServ;
	}
	
	@GetMapping("")
	public ResponseEntity<List<Club>> getAllClubs() {
		return ResponseEntity.ok(clubService.getAllClubs());
	}

	@GetMapping("/{_id}")
	public ResponseEntity<Club> getClubById(@PathVariable("_id") String _id) throws ClubException{
		try {
			c = clubService.findById(_id);
		}
		catch(PlayerException e) {
			c = null;
		}
		return ResponseEntity.ok(c);
	}
	
	@GetMapping("/country/{country}")
	public ResponseEntity<List<Club>> getClubByCountry(@PathVariable("country") String country) throws ClubException{
		List<Club> cs = new ArrayList<Club>();
		try {
			cs = clubService.findByCountry(country);
		}
		catch(PlayerException e) {
			cs = null;
		}
		return ResponseEntity.ok(cs);
	}
	
	@PostMapping("/")
	public ResponseEntity<Club> saveClub(@RequestBody @Valid Club c){
		// no hace falta pasar el id, mongo lo asigna solo y lo devuelve solo
		return ResponseEntity.ok(clubService.saveClub(c));
	}
	
	@PutMapping("/")
	public ResponseEntity<Club> updateClub(@RequestBody @Valid Club c){
		//hay que mandarle el id, si no te crea uno nuevo con otro id
		return ResponseEntity.ok(clubService.saveClub(c)); //el metodo en el repo del save y update hacen lo mismo -> un save
	}
	
	@DeleteMapping("/{_id}")
	public ResponseEntity<Void> deleteClub(@PathVariable String _id){
		clubService.deleteClub(_id);
		return ResponseEntity.noContent().build();
	}
}