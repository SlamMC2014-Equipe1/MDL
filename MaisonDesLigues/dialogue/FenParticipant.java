package dialogue;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//permet d �tablir le lien avec la classe GestionDemandes
import controle.GestionDemandes;
import entite.*;
public class FenParticipant extends JFrame {
	private static final long serialVersionUID = 1L;
	// propri�t� pour �tablir le lien avec la classe GestionDemandes
	private GestionDemandes gestionBD = new GestionDemandes();  
	private JPanel jContentPrincipal = null;
	private JPanel jContentChoixParticipant = null;
	private JPanel jContentIdentite = null;
	private JPanel jContentInscriptionIntervenant= null;
	private JPanel jContentInscriptionBenevole= null;
	private JPanel jContentInscriptionLicencie = null;
	private JPanel jContentNuit�e = null;
	private JPanel jContentHotel = null;
	private JButton btn_quitter = null;
	private GestAtelierList listeAtel;
	private ButtonGroup groupeboutons = new ButtonGroup(); 
	private JRadioButton radiobtn_Intervenant = null;
	private JRadioButton radiobtn_licencie = null;
	private JRadioButton radiobtn_Benevole = null;
	private ButtonGroup groupeRole = new ButtonGroup(); 
	private JRadioButton radiobtn_Animateur = null;
	private JRadioButton radiobtn_NonAnimateur = null;
	private ButtonGroup groupeOuiNon = new ButtonGroup(); 
	private JRadioButton radiobtn_Oui = null;
	private JRadioButton radiobtn_Non = null;
	private JLabel jLabel_nom = null;
	private JLabel jLabel_prenom = null;
	private JLabel jLabel_adresse = null;
	private JLabel jLabel_cp = null;
	private JLabel jLabel_ville = null;
	private JLabel jLabel_mail = null;
	private JLabel jLabel_clewifi = null;
	private JLabel jLabel_dtenais = null;
	private JLabel jLabel_dteins = null;
	private JLabel jLabel_dtearr = null;
	private JLabel jLabel_nolicence = null;
	private JLabel jLabel_idqualite = null;
	private JLabel jLabel_atelier = null;
	private JLabel jlabel_idnuit_un = null;
	private JLabel jlabel_idnuit_deux = null;
	private JButton btn_embaucher = null;
	private JButton btn_ok = null;
	private JButton btn_annuler = null;
	private JTextField txt_mat = null;
	private JTextField txt_nom = null;
	private JTextField txt_prenom = null;
	private JTextField txt_adr1 = null;
	private JTextField txt_adr2 = null;
	private JTextField txt_cp = null;
	private JTextField txt_ville = null;
	private JTextField txt_mail = null;
	private JTextField txt_dtenais = null;
	private JTextField txt_dteinsc = null;
	private JTextField txt_dtearr = null;
	private JTextField txt_nolicence = null;
	private JTextField txt_nolicence_B = null;
	private JComboBox<String> cbx_qualite = null;
	private JComboBox<String> cbx_qualite_B = null;	
	private JComboBox<String> cbx_atelier = null;
	private JComboBox<String> cbx_hotel_nuit1 = null;
	private JComboBox<String> cbx_hotel_nuit2 = null;
	private JComboBox<String> cbx_catchambre_nuit1 = null;
	private JCheckBox check_Nuitdu13 = null;
	private JCheckBox check_Nuitdu12 = null;
	private JTextField txt_clewifi = null;
	private JButton Btn_Rechercher = null;
	private boolean estRechercher=false;
	private JComboBox<String> cbx_catchambre_nuit2 = null;
	private void regroupeboutons()
	{
		groupeboutons.add(radiobtn_licencie);
		groupeboutons.add(radiobtn_Intervenant);
		groupeboutons.add(radiobtn_Benevole);
		groupeboutons.clearSelection(); // aucun bouton n'est s�lectionn�
		groupeRole.add(radiobtn_Animateur);
		groupeRole.add(radiobtn_NonAnimateur);
		groupeRole.clearSelection();
		groupeOuiNon.add(radiobtn_Oui);
		groupeOuiNon.add(radiobtn_Non);
		groupeOuiNon.clearSelection();
	}
	private void enregistrerintervenant(){
		int numatelier=Integer.parseInt(""+((String)cbx_atelier.getSelectedItem()).charAt(0));
		String Statut="I";

		if(!gestionBD.enregistrerIntervenant(txt_nom.getText().toUpperCase(),txt_prenom.getText(),txt_adr1.getText(), txt_adr2.getText(), txt_cp.getText(), txt_ville.getText(), txt_mail.getText(), Statut, numatelier))
			return;
		Integer IdParticipant = gestionBD.rechercherParticipantsurnom(txt_nom.getText()).getNumparticipant();
		if (getRadiobtn_AnimateurOui().isSelected())
		{
			// mise � jour de l'intervenant animateur dans la collection atelier
			listeAtel.elt(listeAtel.rechercher(numatelier)).SetNointervenant(IdParticipant);
		}
		int numordre = gestionBD.sp_maxnumordre(IdParticipant);
		
		if (getRadiobtn_HotelOui().isSelected())
		{
			if (getCheck_Nuitdu12().isSelected())
			{
				numordre = numordre + 1;
				int numhotel1=Integer.parseInt(""+((String)cbx_hotel_nuit1.getSelectedItem()).charAt(0));
				int categorie1=Integer.parseInt(""+((String)cbx_catchambre_nuit1.getSelectedItem()).charAt(0));
				// enregistrement de la nuit d'hotel
				gestionBD.enregistrerDetailHebergement(IdParticipant, numordre, numhotel1, categorie1, 1);
			}
			if (getCheck_Nuitdu13().isSelected())
			{
				numordre = numordre + 1;
				int numhotel2=Integer.parseInt(""+((String)cbx_hotel_nuit2.getSelectedItem()).charAt(0));
				int categorie2=Integer.parseInt(""+((String)cbx_catchambre_nuit2.getSelectedItem()).charAt(0));
				// enregistrement de la nuit d'hotel
				gestionBD.enregistrerDetailHebergement(IdParticipant, numordre, numhotel2, categorie2, 1);
			}
		}
		JOptionPane.showMessageDialog(null, "Inscription Intervenant effectu�e.",
				"", JOptionPane.ERROR_MESSAGE);
	}
	private void enregistrerlicencie(){
		String Statut="L";
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String madteinsc = txt_dteinsc.getText();
		Date dteins = null;
		try {
			dteins= new Date(formater.parse(madteinsc).getTime());
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null,"La date d'inscription n'est pas valide (format dd/MM/yyyy)" 
					,"Format de la date d'inscription incorrecte",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String madtearr = txt_dtearr.getText();
		Date dtearr;
		try {
			dtearr= new Date(formater.parse(madtearr).getTime());
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null,"La date d'arriv�e n'est pas valide (format dd/MM/yyyy)" 
					,"Format de la date d'arriv�e incorrecte",JOptionPane.ERROR_MESSAGE);
			return;
		}
		//float txinteret = Float.valueOf(txt_clewifi.getText()).floatValue();
		int idqualite=Integer.parseInt(""+((String)cbx_qualite.getSelectedItem()).charAt(0));
 	 	if(!gestionBD.enregistrerLicencie(txt_nom.getText(),txt_prenom.getText(),txt_adr1.getText(), txt_adr2.getText(), txt_cp.getText(), txt_ville.getText(), txt_mail.getText(),Statut, dteins,dtearr,txt_clewifi.getText(), txt_nolicence.getText(), idqualite))
		return;
	}
	private void enregistrerbenevole(){
		String Statut="B";
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String madtenais = txt_dtenais.getText();
		Date dtenais = null;
		try {
			dtenais= new Date(formater.parse(madtenais).getTime());
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null,"La date de naissance n'est pas valide (format dd/MM/yyyy)" 
					,"Format de la date de naissance incorrecte",JOptionPane.ERROR_MESSAGE);
			return;
		}
	 	if(!gestionBD.enregistrerBenevole(txt_nom.getText(),txt_prenom.getText(),txt_adr1.getText(), txt_adr2.getText(), txt_cp.getText(), txt_ville.getText(), txt_mail.getText(),Statut, txt_nolicence_B.getText(), dtenais))
		return;
	}
	private void raz()
	{
		txt_nom.setText("");
		txt_prenom.setText("");
		txt_adr1.setText(" ");
		txt_adr2.setText(" ");
		txt_cp.setText(" ");
		txt_ville.setText(" ");
		txt_mail.setText(" ");
		txt_dtenais.setText("__/__/____");
		txt_clewifi.setText("");
		txt_nolicence.setText(" ");
		java.util.Date maDate=new java.util.Date();
		SimpleDateFormat dateStandard = new SimpleDateFormat("dd/MM/yyyy");
		txt_dteinsc.setText(dateStandard.format(maDate));
		txt_dtearr.setText(dateStandard.format(maDate));
		btn_embaucher.setEnabled(true);
		Btn_Rechercher.setEnabled(true);
		btn_ok.setVisible(false);
		btn_annuler.setVisible(false);
		estRechercher=false;
		regroupeboutons();
		
	}
	private Boolean verification(){
		/* cas d'insertion dans la base de donn�es */
		if ((!getRadiobtn_Benevole().isSelected())&&(!getRadiobtn_licencie().isSelected())&&(!getRadiobtn_Intervenant().isSelected())){
			JOptionPane.showMessageDialog(null,"Vous devez choisir un type de participant" 
					,"",JOptionPane.ERROR_MESSAGE);
			return(false);
		}
		if (txt_nom.getText().equals("")||txt_prenom.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Vous devez au minimum saisir un nom et un prenom" 
					,"",JOptionPane.ERROR_MESSAGE);
			return(false);
		}
		if (getRadiobtn_HotelOui().isSelected()&&(!((getCheck_Nuitdu12().isSelected())||(getCheck_Nuitdu13().isSelected())))){
					JOptionPane.showMessageDialog(null,"Vous devez obligatoirement choisir la(les) nuit(�es)" 
					,"",JOptionPane.ERROR_MESSAGE);
			return(false);
		}
		return(true);
	}
	/*** This is the default constructor */
	public FenParticipant() {
		super();
		initialize();
	}
	/** This method initializes this @return void */
	private void initialize() {
		this.setSize(810, 750);
		this.setContentPane(getJContentPrincipal());
		this.setTitle("Maison des Ligues : Inscription ");
	}
	/*** This method initializes jContentPane @return javax.swing.JPanel */
	private JPanel getJContentPrincipal() {
		if (jContentPrincipal == null) {
			// mise en forme de la fenetre
			jContentPrincipal = new JPanel();
			jContentPrincipal.setLayout(null);
			jContentPrincipal.add(getjContentChoixParticipant(), null);
			jContentPrincipal.add(getjContentIdentite(), null);
			jContentPrincipal.add(getjContentInscriptionIntervenant(), null);
			jContentPrincipal.add(getjContentInscriptionBenevole(), null);
			jContentPrincipal.add(getjContentInscriptionLicencie(), null);
			jContentPrincipal.add(getBtn_quitter(), null);
			jContentPrincipal.add(getBtn_embaucher(), null);
			jContentPrincipal.add(getBtn_ok(), null);
			jContentPrincipal.add(getBtn_annuler(), null);
			jContentPrincipal.add(getBtn_Rechercher(), null);
			regroupeboutons();
			btn_embaucher.setEnabled(false);
			Btn_Rechercher.setEnabled(false);
			btn_ok.setVisible(true);
			btn_annuler.setVisible(true);
			jContentInscriptionIntervenant.setVisible(false);
			jContentInscriptionBenevole.setVisible(false);
			jContentInscriptionLicencie.setVisible(false);

		}
		return jContentPrincipal;
	}
	private JPanel getjContentIdentite() {
		if (jContentIdentite == null) {
			jContentIdentite = new JPanel();
			jContentIdentite.setLayout(null);
			jContentIdentite.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Identit� du Participant ");
			jContentIdentite.setBorder(Bord);
			jContentIdentite.setBounds(new Rectangle(1, 60, 750, 220));
			jLabel_nom = new JLabel();
			jLabel_nom.setBounds(new Rectangle(10, 20, 100, 30));
			jLabel_nom.setText("Nom ");
			jLabel_prenom = new JLabel();
			jLabel_prenom.setBounds(new Rectangle(350,20, 50, 30));
			jLabel_prenom.setText("Pr�nom ");
			jLabel_prenom.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel_adresse = new JLabel();
			jLabel_adresse.setBounds(new Rectangle(10,60, 100, 30));
			jLabel_adresse.setText("Adresse ");
			jLabel_cp = new JLabel();
			jLabel_cp.setBounds(new Rectangle(10, 140, 100, 30));
			jLabel_cp.setText("Code Postal ");
			jLabel_ville = new JLabel();
			jLabel_ville.setBounds(new Rectangle(170, 140, 100, 30));
			jLabel_ville.setText("Ville ");
			jLabel_ville.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel_mail = new JLabel();
			jLabel_mail.setBounds(new Rectangle(10, 180, 100, 30));
			jLabel_mail.setText("Adresse Mail ");
			jContentIdentite.add(jLabel_nom, null);
			jContentIdentite.add(jLabel_prenom, null);
			jContentIdentite.add(jLabel_adresse, null);
			jContentIdentite.add(jLabel_cp, null);
			jContentIdentite.add(jLabel_ville, null);
			jContentIdentite.add(jLabel_mail, null);
			jContentIdentite.add(getTxt_nom(), null);
			jContentIdentite.add(getTxt_prenom(), null);
			jContentIdentite.add(getTxt_adr1(), null);
			jContentIdentite.add(getTxt_adr2(), null);
			jContentIdentite.add(getTxt_ville(), null);
			jContentIdentite.add(getTxt_cp(), null);
			jContentIdentite.add(getTxt_mail(), null);
		}
		return jContentIdentite;
	}
	private JPanel getjContentInscriptionLicencie() {
		if (jContentInscriptionLicencie == null) {
			jContentInscriptionLicencie = new JPanel();
			jContentInscriptionLicencie.setLayout(null);
			jContentInscriptionLicencie.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Compl�ment sur l'inscription du Licenci� ");
			jContentInscriptionLicencie.setBorder(Bord);
			jContentInscriptionLicencie.setBounds(new Rectangle(1, 280, 750, 270));
			jLabel_nolicence = new JLabel();
			jLabel_nolicence.setBounds(new Rectangle(5, 20, 100, 30));
			jLabel_nolicence.setText("Numero licence ");
			jLabel_idqualite = new JLabel();
			jLabel_idqualite.setBounds(new Rectangle(250, 20, 75, 30));
			jLabel_idqualite.setText("Fonction ");
			jLabel_dteins = new JLabel();
			jLabel_dteins.setBounds(new Rectangle(5, 60, 100, 30));
			jLabel_dteins.setText("Date Inscription");
			jLabel_dtearr = new JLabel();
			jLabel_dtearr.setBounds(new Rectangle(300, 60, 100, 30));
			jLabel_dtearr.setText("Date Arriv�e ");
			jLabel_clewifi = new JLabel();
			jLabel_clewifi.setBounds(new Rectangle(550, 60, 50, 30));
			jLabel_clewifi.setText("Cl� Wifi ");
			jContentInscriptionLicencie.add(jLabel_dteins, null);
			jContentInscriptionLicencie.add(jLabel_idqualite, null);
			jContentInscriptionLicencie.add(jLabel_nolicence, null);
			jContentInscriptionLicencie.add(jLabel_clewifi, null);
			jContentInscriptionLicencie.add(jLabel_dtearr, null);
			jContentInscriptionLicencie.add(getTxt_clewifi(), null);
			jContentInscriptionLicencie.add(getTxt_nolicence(), null);
			jContentInscriptionLicencie.add(getTxt_dteinsc(), null);
			jContentInscriptionLicencie.add(getTxt_dtearr(), null);
			jContentInscriptionLicencie.add(getCbx_qualite(), null);
			jContentInscriptionIntervenant.add(getjContentNuit�e(),null);
	
		}
		return jContentInscriptionLicencie;
	}
	private JPanel getjContentInscriptionBenevole() {
		if (jContentInscriptionBenevole == null) {
			jContentInscriptionBenevole = new JPanel();
			jContentInscriptionBenevole.setLayout(null);
			jContentInscriptionBenevole.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Compl�ment sur l'inscription du B�n�vole ");
			jContentInscriptionBenevole.setBorder(Bord);
			jContentInscriptionBenevole.setBounds(new Rectangle(1, 280, 750, 270));
			jLabel_idqualite = new JLabel();
			jLabel_idqualite.setBounds(new Rectangle(250, 20, 75, 30));
			jLabel_idqualite.setText("Fonction ");
			jLabel_nolicence = new JLabel();
			jLabel_nolicence.setBounds(new Rectangle(5, 20, 100, 30));
			jLabel_nolicence.setText("Numero licence ");
			jLabel_dtenais = new JLabel();
			jLabel_dtenais.setBounds(new Rectangle(5, 60, 110, 30));
			jLabel_dtenais.setText("Date de Naissance");
			jContentInscriptionBenevole.add(jLabel_idqualite, null);
			jContentInscriptionBenevole.add(jLabel_nolicence, null);
			jContentInscriptionBenevole.add(jLabel_dtenais, null);
			jContentInscriptionBenevole.add(getTxt_nolicence_B(), null);
			jContentInscriptionBenevole.add(getTxt_dtenais(), null);
			jContentInscriptionBenevole.add(getCbx_qualite_B(), null);
		}
		return jContentInscriptionBenevole;
	}
	private JPanel getjContentInscriptionIntervenant() {
		if (jContentInscriptionIntervenant == null) {
			jContentInscriptionIntervenant = new JPanel();
			jContentInscriptionIntervenant.setLayout(null);
			jContentInscriptionIntervenant.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Compl�ment sur l'inscription de l'intervenant ");
			jContentInscriptionIntervenant.setBorder(Bord);
			jContentInscriptionIntervenant.setBounds(new Rectangle(1, 280, 750, 270));
			jLabel_atelier = new JLabel();
			jLabel_atelier.setBounds(new Rectangle(5, 20, 100, 30));
			jLabel_atelier.setText("Choix de l'atelier ");
			jContentInscriptionIntervenant.add(getRadiobtn_AnimateurOui(), null);
			jContentInscriptionIntervenant.add(getRadiobtn_AnimateurNon(), null);
			jContentInscriptionIntervenant.add(jLabel_atelier, null);
			jContentInscriptionIntervenant.add(getcbx_atelier(), null);
			jContentInscriptionIntervenant.add(getjContentNuit�e(),null);
		}
		return jContentInscriptionIntervenant;
	}
	private JPanel getjContentNuit�e() {
		if (jContentNuit�e == null) {
			jContentNuit�e = new JPanel();
			jContentNuit�e.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Nuit�e(s)");
			jContentNuit�e.setBorder(Bord);
			jContentNuit�e.setBounds(new Rectangle(10, 100, 700, 150));
			jContentNuit�e.add(getRadiobtn_HotelOui(), null);
			jContentNuit�e.add(getRadiobtn_HotelNon(), null);
			jContentNuit�e.add(getjContentHotel(), null);
			jContentHotel.setVisible(false);
		}
		return jContentNuit�e;
	}
	
	private JPanel getjContentHotel() {
		if (jContentHotel == null) {
			jContentHotel = new JPanel();
			jContentHotel.setLayout(null);
			jContentHotel.setBounds(new Rectangle(10, 40, 680, 100));
			jlabel_idnuit_un = new JLabel();
			jlabel_idnuit_un.setBounds(new Rectangle(30, 30,250, 30));
			jlabel_idnuit_un.setText("Nuit du Vendredi 12 Septembre 2014 ");
			jlabel_idnuit_deux = new JLabel();
			jlabel_idnuit_deux.setBounds(new Rectangle(30, 70, 250, 30));
			jlabel_idnuit_deux.setText("Nuit du Samedi 13 Septembre 2014 ");
			jContentHotel.add(jlabel_idnuit_un, null);
			jContentHotel.add(jlabel_idnuit_deux, null);
			jContentHotel.add(getCheck_Nuitdu12(), null);
			jContentHotel.add(getCheck_Nuitdu13(), null);
			jContentHotel.add(getcbx_hotel_nuit2(), null);
			jContentHotel.add(getcbx_catchambre_nuit2(), null);
			jContentHotel.add(getcbx_hotel_nuit1(), null);
			jContentHotel.add(getcbx_catchambre_nuit1(), null);
			
		}
		return jContentHotel;
	}
	private JPanel getjContentChoixParticipant() {
		if (jContentChoixParticipant == null) {
			jContentChoixParticipant = new JPanel();
			jContentChoixParticipant.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Type de Participant ");
			jContentChoixParticipant.setBorder(Bord);
			jContentChoixParticipant.setBounds(new Rectangle(1, 1, 500, 59));
			jContentChoixParticipant.add(getRadiobtn_Intervenant(), null);
			jContentChoixParticipant.add(getRadiobtn_licencie(), null);
			jContentChoixParticipant.add(getRadiobtn_Benevole(), null);
		}
		return jContentChoixParticipant;
	}
	/*** This method initializes btn_quitter@return javax.swing.JButton	 */
	private JButton getBtn_quitter() {
		if (btn_quitter == null) {
			btn_quitter = new JButton();
			btn_quitter.setBounds(new Rectangle(451, 600, 110, 40));
			btn_quitter.setText("Quitter");
			btn_quitter.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					// Recopie des collections dans la base de donn�es
					if (gestionBD.majAtelier(listeAtel))
					{	
						System.out.println("mise a jour de la base effectu�e");
					}
					FenParticipant.this.dispose() ;
	//				controle.ControleConnexion.getControleConnexion().fermetureSession();
					System.exit(0);

				}
			});
		}
		return btn_quitter;
	}
	/*** This method initializes radiobtn_Intervenant @return javax.swing.JRadioButton*/
	private JRadioButton getRadiobtn_Benevole() {
		if (radiobtn_Benevole == null) {
			radiobtn_Benevole = new JRadioButton();
			radiobtn_Benevole.setBounds(new Rectangle(200,20, 102, 30));
			radiobtn_Benevole.setText("B�n�vole");
			radiobtn_Benevole.setVisible(true);
			radiobtn_Benevole.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jContentInscriptionIntervenant.setVisible(false);
				jContentInscriptionBenevole.setVisible(true);
				jContentInscriptionLicencie.setVisible(false);
			}
			});
		}
		return radiobtn_Benevole;
	}
	private JRadioButton getRadiobtn_HotelOui() {
		if (radiobtn_Oui == null) {
			radiobtn_Oui = new JRadioButton();
			radiobtn_Oui.setBounds(new Rectangle(10, 20, 80, 30));
			radiobtn_Oui.setText("Oui");
			radiobtn_Oui.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jContentHotel.setVisible(true);
				}
			});
		}
		return radiobtn_Oui;
	}
	private JRadioButton getRadiobtn_HotelNon() {
		if (radiobtn_Non== null) {
			radiobtn_Non = new JRadioButton();
			radiobtn_Non.setBounds(new Rectangle(100, 20, 100, 30));
			radiobtn_Non.setText("Non");
			radiobtn_Non.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jContentHotel.setVisible(false);
				}
			});
		}
		return radiobtn_Non;
	}
	private JRadioButton getRadiobtn_AnimateurOui() {
		if (radiobtn_Animateur == null) {
			radiobtn_Animateur = new JRadioButton();
			radiobtn_Animateur.setBounds(new Rectangle(460, 20, 100, 30));
			radiobtn_Animateur.setText("Animateur");
			radiobtn_Animateur.setVisible(true);
			radiobtn_Animateur.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				}
			});
		}
		return radiobtn_Animateur;
	}
	private JRadioButton getRadiobtn_AnimateurNon() {
		if (radiobtn_NonAnimateur == null) {
			radiobtn_NonAnimateur = new JRadioButton();
			radiobtn_NonAnimateur.setBounds(new Rectangle(460, 45, 100, 30));
			radiobtn_NonAnimateur.setText("Intervenant");
			radiobtn_NonAnimateur.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
					
				}
			});
		}
		return radiobtn_NonAnimateur;
	}
	private JRadioButton getRadiobtn_Intervenant() {
		if (radiobtn_Intervenant == null) {
			radiobtn_Intervenant = new JRadioButton();
			radiobtn_Intervenant.setBounds(new Rectangle(50,20, 100, 30));
			radiobtn_Intervenant.setText("Intervenant");
			radiobtn_Intervenant.setVisible(true);
			radiobtn_Intervenant.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jContentInscriptionIntervenant.setVisible(true);
					jContentInscriptionBenevole.setVisible(false);
					jContentInscriptionLicencie.setVisible(false);
				}
			});
		}
		return radiobtn_Intervenant;
	}
	private JRadioButton getRadiobtn_licencie() {
		if (radiobtn_licencie == null) {
			radiobtn_licencie = new JRadioButton();
			radiobtn_licencie.setBounds(new Rectangle(350,20, 100, 30));
			radiobtn_licencie.setText("licencie");
			radiobtn_licencie.setVisible(true);
			radiobtn_licencie.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jContentInscriptionIntervenant.setVisible(false);
					jContentInscriptionBenevole.setVisible(false);
					jContentInscriptionLicencie.setVisible(true);
				}
			});
		}
		return radiobtn_licencie;
	}
	/*** This method initializes btn_embaucher	@return javax.swing.JButton	 */
	private JButton getBtn_embaucher() {
		if (btn_embaucher == null) {
			btn_embaucher = new JButton();
			btn_embaucher.setBounds(new Rectangle(350, 600, 110, 40));
			btn_embaucher.setText("Etre Benevole");
			btn_embaucher.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					regroupeboutons();
					btn_embaucher.setEnabled(false);
					Btn_Rechercher.setEnabled(false);
					btn_ok.setVisible(true);
					btn_annuler.setVisible(true);
		
				}
			});
		}
		return btn_embaucher;
	}
	/*** This method initializes btn_ok	@return javax.swing.JButton	*/
	private JButton getBtn_ok() {
		if (btn_ok == null) {
			btn_ok = new JButton();
			btn_ok.setBounds(new Rectangle(250, 600, 110, 40));
			btn_ok.setText("Enregistrer");
			btn_ok.setVisible(false);
			btn_ok.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
				if(!estRechercher)
				{
					if (verification())
					{
					/* cas d'un intervenant � traiter */
						if(radiobtn_Intervenant.isSelected())
						{
						enregistrerintervenant();
						}
						else
						{
						/* cas d'un b�n�vole � traiter */
							if (radiobtn_Benevole.isSelected())
							{
								enregistrerbenevole();
							}
							else
							{
								enregistrerlicencie();
							}
						}
						raz();
						btn_embaucher.setEnabled(false);
						Btn_Rechercher.setEnabled(false);
						btn_ok.setVisible(true);
						btn_annuler.setVisible(true);
						jContentInscriptionIntervenant.setVisible(false);
						jContentInscriptionBenevole.setVisible(false);
						jContentInscriptionLicencie.setVisible(false);
					}
				}
				else
				{
					/* cas de recherche d'une personne dans la base de donn�es */
					Participant retourParticipant=gestionBD.rechercherParticipant(txt_mat.getText());
					if(retourParticipant==null){
						JOptionPane.showMessageDialog(null,"Pas de personne (intervenant, licenci� ou b�n�vole) avec le matricule "+txt_mat.getText() 
								,"Erreur de matricule",JOptionPane.ERROR_MESSAGE);
						return;
					}
					// Affichage
					txt_nom.setText(retourParticipant.getNomparticipant());
					txt_prenom.setText(retourParticipant.getPrenomparticipant());
					txt_adr1.setText(retourParticipant.getAdress1participant());
					txt_adr1.setText(retourParticipant.getAdress2participant());
					txt_cp.setText(retourParticipant.getCpparticipant());
					txt_ville.setText(retourParticipant.getVilleparticipant());
					txt_mail.setText(retourParticipant.getMailparticipant());
					radiobtn_licencie.setVisible(true);
					radiobtn_Intervenant.setVisible(true);
					radiobtn_Benevole.setVisible(true);
					if (retourParticipant.getStatutparticipant().equals("L"))
					{
						Licencie unlicencie = (Licencie)retourParticipant;
						SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
						String dateFormatee = formatDateJour.format(unlicencie.getDateinscparticipant()); 
						txt_dteinsc.setText(dateFormatee);
						dateFormatee = formatDateJour.format(unlicencie.getDateenrparticipant()); 
						txt_dtearr.setText(dateFormatee);
						txt_clewifi.setText(unlicencie.getClewifiparticipant());
						cbx_atelier.setSelectedIndex(unlicencie.getIdqualitelicencie());
						radiobtn_licencie.setEnabled(true);							
					}
					else 
					{
						if (retourParticipant.getStatutparticipant().equals("B"))
						{
							Benevole unbenevole = (Benevole)retourParticipant;
							txt_nolicence.setText(unbenevole.getNolicencebenevole());
							SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
							String dateFormatee = formatDateJour.format(unbenevole.getDatenaissbenevole()); 
							txt_dtenais.setText(dateFormatee);
							radiobtn_Benevole.setEnabled(true);	
						}
						else
						{
							Intervenant unIntervenant = (Intervenant)retourParticipant;
							cbx_atelier.setSelectedIndex(unIntervenant.getIdatelierintervenant());
							radiobtn_Intervenant.setEnabled(true);
						}
					}
				}
				}
			});
		}
		return btn_ok;
	}
	/*** This method initializes btn_annuler @return javax.swing.JButton */
	private JButton getBtn_annuler() {
		if (btn_annuler == null) {
			btn_annuler = new JButton();
			btn_annuler.setBounds(new Rectangle(150, 600, 110, 40));
			btn_annuler.setText("Annuler");
			btn_annuler.setVisible(false);
			btn_annuler.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					raz();
					jContentInscriptionBenevole.setVisible(false);
					jContentInscriptionIntervenant.setVisible(false);
					jContentInscriptionLicencie.setVisible(false);
				}
			});
		}
		return btn_annuler;
	}
	/*** This method initializes txt_nom @return javax.swing.JTextField	 */
	private JTextField getTxt_nom() {
		if (txt_nom == null) {
			txt_nom = new JTextField();
			txt_nom.setBounds(new Rectangle(90, 20, 250, 30));
			//	txt_nom.addKeyListener(new java.awt.event.KeyAdapter() {
			//	public void keyPressed(java.awt.event.KeyEvent e) {
			//		if (e.getKeyCode() == 10)
			//		{ txt_mat.setText(txt_nom.getText().substring(0, 2));}
			//	}
			//	});
		}
		return txt_nom;
	}
	/*** This method initializes txt_prenom	@return javax.swing.JTextField */
	private JTextField getTxt_prenom() {
		if (txt_prenom == null) {
			txt_prenom = new JTextField();
			txt_prenom.setBounds(new Rectangle(423, 20, 267, 30));
		}
		return txt_prenom;
	}
	private JTextField getTxt_adr1() {
		if (txt_adr1 == null) {
			txt_adr1 = new JTextField();
			txt_adr1.setBounds(new Rectangle(90, 60, 600, 30));
		}
		return txt_adr1;
	}
	private JTextField getTxt_adr2() {
		if (txt_adr2 == null) {
			txt_adr2 = new JTextField();
			txt_adr2.setBounds(new Rectangle(90, 100, 600, 30));
		}
		return txt_adr2;
	}
	private JTextField getTxt_cp() {
		if (txt_cp == null) {
			txt_cp = new JTextField();
			txt_cp.setBounds(new Rectangle(90, 140, 50, 30));
		}
		return txt_cp;
	}
	private JTextField getTxt_ville() {
		if (txt_ville == null) {
			txt_ville = new JTextField();
			txt_ville.setBounds(new Rectangle(300, 140, 200, 30));
		}
		return txt_ville;
	}
	private JTextField getTxt_mail() {
		if (txt_mail == null) {
			txt_mail = new JTextField();
			txt_mail.setBounds(new Rectangle(90, 180, 410, 30));
			txt_mail.setText(txt_prenom.getText()+"."+txt_nom.getText()+"@mail.fr");
		}
		return txt_mail;
	}

	private JCheckBox getCheck_Nuitdu12() {
		if (check_Nuitdu12 == null) {
			check_Nuitdu12 = new JCheckBox();
			check_Nuitdu12.setBounds(new Rectangle(10, 33, 21, 21));
			check_Nuitdu12.setVisible(true);
		}
		check_Nuitdu12.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent e) {
				if (e.getStateChange()== ItemEvent.SELECTED)
				{
					
				}
			}
		});
		return check_Nuitdu12;
	}
	private JCheckBox getCheck_Nuitdu13() {
		if (check_Nuitdu13 == null) {
			check_Nuitdu13 = new JCheckBox();
			check_Nuitdu13.setBounds(new Rectangle(10, 73, 21, 21));
			check_Nuitdu13.setVisible(true);
		}
		return check_Nuitdu13;
	}
	/*** This method initializes txt_clewifi @return javax.swing.JTextField	*/
	private JTextField getTxt_clewifi() {
		if (txt_clewifi == null) {
			txt_clewifi = new JTextField();
			txt_clewifi.setBounds(new Rectangle(620, 60, 110, 30));
		}
		return txt_clewifi;
		
	}
	/*** This method initializes txt_dteinsc @return javax.swing.JTextField	*/
	private JTextField getTxt_dteinsc() {
		if (txt_dteinsc == null) {
			txt_dteinsc = new JTextField();
			txt_dteinsc.setBounds(new Rectangle(130, 60, 110, 30));
			java.util.Date maDate=new java.util.Date();
			SimpleDateFormat dateStandard = new SimpleDateFormat("dd/MM/yyyy");
			//Utilisation de la methode FORMAT
			txt_dteinsc.setText(dateStandard.format(maDate));
		}
		return txt_dteinsc;
	}
	private JTextField getTxt_dtearr() {
		if (txt_dtearr == null) {
			txt_dtearr = new JTextField();
			txt_dtearr.setBounds(new Rectangle(450, 60, 100, 30));
			java.util.Date maDate=new java.util.Date();
			SimpleDateFormat dateStandard = new SimpleDateFormat("dd/MM/yyyy");
			txt_dtearr.setText(dateStandard.format(maDate));
		}
		return txt_dtearr;
	}
	/*** This method initializes txt_idqual @return javax.swing.JTextField */
	private JComboBox<String> getCbx_qualite() {
		if (cbx_qualite == null) {
			cbx_qualite = new JComboBox<String>();
			cbx_qualite.setBounds(new Rectangle(370, 20, 310, 30));
			GestQualiteList listeQualite=gestionBD.chargeQualite();
			int taille = listeQualite.Nbelement();
			cbx_qualite.setMaximumRowCount(taille);
			for (int ind = 0 ; ind < taille; ind ++ )
			{
				cbx_qualite.addItem(listeQualite.elt(ind).getIdqualite()+ " " +listeQualite.elt(ind).getLibellequalite());
			}
			cbx_qualite.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// permet de r�cup�rer le num atelier
					System.out.println(((String)cbx_qualite.getSelectedItem()).charAt(0));
				}
			});
		}
		return cbx_qualite;
	}

	/*** This method initializes cbx_atelier	@return javax.swing.JComboBox */
	private JComboBox<String> getcbx_atelier() {
		if (cbx_atelier == null) {
			cbx_atelier = new JComboBox<String>();
			cbx_atelier.setBounds(new Rectangle(120, 20, 310, 30));
			listeAtel=gestionBD.chargeAtelier();
			int taille = listeAtel.Nbelement();
			cbx_atelier.setMaximumRowCount(taille);
			for (int ind = 0 ; ind < taille; ind ++ )
			{
				cbx_atelier.addItem(listeAtel.elt(ind).getNoatelier()+ " " +listeAtel.elt(ind).getLibelleatelier());
			}
			cbx_atelier.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// permet de r�cup�rer le num atelier
					System.out.println(((String)cbx_atelier.getSelectedItem()).charAt(0));
				}
			});
		}
		return cbx_atelier;
	}
	private JComboBox<String> getcbx_hotel_nuit2() {
		if (cbx_hotel_nuit2 == null) {
			cbx_hotel_nuit2 = new JComboBox<String>();
			cbx_hotel_nuit2.setBounds(new Rectangle(250, 70, 300, 30));
			GestHotelList listeHotel=gestionBD.chargeHotel();
			int taille = listeHotel.Nbelement();
			cbx_hotel_nuit2.setMaximumRowCount(taille);
			for (int ind = 0 ; ind < taille; ind ++ )
			{
				cbx_hotel_nuit2.addItem(listeHotel.elt(ind).getCodehotel()+ " " +listeHotel.elt(ind).getNomhotel()+ " "+listeHotel.elt(ind).getAdress1hotel() );
			}
			cbx_hotel_nuit2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(((String)cbx_hotel_nuit2.getSelectedItem()).charAt(0));
				}
			});
		}
		return cbx_hotel_nuit2;
	}
	private JComboBox<String> getcbx_hotel_nuit1() {
		if (cbx_hotel_nuit1 == null) {
			cbx_hotel_nuit1 = new JComboBox<String>();
			cbx_hotel_nuit1.setBounds(new Rectangle(250, 30, 300, 30));
			GestHotelList listeHotel=gestionBD.chargeHotel();
			int taille = listeHotel.Nbelement();
			cbx_hotel_nuit1.setMaximumRowCount(taille);
			for (int ind = 0 ; ind < taille; ind ++ )
			{
				cbx_hotel_nuit1.addItem(listeHotel.elt(ind).getCodehotel()+ " " +listeHotel.elt(ind).getNomhotel()+ " "+listeHotel.elt(ind).getAdress1hotel() );
			}
			}
		return cbx_hotel_nuit1;
	}
	private JComboBox<String> getcbx_catchambre_nuit2() {
		if (cbx_catchambre_nuit2 == null) {
			cbx_catchambre_nuit2 = new JComboBox<String>();
			cbx_catchambre_nuit2.setBounds(new Rectangle(560, 70, 100, 30));
			GestCategorieList listeChambre=gestionBD.chargeCategorie();
			int taille = listeChambre.Nbelement();
			cbx_catchambre_nuit2.setMaximumRowCount(taille);
			for (int ind = 0 ; ind < taille; ind ++ )
			{
				cbx_catchambre_nuit2.addItem(listeChambre.elt(ind).getIdcategorie()+ " " +listeChambre.elt(ind).getLibellecategorie());
			}
			cbx_catchambre_nuit2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(((String)cbx_catchambre_nuit2.getSelectedItem()).charAt(0));
				}
			});
		}
		return cbx_catchambre_nuit2;
	}
	private JComboBox<String> getcbx_catchambre_nuit1() {
		if (cbx_catchambre_nuit1 == null) {
			cbx_catchambre_nuit1 = new JComboBox<String>();
			cbx_catchambre_nuit1.setBounds(new Rectangle(560, 30, 100, 30));
			GestCategorieList listeChambre=gestionBD.chargeCategorie();
			int taille = listeChambre.Nbelement();
			cbx_catchambre_nuit1.setMaximumRowCount(taille);
			for (int ind = 0 ; ind < taille; ind ++ )
			{
				cbx_catchambre_nuit1.addItem(listeChambre.elt(ind).getIdcategorie()+ " " +listeChambre.elt(ind).getLibellecategorie());
			}
			cbx_catchambre_nuit1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(((String)cbx_catchambre_nuit1.getSelectedItem()).charAt(0));
				}
			});
		}
		return cbx_catchambre_nuit1;
	}
	private JTextField getTxt_nolicence() {
		if (txt_nolicence == null) {
			txt_nolicence = new JTextField();
			txt_nolicence.setBounds(new Rectangle(110, 20, 110, 30));
		}
		return txt_nolicence;
	}
	private JTextField getTxt_nolicence_B() {
		if (txt_nolicence_B == null) {
			txt_nolicence_B = new JTextField();
			txt_nolicence_B.setBounds(new Rectangle(110, 20, 110, 30));
		}
		return txt_nolicence_B;
	}

	/*** This method initializes txt_dtenais @return javax.swing.JTextField	*/
	private JTextField getTxt_dtenais() {
		if (txt_dtenais == null) {
			txt_dtenais = new JTextField();
			txt_dtenais.setBounds(new Rectangle(130, 60, 110, 30));
			txt_dtenais.setText("__/__/____");
		}
		return txt_dtenais;
	}
	
	
	/*** This method initializes Btn_Rechercher	@return javax.swing.JButton	*/
	private JButton getBtn_Rechercher() {
		if (Btn_Rechercher == null) {
			Btn_Rechercher = new JButton();
			Btn_Rechercher.setBounds(new Rectangle(50, 600, 110, 40));
			Btn_Rechercher.setText("Rechercher");
			Btn_Rechercher.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					estRechercher=true;
					btn_embaucher.setEnabled(false);
					Btn_Rechercher.setEnabled(false);
					btn_ok.setVisible(true);
					btn_annuler.setVisible(true);
	//				txt_mat.setEditable(true);
				}
			});
		}
		return Btn_Rechercher;
	}
	/*** This method initializes txt_idqual @return javax.swing.JTextField */
	private JComboBox<String> getCbx_qualite_B() {
		if (cbx_qualite_B == null) {
			cbx_qualite_B = new JComboBox<String>();
			cbx_qualite_B.setBounds(new Rectangle(370, 20, 210, 30));
			GestQualiteList listeQualite=gestionBD.chargeQualite();
			int taille = listeQualite.Nbelement();
			cbx_qualite_B.setMaximumRowCount(taille);
			for (int ind = 0 ; ind < taille; ind ++ )
			{
				cbx_qualite_B.addItem(listeQualite.elt(ind).getIdqualite()+ " " +listeQualite.elt(ind).getLibellequalite());
			}
			cbx_qualite_B.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// permet de r�cup�rer le num atelier
					System.out.println(((String)cbx_qualite_B.getSelectedItem()).charAt(0));
				}
			});
		}
		return cbx_qualite_B;
	}


}  //  @jve:decl-index=0:visual-constraint="16,5"
