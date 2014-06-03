package vues;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.Calendar;

import controle.GestionDemandes;
import dialogue.FenParticipant;
import entite.Benevole;
import entite.GestAtelierList;
import entite.GestCategorieList;
import entite.GestHotelList;
import entite.GestQualiteList;
import entite.Intervenant;
import entite.Licencie;
import entite.Participant;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.UIManager;

public class JContentInscription extends JPanel {
	private GestionDemandes gestionBD = new GestionDemandes();
	private GestAtelierList listeAtel;
	
	private JPanel jContentChoixParticipant = null;
	private JPanel jContentIdentite = null;
	private JPanel jContentInscriptionIntervenant= null;
	private JPanel jContentInscriptionBenevole= null;
	private JPanel jContentInscriptionLicencie = null;
	private JPanel jContentNuitée = null;
	private JPanel jContentAccompagnant = null;
	private JPanel jContentHotel = null;
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
	private int nombreDeChoix=0;
	private JRadioButton btn_Accompagnant_Oui=null;
	private JRadioButton btn_Accompagnant_Non=null;
	private JPanel jChoixAccompagnant;
	private ButtonGroup groupeAccompagnant = new ButtonGroup();
	private ArrayList<Integer> listeChoix = new ArrayList<Integer>();
	private JCheckBox samediDejeuner=null;
	private JCheckBox samediDiner=null;
	private JCheckBox dimancheDejeuner=null;
	private JCheckBox cbxJour1=null;
	private JCheckBox cbxJour2=null;
	private JTextField montantCheque1=null;
	private JTextField numeroCheque1=null;
	private JTextField montantCheque2=null;
	private JTextField numeroCheque2=null;
	private JPanel jContentPaiement=null;
	private JRadioButton unPaiement =null;
	private JRadioButton deuxPaiement=null;
	private ButtonGroup groupePaiement = new ButtonGroup();
	private JLabel num1=null;
	private JLabel num2=null;
	private JLabel mont1=null;
	private JLabel mont2=null;
	private ArrayList<JCheckBox> listeAteliersCbx = new ArrayList<JCheckBox>();
	

	/**
	 * Create the panel.
	 */
	public JContentInscription() {
		setLayout(null);
		setSize(1100, 969);
		add(getjContentInscriptionBenevole());
		add(getjContentInscriptionLicencie());
		add(getjContentInscriptionIntervenant());
		add(getjContentChoixParticipant());
		add(getjContentIdentite());
		add(getBtn_ok());
		add(getBtn_annuler());
		add(getBtn_Rechercher());
		regroupeboutons();
		Btn_Rechercher.setEnabled(false);
		btn_ok.setVisible(true);
		btn_annuler.setVisible(true);
		jContentInscriptionIntervenant.setVisible(false);
		jContentInscriptionBenevole.setVisible(false);
		jContentInscriptionLicencie.setVisible(false);
	}
	
	private void regroupeboutons()
	{
		groupeboutons.add(radiobtn_licencie);
		groupeboutons.add(radiobtn_Intervenant);
		groupeboutons.add(radiobtn_Benevole);
		groupeboutons.clearSelection(); // aucun bouton n'est sélectionné
		groupeRole.add(radiobtn_Animateur);
		groupeRole.add(radiobtn_NonAnimateur);
		groupeRole.clearSelection();
		groupeOuiNon.add(radiobtn_Oui);
		groupeOuiNon.add(radiobtn_Non);
		groupeOuiNon.clearSelection();
		groupeAccompagnant.add(btn_Accompagnant_Oui);
		groupeAccompagnant.add(btn_Accompagnant_Non);
		groupeAccompagnant.clearSelection();
		groupePaiement.add(getUnPaiement());
		groupePaiement.add(getDeuxPaiement());
		groupePaiement.clearSelection();
	}
	
	private void enregistrerintervenant(){
		String strLabel[] = ((String)cbx_atelier.getSelectedItem()).split(" ");
		int numatelier = Integer.parseInt(strLabel[0]);
		String Statut="I";

		if(!gestionBD.enregistrerIntervenant(txt_nom.getText().toUpperCase(),txt_prenom.getText(),txt_adr1.getText(), txt_adr2.getText(), txt_cp.getText(), txt_ville.getText(), txt_mail.getText(), Statut, numatelier))
			return;
		Integer IdParticipant = gestionBD.rechercherParticipantsurnom(txt_nom.getText()).getNumparticipant();
		System.out.println(IdParticipant);
		if (getRadiobtn_AnimateurOui().isSelected())
		{
			// mise à jour de l'intervenant animateur dans la collection atelier
			// TODO gerer enabled
			listeAtel.elt(listeAtel.rechercher(numatelier)).SetNointervenant(IdParticipant);
		}
		gestionBD.enregistrerDetailHebergement(IdParticipant, 1, 2, 1, 1);
		gestionBD.enregistrerDetailHebergement(IdParticipant, 2, 2, 1, 2);
		JOptionPane.showMessageDialog(null, "Inscription Intervenant effectuée.",
				"", JOptionPane.INFORMATION_MESSAGE);
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
			JOptionPane.showMessageDialog(null,"La date d'arrivée n'est pas valide (format dd/MM/yyyy)" 
					,"Format de la date d'arrivée incorrecte",JOptionPane.ERROR_MESSAGE);
			return;
		}
		//float txinteret = Float.valueOf(txt_clewifi.getText()).floatValue();
		int idqualite=Integer.parseInt(""+((String)cbx_qualite.getSelectedItem()).charAt(0));
 	 	if(!gestionBD.enregistrerLicencie(txt_nom.getText(),txt_prenom.getText(),txt_adr1.getText(), txt_adr2.getText(), txt_cp.getText().trim(), txt_ville.getText(), txt_mail.getText(),Statut, dteins,dtearr,txt_clewifi.getText(), txt_nolicence.getText(), idqualite, listeChoix))
 	 		return;
 	 	
 	 	int IdParticipant = (gestionBD.rechercherParticipantsurlicence(txt_nolicence.getText())).getNumparticipant() ;
 	 	System.out.println(IdParticipant);
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
					gestionBD.enregistrerDetailHebergement(IdParticipant, numordre, numhotel2, categorie2, 2);
				}
			}
		System.out.println(getRadiobtn_AccompagnantOui().isSelected());
		if(getRadiobtn_AccompagnantOui().isSelected())
		{
			if(getSamediDejeuner().isSelected())
			{
				gestionBD.enregistrerAccompagnant(IdParticipant,1);
			}
			if(getSamediDiner().isSelected())
			{
				gestionBD.enregistrerAccompagnant(IdParticipant,2);
			}
			if(getDimancheDejeuner().isSelected())
			{
				gestionBD.enregistrerAccompagnant(IdParticipant,3);
			}
		}
		if(getUnPaiement().isSelected()){
			float montant = Float.valueOf(getMontantCheque1().getText().trim()).floatValue();
			gestionBD.enregistrerPaiement(IdParticipant,getNumeroCheque1().getText(),montant);
		}
		if(getDeuxPaiement().isSelected()){
			float montant1=Float.valueOf(getMontantCheque1().getText().trim()).floatValue();
			float montant2=Float.valueOf(getMontantCheque2().getText().trim()).floatValue();
			gestionBD.enregistrerPaiement(IdParticipant,getNumeroCheque1().getText(),montant1);
			gestionBD.enregistrerPaiement(IdParticipant,getNumeroCheque2().getText(),montant2);
			
		}
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
		
        Calendar calendar = Calendar.getInstance();  
        int anneeCourante = calendar.get(Calendar.YEAR);
        calendar.setTime(dtenais);  
        int annee = calendar.get(Calendar.YEAR);  
        if (anneeCourante - annee <18){
        	JOptionPane.showMessageDialog(null,"Vous devez avoir 18 ans pour être bénévole"
        			,"Vous êtes trop jeune",JOptionPane.ERROR_MESSAGE);
			return;
        }
	 	if(!gestionBD.enregistrerBenevole(txt_nom.getText(),txt_prenom.getText(),txt_adr1.getText(), txt_adr2.getText(), txt_cp.getText().trim(), txt_ville.getText().trim(), txt_mail.getText(),Statut, txt_nolicence_B.getText(), dtenais))
	 		return;
	 	Integer IdParticipant = gestionBD.rechercherParticipantsurlicence(txt_nolicence_B.getText()).getNumparticipant();
 	 	System.out.println(IdParticipant);
	 	if (getCbxJour1().isSelected()){
	 		gestionBD.enregistrerPresence(IdParticipant, 1);
	 	}
	 	if (getCbxJour2().isSelected()){
	 		gestionBD.enregistrerPresence(IdParticipant, 2);
	 	}
	}
	
	private void raz()
	{
		txt_nom.setText("");
		txt_prenom.setText("");
		txt_adr1.setText("");
		txt_adr2.setText("");
		txt_cp.setText("");
		txt_ville.setText("");
		txt_mail.setText("");
		txt_dtenais.setText("__/__/____");
		txt_clewifi.setText("");
		txt_nolicence.setText("");
		txt_nolicence_B.setText("");
		montantCheque1.setText("");
		montantCheque2.setText("");
		numeroCheque1.setText("");
		numeroCheque2.setText("");
		samediDejeuner.setSelected(false);
		samediDiner.setSelected(false);
		dimancheDejeuner.setSelected(false);
		cbxJour1.setSelected(false);
		cbxJour2.setSelected(false);
		cbx_qualite_B.setSelectedIndex(0);
		check_Nuitdu12.setSelected(false);
		check_Nuitdu13.setSelected(false);
		java.util.Date maDate=new java.util.Date();
		SimpleDateFormat dateStandard = new SimpleDateFormat("dd/MM/yyyy");
		txt_dteinsc.setText(dateStandard.format(maDate));
		txt_dtearr.setText(dateStandard.format(maDate));
		Btn_Rechercher.setEnabled(false);
		btn_ok.setVisible(true);
		btn_annuler.setVisible(true);
		estRechercher=false;
		regroupeboutons();
		listeChoix.clear();
		nombreDeChoix = 0;
		for(JCheckBox cbx : listeAteliersCbx) {
			cbx.setSelected(false);
		}
		
	}
	
	private Boolean verification(){
		/* cas d'insertion dans la base de données */
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
		if(txt_nolicence.equals("")||txt_nolicence_B.equals("")){
			JOptionPane.showMessageDialog(null,"Vous devez saisir votre numéro de licence" 
					,"",JOptionPane.ERROR_MESSAGE);
			return(false);
		}
		if (getRadiobtn_licencie().isSelected() && getRadiobtn_HotelOui().isSelected()&&(!((getCheck_Nuitdu12().isSelected())||(getCheck_Nuitdu13().isSelected())))){
					JOptionPane.showMessageDialog(null,"Vous devez obligatoirement choisir la(les) nuit(ées)" 
					,"",JOptionPane.ERROR_MESSAGE);
			return(false);
		}
		return(true);
	}
	
	public GestAtelierList getListeAtel() {
		return this.listeAtel;
	}
	
	private JPanel getjContentIdentite() {
		if (jContentIdentite == null) {
			jContentIdentite = new JPanel();
			//jContentIdentite.setBounds(11, 56, 750, 220);
			jContentIdentite.setBounds(10, 81, 750, 220);
			jContentIdentite.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Identité du Participant ");
			jContentIdentite.setBorder(Bord);
			jLabel_nom = new JLabel();
			jLabel_nom.setBounds(new Rectangle(10, 20, 100, 30));
			jLabel_nom.setText("Nom ");
			jLabel_prenom = new JLabel();
			jLabel_prenom.setBounds(new Rectangle(350,20, 50, 30));
			jLabel_prenom.setText("Prénom ");
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
			jContentInscriptionLicencie.setBounds(10, 300, 1052, 490);
			jContentInscriptionLicencie.setLayout(null);
			jContentInscriptionLicencie.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Complément sur l'inscription du Licencié ");
			jContentInscriptionLicencie.setBorder(Bord);
			jLabel_nolicence = new JLabel();
			jLabel_nolicence.setBounds(new Rectangle(15, 20, 100, 30));
			jLabel_nolicence.setText("Numero licence ");
			jLabel_idqualite = new JLabel();
			jLabel_idqualite.setBounds(new Rectangle(285, 20, 75, 30));
			jLabel_idqualite.setText("Fonction ");
			jLabel_dteins = new JLabel();
			jLabel_dteins.setBounds(new Rectangle(744, 20, 100, 30));
			jLabel_dteins.setText("Choix d'ateliers");
			jLabel_dtearr = new JLabel();
			jLabel_dtearr.setBounds(new Rectangle(285, 60, 100, 30));
			jLabel_dtearr.setText("Date Arrivée ");
			jLabel_clewifi = new JLabel();
			jLabel_clewifi.setBounds(new Rectangle(513, 60, 50, 30));
			jLabel_clewifi.setText("Clé Wifi ");
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

			genererAtelier();
			jContentInscriptionLicencie.add(getjContentNuitée(),null);
			jContentInscriptionLicencie.add(getjContentAccompagnant(),null);


			JLabel label = new JLabel();
			label.setText("Date Inscription");
			label.setBounds(new Rectangle(5, 60, 100, 30));
			label.setBounds(15, 68, 100, 30);
			jContentInscriptionLicencie.add(label);
			
			JButton btnNewButton = new JButton("G\u00E9n\u00E9rer\r\n");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getTxt_clewifi().setText(generate(8));
				}
			});
			btnNewButton.setBounds(569, 101, 89, 23);
			jContentInscriptionLicencie.add(btnNewButton);
			

			jContentInscriptionLicencie.add(getjContentPaiement());
			
		

			
		}
		return jContentInscriptionLicencie;
	}
	
	private JPanel getjContentPaiement(){
		if(jContentPaiement==null){
			jContentPaiement = new JPanel();
			jContentPaiement.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Paiement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			jContentPaiement.setBounds(19, 116, 427, 101);
			jContentPaiement.setLayout(null);
			jContentPaiement.add(getUnPaiement());
			jContentPaiement.add(getDeuxPaiement());
			jContentPaiement.add(getNumeroCheque1());
			jContentPaiement.add(getMontantCheque1());
			jContentPaiement.add(getNumeroCheque2());
			jContentPaiement.add(getMontantCheque2());
			jContentPaiement.add(getMont1());
			jContentPaiement.add(getNum1());
			jContentPaiement.add(getMont2());
			jContentPaiement.add(getNum2());
			
			
			getNumeroCheque1().setVisible(false);
			getMontantCheque1().setVisible(false);
			getNumeroCheque2().setVisible(false);
			getMontantCheque2().setVisible(false);
			getNum1().setVisible(false);
			getMont1().setVisible(false);
			getNum2().setVisible(false);
			getMont2().setVisible(false);
			
		}
		return jContentPaiement;
	}
	
	private JLabel getNum1(){
		if(num1==null){
			num1 = new JLabel("Num\u00E9ro");
			num1.setBounds(16, 50, 46, 14);
		}
		return num1;
	}
	
	private JLabel getMont1(){
		if(mont1==null){
			mont1 = new JLabel("Montant");
			mont1.setBounds(17, 73, 46, 14);
			}
		return mont1;
	}
	
	private JLabel getNum2(){
		if(num2==null){
			num2 = new JLabel("Num\u00E9ro");
			num2.setBounds(229, 50, 46, 14);
		}
		return num2;
	}
	
	private JLabel getMont2(){
		if(mont2==null){
			mont2 = new JLabel("Montant");
			mont2.setBounds(229, 73, 46, 14);
		}
		return mont2;
	}
	
	private JRadioButton getUnPaiement(){
		if (unPaiement==null){
			unPaiement = new JRadioButton("1 ch\u00E8que");
			unPaiement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (unPaiement.isSelected()){
						getNumeroCheque1().setVisible(true);
						getMontantCheque1().setVisible(true);
						getNumeroCheque2().setVisible(false);
						getMontantCheque2().setVisible(false);
						getNum1().setVisible(true);
						getMont1().setVisible(true);
						getNum2().setVisible(false);
						getMont2().setVisible(false);
						
					}
				}
			});
			unPaiement.setBounds(6, 17, 109, 23);
		}
		return unPaiement;
	}
	
	private JRadioButton getDeuxPaiement(){
		if (deuxPaiement==null){
			deuxPaiement = new JRadioButton("2 ch\u00E8ques");
			deuxPaiement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(deuxPaiement.isSelected()){
							
						getNumeroCheque1().setVisible(true);
						getMontantCheque1().setVisible(true);
						getNumeroCheque2().setVisible(true);
						getMontantCheque2().setVisible(true);
						getNum1().setVisible(true);
						getMont1().setVisible(true);
						getNum2().setVisible(true);
						getMont2().setVisible(true);
					}
				}
			});
			deuxPaiement.setBounds(121, 17, 109, 23);
		}
		return deuxPaiement;
	}
	
	private JTextField getNumeroCheque1(){
		if(numeroCheque1==null){
			numeroCheque1 = new JTextField();
			numeroCheque1.setBounds(73, 47, 86, 20);
			numeroCheque1.setColumns(10);
		}
		return numeroCheque1;
	}
	
	private JTextField getMontantCheque1(){
		if(montantCheque1==null){
			montantCheque1 = new JTextField();
			montantCheque1.setBounds(73, 70, 86, 20);
			montantCheque1.setColumns(10);
		}
		return montantCheque1;
	}
	private JTextField getNumeroCheque2(){
		if(numeroCheque2==null){
			numeroCheque2 = new JTextField();
			numeroCheque2.setBounds(290, 47, 86, 20);
			numeroCheque2.setColumns(10);
		}
		return numeroCheque2;
	}
	private JTextField getMontantCheque2(){
		if(montantCheque2==null){
			montantCheque2 = new JTextField();
			montantCheque2.setBounds(290, 70, 86, 20);
			montantCheque2.setColumns(10);
		}
		return montantCheque2;
	}
	private JPanel getjContentInscriptionBenevole() {
		if (jContentInscriptionBenevole == null) {
			jContentInscriptionBenevole = new JPanel();
			jContentInscriptionBenevole.setBounds(10, 300, 750, 270);
			jContentInscriptionBenevole.setLayout(null);
			jContentInscriptionBenevole.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Complément sur l'inscription du Bénévole ");
			jContentInscriptionBenevole.setBorder(Bord);
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
			

			jContentInscriptionBenevole.add(getCbxJour1());

			jContentInscriptionBenevole.add(getCbxJour2());
			
			JLabel lblJourDeBnvolat = new JLabel("Jour de b\u00E9n\u00E9volat");
			lblJourDeBnvolat.setBounds(5, 120, 100, 14);
			jContentInscriptionBenevole.add(lblJourDeBnvolat);
		}
		return jContentInscriptionBenevole;
	}
	
	
		
		
	
	private JPanel getjContentInscriptionIntervenant() {
		if (jContentInscriptionIntervenant == null) {
			jContentInscriptionIntervenant = new JPanel();
			jContentInscriptionIntervenant.setBounds(10, 300, 750, 270);
			jContentInscriptionIntervenant.setLayout(null);
			jContentInscriptionIntervenant.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Complément sur l'inscription de l'intervenant ");
			jContentInscriptionIntervenant.setBorder(Bord);
			jLabel_atelier = new JLabel();
			jLabel_atelier.setBounds(new Rectangle(5, 20, 100, 30));
			jLabel_atelier.setText("Choix de l'atelier ");
			jContentInscriptionIntervenant.add(getRadiobtn_AnimateurOui(), null);
			jContentInscriptionIntervenant.add(getRadiobtn_AnimateurNon(), null);
			jContentInscriptionIntervenant.add(jLabel_atelier, null);
			jContentInscriptionIntervenant.add(getcbx_atelier(), null);
			
		}
		return jContentInscriptionIntervenant;
	}
	
	private JPanel getjContentNuitée() {
		if (jContentNuitée == null) {
			jContentNuitée = new JPanel();
			jContentNuitée.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Nuitée(s)");
			jContentNuitée.setBorder(Bord);
			jContentNuitée.setBounds(new Rectangle(15, 222, 431, 257));
			

			jContentNuitée.add(getRadiobtn_HotelOui(), null);
			jContentNuitée.add(getRadiobtn_HotelNon(), null);
			jContentNuitée.add(getjContentHotel(), null);
			jContentHotel.setVisible(false);
		}
		return jContentNuitée;
	}
	
	private JPanel getjContentAccompagnant() {
		if (jContentAccompagnant == null) {
			jContentAccompagnant = new JPanel();
			jContentAccompagnant.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Accompagnant ");
			jContentAccompagnant.setBorder(Bord);
			jContentAccompagnant.setBounds(new Rectangle(456, 222, 277, 257));
			
			jContentAccompagnant.add(getRadiobtn_AccompagnantOui());
			jContentAccompagnant.add(getRadiobtn_AccompagnantNon());
			jContentAccompagnant.add(getChoixAccompagnant());
			
			jChoixAccompagnant.setVisible(false);
		}
		return jContentAccompagnant;
	}
	
	private JPanel getChoixAccompagnant(){
		jChoixAccompagnant = new JPanel();
		jChoixAccompagnant.setBounds(16, 57, 251, 189);
		jChoixAccompagnant.setLayout(null);
		JLabel lblSamedi = new JLabel("Samedi");
		lblSamedi.setBounds(10, 11, 46, 14);
		jChoixAccompagnant.add(lblSamedi);
		jChoixAccompagnant.add(getSamediDejeuner());
		jChoixAccompagnant.add(getSamediDiner());
		JLabel lblDimanche = new JLabel("Dimanche");
		lblDimanche.setBounds(10, 85, 74, 14);
		jChoixAccompagnant.add(lblDimanche);
		jChoixAccompagnant.add(getDimancheDejeuner());

		
		return jChoixAccompagnant;
	}
	
	private JCheckBox getSamediDejeuner(){
		if (samediDejeuner==null){	
			samediDejeuner = new JCheckBox("D\u00E9jeuner");
			samediDejeuner.setBounds(10, 32, 97, 23);
		}
		return samediDejeuner;
		
	}
	
	private JCheckBox getSamediDiner(){
		if(samediDiner==null){
			samediDiner = new JCheckBox("D\u00EEner");
			samediDiner.setBounds(109, 32, 97, 23);
		}
		return samediDiner;
		
	}
	
	private JCheckBox getDimancheDejeuner(){
		if(dimancheDejeuner==null){
			dimancheDejeuner = new JCheckBox("D\u00E9jeuner");
			dimancheDejeuner.setBounds(10, 106, 97, 23);
		}
		return dimancheDejeuner;
		
	}
	
	private JCheckBox getCbxJour1(){
		if (cbxJour1==null){
			cbxJour1 = new JCheckBox("Jour 1");
			cbxJour1.setBounds(155, 116, 65, 23);
		}
		return cbxJour1;
	}
	private JCheckBox getCbxJour2(){
		if(cbxJour2==null){
			cbxJour2 = new JCheckBox("Jour 2");
			cbxJour2.setBounds(247, 116, 97, 23);
		}
		return cbxJour2;
	}
	
	private JPanel getjContentHotel() {
		if (jContentHotel == null) {
			jContentHotel = new JPanel();
			jContentHotel.setLayout(null);
			jContentHotel.setBounds(new Rectangle(10, 57, 413, 189));
			jlabel_idnuit_un = new JLabel();
			jlabel_idnuit_un.setBounds(new Rectangle(30, 11, 250, 30));
			jlabel_idnuit_un.setText("Nuit du Vendredi 12 Septembre 2014 ");
			jlabel_idnuit_deux = new JLabel();
			jlabel_idnuit_deux.setBounds(new Rectangle(30, 102, 250, 30));
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
			jContentChoixParticipant.setBounds(150, 11, 500, 59);
			jContentChoixParticipant.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Type de Participant ");
			jContentChoixParticipant.setBorder(Bord);
			jContentChoixParticipant.add(getRadiobtn_Intervenant(), null);
			jContentChoixParticipant.add(getRadiobtn_licencie(), null);
			jContentChoixParticipant.add(getRadiobtn_Benevole(), null);
		}
		return jContentChoixParticipant;
	}
	
	/*** This method initializes radiobtn_Intervenant @return javax.swing.JRadioButton*/
	private JRadioButton getRadiobtn_Benevole() {
		if (radiobtn_Benevole == null) {
			radiobtn_Benevole = new JRadioButton();
			radiobtn_Benevole.setBounds(new Rectangle(198, 20, 102, 30));
			radiobtn_Benevole.setText("Bénévole");
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
		
	private JRadioButton getRadiobtn_AccompagnantOui(){
		if (btn_Accompagnant_Oui == null) {
			btn_Accompagnant_Oui = new JRadioButton();
			btn_Accompagnant_Oui.setText("Oui");
			btn_Accompagnant_Oui.setBounds(new Rectangle(10, 20, 80, 30));
			btn_Accompagnant_Oui.setBounds(16, 20, 80, 30);
			btn_Accompagnant_Oui.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jChoixAccompagnant.setVisible(true);
				}
			});
		}
		return btn_Accompagnant_Oui;
	}
	
	
	private JRadioButton getRadiobtn_AccompagnantNon(){
		if (btn_Accompagnant_Non==null){
			
			btn_Accompagnant_Non = new JRadioButton();
			btn_Accompagnant_Non.setText("Non");
			btn_Accompagnant_Non.setBounds(new Rectangle(100, 20, 100, 30));
			btn_Accompagnant_Non.setBounds(98, 20, 100, 30);
			btn_Accompagnant_Non.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jChoixAccompagnant.setVisible(false);
				}
			});
		}
		return btn_Accompagnant_Non;
		
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
	/*** This method initializes btn_ok	@return javax.swing.JButton	*/
	private JButton getBtn_ok() {
		if (btn_ok == null) {
			btn_ok = new JButton();
			btn_ok.setBounds(518, 801, 110, 40);
			btn_ok.setText("Enregistrer");
			btn_ok.setVisible(false);
			btn_ok.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
				if(!estRechercher)
				{
					if (verification())
					{
					/* cas d'un intervenant à traiter */
						if(radiobtn_Intervenant.isSelected())
						{
						enregistrerintervenant();
						}
					
						
						/* cas d'un bénévole à traiter */
						if (radiobtn_Benevole.isSelected())
						{
							enregistrerbenevole();
						}
						if(radiobtn_licencie.isSelected())
						{
							enregistrerlicencie();
						}
						
						raz();
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
					/* cas de recherche d'une personne dans la base de données */
					Participant retourParticipant=gestionBD.rechercherParticipant(txt_mat.getText());
					if(retourParticipant==null){
						JOptionPane.showMessageDialog(null,"Pas de personne (intervenant, licencié ou bénévole) avec le matricule "+txt_mat.getText() 
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
			btn_annuler.setBounds(398, 801, 110, 40);
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
			check_Nuitdu12.setBounds(new Rectangle(10, 13, 21, 21));
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
			check_Nuitdu13.setBounds(new Rectangle(6, 105, 21, 21));
			check_Nuitdu13.setVisible(true);
		}
		return check_Nuitdu13;
	}
	/*** This method initializes txt_clewifi @return javax.swing.JTextField	*/
	private JTextField getTxt_clewifi() {
		if (txt_clewifi == null) {
			txt_clewifi = new JTextField();
			txt_clewifi.setEditable(false);
			txt_clewifi.setBounds(new Rectangle(559, 60, 110, 30));
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
			txt_dtearr.setBounds(new Rectangle(370, 60, 100, 30));
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
					// permet de récupérer le num atelier
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
					// permet de récupérer le num atelier

					String strLabel[] = ((String)cbx_atelier.getSelectedItem()).split(" ");
					int numatelier = Integer.parseInt(strLabel[0]);
					//int numatelier = Character.getNumericValue(((String)cbx_atelier.getSelectedItem()).charAt(0));
					Integer noIntervenant = listeAtel.elt(listeAtel.rechercher(numatelier)).getNointervenant();
					System.out.println(numatelier);
					if (noIntervenant != null) {
						getRadiobtn_AnimateurOui().setEnabled(false);
					} else {
						getRadiobtn_AnimateurOui().setEnabled(true);
					}
				}
			});
		}
		return cbx_atelier;
	}
	private JComboBox<String> getcbx_hotel_nuit2() {
		if (cbx_hotel_nuit2 == null) {
			cbx_hotel_nuit2 = new JComboBox<String>();
			cbx_hotel_nuit2.setBounds(new Rectangle(6, 135, 300, 30));
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
			cbx_hotel_nuit1.setBounds(new Rectangle(6, 45, 300, 30));
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
			cbx_catchambre_nuit2.setBounds(new Rectangle(307, 135, 100, 30));
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
			cbx_catchambre_nuit1.setBounds(new Rectangle(307, 45, 100, 30));
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
			txt_nolicence.setBounds(new Rectangle(130, 20, 110, 30));
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
			Btn_Rechercher.setBounds(278, 801, 110, 40);
			Btn_Rechercher.setText("Rechercher");
			Btn_Rechercher.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					estRechercher=true;
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
					// permet de récupérer le num atelier
					System.out.println(((String)cbx_qualite_B.getSelectedItem()).charAt(0));
				}
			});
		}
		return cbx_qualite_B;
	}
	



	private void genererAtelier(){
		listeAtel = gestionBD.chargeAtelier();
		int j = 64;
		for (int i=0; i<listeAtel.Nbelement();i++){
			JCheckBox cbx = new JCheckBox(listeAtel.elt(i).getNoatelier()+" "+listeAtel.elt(i).getLibelleatelier());
			cbx.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modifierNombreChoix();
					gererCheckbox();
					}
				}
			);
			cbx.setBounds(744, j, 233, 23);
			j+=30;
			jContentInscriptionLicencie.add(cbx);
			listeAteliersCbx.add(cbx);
			
		}
		
	}
		
		private void modifierNombreChoix(){
			for (JCheckBox cbx : listeAteliersCbx){
				String strLabel[] = cbx.getText().split(" ");
				int indice = Integer.parseInt(strLabel[0]);
				
				if (cbx.isSelected()&&!listeChoix.contains(indice)){
					nombreDeChoix++;
					listeChoix.add(indice);
					
				}
				else if (!cbx.isSelected()&&listeChoix.contains(indice)){
					nombreDeChoix--;
					listeChoix.remove((Object)(indice));		
				}
				
			}
			System.out.println(listeChoix);
		}
		
		private void gererCheckbox(){
			if (nombreDeChoix==5){
				for (JCheckBox cbx : listeAteliersCbx){
					if(!cbx.isSelected()){
						cbx.setEnabled(false);
					}
				}
			}else{
				for (JCheckBox cbx : listeAteliersCbx){
					cbx.setEnabled(true);			
				}
				
			}
		}
		public static String generate(int length) {
	        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
	        StringBuffer pass = new StringBuffer();
	        for(int x=0;x<length;x++)   {
	           int i = (int)Math.floor(Math.random() * (chars.length() -1));
	           pass.append(chars.charAt(i));
	        }
	        return pass.toString();
	}

}
