package fr.telecom_st_etienne.fx.series.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.telecom_st_etienne.fx.series.business.Serie;
import fr.telecom_st_etienne.fx.series.dao.SerieDao;
import fr.telecom_st_etienne.fx.series.service.SerieService;

@Service
public class SerieServiceImpl implements SerieService {

	// Important : on délègue la création de l'objet au framework Spring
	// Principe Hollywood : ne nous appelez pas, nous vous appelerons
	// Injection de dépendances
	@Autowired
	private SerieDao serieDao;
	
	@Override
	public Serie ajouterSerie(String nom, float prixEnEuros) {
		Serie serie = new Serie();
		serie.setNom(nom);
		serie.setPrixEnEuros(prixEnEuros);
		serieDao.save(serie);
		return serie;
	}

	@Override
	public Serie recupererSerie(Long id) {
		Optional<Serie> serie = serieDao.findById(id);
		
		if (serie.isPresent()) {
			return serie.get();
		}
		return null;
	}

	@Override
	public List<Serie> recupererSeries() {
		return serieDao.findAll();
	}

	@Override
	public boolean supprimerSerie(Long id) {
		Optional<Serie> serie = serieDao.findById(id);
		
		if (!serie.isPresent()) {
			return false;
		}
		serieDao.delete(serie.get());
		return true;
	}

}
