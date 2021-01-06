package fr.telecom_st_etienne.fx.series.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Episode {

	@Id // indique la clé primaire de la table correspondante
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	@ManyToOne
	@NotNull(message="Merci de sélectionner la saison")
	private Saison saison;

	private int dureeEnMinutes;
	
	public Episode() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Saison getSaison() {
		return saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}

	public int getDureeEnMinutes() {
		return dureeEnMinutes;
	}

	public void setDureeEnMinutes(int dureeEnMinutes) {
		this.dureeEnMinutes = dureeEnMinutes;
	}

	@Override
	public String toString() {
		return "Episode [id=" + id + ", nom=" + nom + ", saison=" + saison + ", dureeEnMinutes=" + dureeEnMinutes + "]";
	}
	
}
