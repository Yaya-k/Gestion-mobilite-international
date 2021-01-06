package fr.telecom_st_etienne.fx.series.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.telecom_st_etienne.fx.series.business.Serie;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class SerieServiceImplTest {

	@Autowired
	private SerieService serieService;
	
    @Test
    public void testerAjouterSerie() {
    	String nom = "Série de test";
    	float prixEnEuros = 10f;
        Serie serie = serieService.ajouterSerie(nom, prixEnEuros);
        assertNotNull(serie);
        assertNotNull(serie.getNom());
        assertEquals(serie.getNom(), nom);
        assertEquals(serie.getPrixEnEuros(), prixEnEuros);
    }

    @Test
    public void testerRecupererSeries() {
        List<Serie> series = serieService.recupererSeries();
        assertNotNull(series);
        assertTrue(!series.isEmpty());
    }

    @Test
    public void testerSupprimerSeries() {
        List<Serie> series = serieService.recupererSeries();
        Serie serie = series.get(series.size()-1);
        boolean resultat = serieService.supprimerSerie(serie.getId());
        assertTrue(resultat);
    }

}