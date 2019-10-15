package com.pinamar.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinamar.api.exceptions.ClubException;
import com.pinamar.api.negocio.Club;
import com.pinamar.api.repositorios.ClubRepositorio;

@Service("clubsService")
@Transactional
public class ClubServiceImplem implements ClubService {

	private ClubRepositorio clubRepo;
	
	@Autowired
	public ClubServiceImplem(ClubRepositorio clubRepo) {
		this.clubRepo = clubRepo;
	}
	
	public List<Club> getAllClubs() {
		Optional<List<Club>> clubs = clubRepo.getAllClubs();
		return clubs.get();
	}
	public Club findById(String id) throws ClubException {
		Optional<Club> clu = clubRepo.findById(id);
		if (clu.isPresent())
			return clu.get();
		else
			throw new ClubException("Club with id: " + id + " not found. Please use another id");
	}
	public Club findByName(String name) throws ClubException {
		Optional<Club> clu = clubRepo.findByName(name);
		if (clu.isPresent())
			return clu.get();
		else
			throw new ClubException("Club with name: " + name + " not found. Please use another name");
	}
	public List<Club> findByCountry(String country) throws ClubException {
		Optional<List<Club>> clu = clubRepo.findByCountry(country);
		if (clu.isPresent())
			return clu.get();
		else
			throw new ClubException("Clubs with country: " + country + " not found. Please use another country");
	}
	public Club saveClub(Club c) {
		return this.clubRepo.saveClub(c);
	}
	public void deleteClub(String id) {
		this.clubRepo.deleteClub(id);
	}
	public void updateClub(Club c) {
		this.clubRepo.updateClub(c);
	}
}