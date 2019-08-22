package com.macrosoftas.archijee.service;

import java.util.List;

import com.macrosoftas.archijee.model.Genre;
import com.macrosoftas.archijee.model.Livre;

public interface LivreServiceInterface {
	
	public Livre saveLivre(Livre livre);
	public Boolean deleteLivre(Long livreId);
	public Livre editLivre(Livre livre);
	public Livre findLivre(Long livreId);
	public List<Livre> getAllLivres();
	public List<Genre> getAllGenres();

}
