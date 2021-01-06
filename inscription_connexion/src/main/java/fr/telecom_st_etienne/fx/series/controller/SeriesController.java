package fr.telecom_st_etienne.fx.series.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.telecom_st_etienne.fx.series.business.Serie;
import fr.telecom_st_etienne.fx.series.business.Utilisateur;
import fr.telecom_st_etienne.fx.series.service.SaisonService;
import fr.telecom_st_etienne.fx.series.service.SerieService;
import fr.telecom_st_etienne.fx.series.service.UtilisateurService;

@Controller
@RequestMapping("/")
public class SeriesController {

	private static final String FORMAT_DATE_AMERICAIN = "yyyy-MM-dd";

	private SerieService serieService;

	private SaisonService saisonService;

	private UtilisateurService utilisateurService;

	private HttpSession httpSession;

	public SeriesController(SerieService serieService, SaisonService saisonService,
			UtilisateurService utilisateurService, HttpSession httpSession) {
		super();
		this.serieService = serieService;
		this.saisonService = saisonService;
		this.utilisateurService = utilisateurService;
		this.httpSession = httpSession;
	}

	@GetMapping("/connexion")
	public ModelAndView connexionGet() {
		return new ModelAndView("connexion");
	}

	@GetMapping("/inscription")
	public ModelAndView inscriptionGet() {
		ModelAndView mav = new ModelAndView("inscription");
		mav.addObject("utilisateur", new Utilisateur());
		return mav;
	}

	@PostMapping("/inscription")
	public ModelAndView inscriptionPost(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView mav = inscriptionGet();
			mav.addObject("utilisateur", utilisateur);
			return mav;
		} else {
			utilisateur = utilisateurService.ajouterUtilisateur(utilisateur);
			ModelAndView mav = new ModelAndView("merciInscription");
			mav.addObject("utilisateur", utilisateur);
			return mav;
		}
	}

	@PostMapping("/connexion")
	public ModelAndView connexionPost(@RequestParam("EMAIL") String email,
			@RequestParam("MOT_DE_PASSE") String motDePasse) {
		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(email, motDePasse);
		if (utilisateur == null) {
			return new ModelAndView("connexion");
		} else {
			httpSession.setAttribute("utilisateur", utilisateur);
			return new ModelAndView("redirect:index");
		}
	}

	@GetMapping("/deconnexion")
	public ModelAndView deconnexion() {
		httpSession.invalidate();
		return new ModelAndView("redirect:index");
	}

	@RequestMapping(value = { "/index", "/" })
	public ModelAndView accueil() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("series", serieService.recupererSeries());
		return mav;
	}
	
	@GetMapping(value = "exportWord", produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
	public void getSerieAuFormatWord(HttpServletResponse response, @RequestParam("ID_SERIE") Long idSerie) throws IOException {
		response.addHeader("Content-Disposition", "attachment; filename=Serie_" + idSerie + ".docx");
		response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

		Serie serie = serieService.recupererSerie(idSerie);
		// Création du document Word
		XWPFDocument document = new XWPFDocument();

		// Entête
		XWPFHeader headerHeader = document.createHeader(HeaderFooterType.DEFAULT);
		XWPFParagraph headerPara = headerHeader.createParagraph();
		headerPara.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun header = headerPara.createRun();
		URL url = new URL("https://www.clelia.fr/images/Logo-Telecom.png");
		try {
			header.addPicture(url.openStream(), Document.PICTURE_TYPE_PNG, "IsoPUR4.png", Units.toEMU(200),
					Units.toEMU(70));
		} catch (InvalidFormatException | IOException e1) {
			e1.printStackTrace();
		}
		header.addBreak();
		header.setText("Applications Distribuées : " + serie.getNom());
		header.setColor("0000ff");
		header.setFontSize(24);
		header.setBold(true);
		
		// Téléchargement du document généré
		OutputStream outStream = response.getOutputStream();
		document.write(outStream);
		outStream.close();
		document.close();
	}

	@PostConstruct
	private void init() {
		System.out.println("Invocation de la méthode init()");
		if (serieService.recupererSeries().isEmpty()) {
			Serie serie1 = serieService.ajouterSerie("Umbrella Academy", 40f);
			Serie serie2 = serieService.ajouterSerie("Game of Thrones", 60f);
			saisonService.ajouterSaison("Saison 1", serie1);
			saisonService.ajouterSaison("Saison 1", serie2);
		}

		if (utilisateurService.recupererUtilisateurs().isEmpty()) {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setEmail("a@a");
			utilisateur.setMotDePasse("12345");
			utilisateur.setDescription("Voici ma bio");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2000);
			calendar.set(Calendar.MONTH, Calendar.DECEMBER);
			calendar.set(Calendar.DAY_OF_MONTH, 2);
			utilisateur.setDateDeNaissance(calendar.getTime());
			utilisateurService.ajouterUtilisateur(utilisateur);
		}

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_AMERICAIN);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
	}

}