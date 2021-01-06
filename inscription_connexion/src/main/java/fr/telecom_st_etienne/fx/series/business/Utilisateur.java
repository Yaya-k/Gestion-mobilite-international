package fr.telecom_st_etienne.fx.series.business;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class Utilisateur {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Merci de renseigner un email")
	@Column(unique=true)
	@Email(message="Merci de renseigner un email au bon format")
	private String email;
	
	@Size(min=5, message="Votre mot de passe doit contenir au moins 5 caractères")
	private String motDePasse;
	
	@Lob
	@NotEmpty(message="Parlez-nous de vous")
	private String description;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message="Merci de renseigner votre date de naissance")
	@Past(message="Votre date de naissance doit être dans le passé")
	private Date dateDeNaissance;
	
	private Date dateInscription;
	
	@ManyToMany
	//@NotEmpty(message="Merci de choisir au moins un intérêt")
	private List<Interet> interets;
	
	public Utilisateur() {
		dateInscription = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public List<Interet> getInterets() {
		return interets;
	}

	public void setInterets(List<Interet> interets) {
		this.interets = interets;
	}
	
}
