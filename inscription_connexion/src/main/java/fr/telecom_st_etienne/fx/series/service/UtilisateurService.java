package fr.telecom_st_etienne.fx.series.service;

import java.util.List;

import fr.telecom_st_etienne.fx.series.business.Utilisateur;

public interface UtilisateurService {

	Utilisateur ajouterUtilisateur(Utilisateur utilisateur);

	List<Utilisateur> recupererUtilisateurs();

	Utilisateur recupererUtilisateur(String email, String motDePasse);

}
