package fr.telecom_st_etienne.fx.series.business;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Serie {

	private static final long MINIMUM_PRICE = 10;
	private static final long MAXIMUM_PRICE = 100;
	
	@Id // indique la clé primaire de la table correspondante
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=100)
	private String nom;
	
	@Min(value=MINIMUM_PRICE, message="The price cannot be less than " + MINIMUM_PRICE + " euros")
	@Max(value=MAXIMUM_PRICE, message="The price cannot exceed " + MAXIMUM_PRICE + " euros")
	private float prixEnEuros;
	
	@OneToMany(mappedBy="serie", fetch = FetchType.EAGER)
	private List<Saison> saisons;
	
	public Serie() {
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

	public float getPrixEnEuros() {
		return prixEnEuros;
	}

	public void setPrixEnEuros(float prixEnEuros) {
		this.prixEnEuros = prixEnEuros;
	}

	public List<Saison> getSaisons() {
		return saisons;
	}

	public void setSaisons(List<Saison> saisons) {
		this.saisons = saisons;
	}

	@Override
	public String toString() {
		return "Serie [id=" + id + ", nom=" + nom + ", prixEnEuros=" + prixEnEuros + "]";
	}
	
	
}
