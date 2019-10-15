package com.pinamar.api.services;

import java.util.List;

import com.pinamar.api.exceptions.ClubException;
import com.pinamar.api.negocio.Club;

public interface ClubService {

	List<Club> getAllClubs();
	Club findById(String id) throws ClubException;
	Club findByName(String name) throws ClubException;
	List<Club> findByCountry (String country) throws ClubException;
	public Club saveClub(Club c);
	public void deleteClub(String id);
	public void updateClub(Club c);
}
