package fr.telecom_st_etienne.fx.series.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.telecom_st_etienne.fx.series.business.Achat;

public interface AchatDao extends JpaRepository<Achat, Long> {

}
