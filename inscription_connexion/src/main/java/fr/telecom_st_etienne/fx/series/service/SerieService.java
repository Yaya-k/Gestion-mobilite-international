package fr.telecom_st_etienne.fx.series.service;

import java.util.List;

import fr.telecom_st_etienne.fx.series.business.Serie;

public interface SerieService {

	Serie ajouterSerie(String nom, float prixEnEuros);
	
	Serie recupererSerie(Long id);
	
	List<Serie> recupererSeries();

	boolean supprimerSerie(Long id);

}
