package fr.telecom_st_etienne.fx.series.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Saison {

	@Id // indique la clé primaire de la table correspondante
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	@ManyToOne // Engendre une clé étrangère
	private Serie serie;
	
	@OneToMany(mappedBy="saison")
	private List<Episode> episodes;
	
	public Saison() {
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

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}

	@Override
	public String toString() {
		return "Saison [id=" + id + ", nom=" + nom + ", serie=" + serie + "]";
	}
	
	
}
