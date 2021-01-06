package fr.telecom_st_etienne.fx.series.service;

import java.util.List;

import fr.telecom_st_etienne.fx.series.business.Saison;
import fr.telecom_st_etienne.fx.series.business.Serie;

public interface SaisonService {

	Saison ajouterSaison(String nom, Serie serie);

	List<Saison> recupererSaisons();
	
	boolean supprimerSaison(Long id);
}
