package com.pinamar.api.repositorios;

import java.util.List;
import java.util.Optional;

import com.pinamar.api.negocio.Club;

public interface ClubRepositorio {
	
	Optional <List<Club>> getAllClubs();
	Optional<Club> findById(String id);
	Optional<Club> findByName(String name);
	Optional<List<Club>> findByCountry(String country);
	public Club saveClub(Club c);
	public void deleteClub(String id);
	public void updateClub(Club c);

}
