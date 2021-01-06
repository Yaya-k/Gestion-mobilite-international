package fr.telecom_st_etienne.fx.series.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.telecom_st_etienne.fx.series.business.Serie;

public interface SerieDao extends JpaRepository<Serie, Long> {

	List<Serie> findByNomContainingAndPrixEnEurosBetween(String filtre, float prixMin, float prixMax);
	
	List<Serie> findByPrixEnEurosBetween(float prixMin, float prixMax);
	
}
