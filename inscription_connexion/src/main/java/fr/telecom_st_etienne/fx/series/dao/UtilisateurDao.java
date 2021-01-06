package fr.telecom_st_etienne.fx.series.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.telecom_st_etienne.fx.series.business.Utilisateur;

// JPA : Java Persitance API
// Repository : communicates with the database
public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {

	Utilisateur findByEmailAndMotDePasse(String email, String motDePasse);
	
}
