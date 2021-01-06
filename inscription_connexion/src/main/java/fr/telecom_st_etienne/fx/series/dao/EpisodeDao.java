package fr.telecom_st_etienne.fx.series.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.telecom_st_etienne.fx.series.business.Episode;
import fr.telecom_st_etienne.fx.series.business.Saison;
import fr.telecom_st_etienne.fx.series.business.Serie;

public interface EpisodeDao extends JpaRepository<Episode, Long> {

	List<Episode> findAllBySaison(Saison saison);
	
	List<Episode> findAllBySaisonSerie(Serie serie);
	
}
