package fr.telecom_st_etienne.fx.series.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.telecom_st_etienne.fx.series.business.Utilisateur;
import fr.telecom_st_etienne.fx.series.dao.UtilisateurDao;
import fr.telecom_st_etienne.fx.series.service.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Override
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
		return utilisateurDao.save(utilisateur);
	}

	@Override
	public List<Utilisateur> recupererUtilisateurs() {
		return utilisateurDao.findAll();
	}

	@Override
	public Utilisateur recupererUtilisateur(String email, String motDePasse) {
		return utilisateurDao.findByEmailAndMotDePasse(email, motDePasse);
	}

}
