package dialogue;

import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import controle.*;
import entite.*;

import javax.swing.ButtonGroup;

public class FenAjouter extends JPanel {
	private GestionDemandes gestionBD = new GestionDemandes();
	private GestAtelierList listeAtel;
	
	private JTextField textIdVacation;
	private JTextField textDateDebut;
	private JTextField textDateFin;
	private ButtonGroup groupeboutons = new ButtonGroup(); 
	private JRadioButton rdbtnAtelier = null;
	private JRadioButton rdbtnThme = null;
	private JRadioButton rdbtnVacation = null;
	
	private JPanel JContentPrincipal = null;
	private JPanel jContentChoixAjouter = null;
	private JPanel jContentAjouterAtelier = null;
	private JPanel jContentAjouterTheme = null;
	private JPanel jContentAjouterVacation = null;
	
	private JButton btnAnnuler;
	private JButton btnEnregistrer;
	private JButton btnQuitter;
	
	// JContentAjoutAtelier
	private JLabel lblIdDeLatelier = null;
	private JLabel lblIdParticipant = null;
	private JLabel lblLabellDeLatelier = null;
	private JLabel lblNombreDePlaces = null;
	private JTextField textIdParticipant;
	private JTextField textLibelleAtelier;
	private JTextField textNbPlaces;
	private JTextField textField;
	
	// JContentAjoutTheme
	private JLabel lblIdAtelierTheme = null;
	private JLabel lblLibelleTheme = null;
	private JLabel lblIdTheme = null;
	private JTextField textIdTheme = null;
	private JTextField textIdLibelleTheme;
	public JComboBox cbx_AtelierTheme;
	
	// JContentAjouterVacation
	private JLabel lblIdAtelier;
	private JLabel lblVacation;
	private JLabel lblDateDebut;
	private JLabel lblDateFin;
	private JComboBox<String> cbx_AtelierVacation;
	
	public void regroupeboutton() {
		groupeboutons.add(rdbtnAtelier);
		groupeboutons.add(rdbtnThme);
		groupeboutons.add(rdbtnVacation);
		groupeboutons.clearSelection();
	}
	// ################
	// ## Conteneurs ##
	// ################
	
	public JPanel getjContentChoixAjouter() {
		if (jContentChoixAjouter == null) {
			jContentChoixAjouter = new JPanel();
			jContentChoixAjouter.setBounds(98, 5, 430, 56);
			jContentChoixAjouter.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
			jContentChoixAjouter.add(getrdbtnAtelier());
			jContentChoixAjouter.add(getrdbtnThme());
			jContentChoixAjouter.add(getrdbtnVacation());
		}
		return jContentChoixAjouter;
	}
	
	public JPanel getjContentAjouterTheme() {
		if (jContentAjouterTheme == null) {
			jContentAjouterTheme = new JPanel();
			jContentAjouterTheme.setBounds(98, 84, 430, 194);
			
			jContentAjouterTheme.setLayout(null);
			jContentAjouterTheme.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter un thème", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
			jContentAjouterTheme.add(getlblIdAtelierTheme());
			jContentAjouterTheme.add(getlblLibelleTheme());
			
			jContentAjouterTheme.add(gettextIdTheme());
			jContentAjouterTheme.add(gettextIdLibelleTheme());
			jContentAjouterTheme.add(getlblIdTheme());
			jContentAjouterTheme.add(getcomboBoxAtelier());
		}
		return jContentAjouterTheme;
	}
	
	public JPanel getjContentAjouterAtelier() {
		if (jContentAjouterAtelier == null) {
			jContentAjouterAtelier = new JPanel();
			jContentAjouterAtelier.setBounds(98, 84, 430, 164);
			jContentAjouterAtelier.setVisible(false);
			jContentAjouterAtelier.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter un atelier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			jContentAjouterAtelier.setLayout(null);
			
			jContentAjouterAtelier.add(getlblIdDeLatelier());
			jContentAjouterAtelier.add(getlblIdParticipant());
			jContentAjouterAtelier.add(getlblLabellDeLatelier());
			jContentAjouterAtelier.add(getlblNombreDePlaces());
			
			jContentAjouterAtelier.add(gettextIdParticipant());
			jContentAjouterAtelier.add(gettextLibelleAtelier());
			jContentAjouterAtelier.add(gettextNbPlaces());
			jContentAjouterAtelier.add(gettextField());
		}
		return jContentAjouterAtelier;
	}
	
	public JPanel getjContentAjouterVacation() {
		if (jContentAjouterVacation == null) {
			jContentAjouterVacation = new JPanel();
			jContentAjouterVacation.setBounds(98, 84, 430, 165);
			jContentAjouterVacation.setLayout(null);
			jContentAjouterVacation.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter une vacation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
			jContentAjouterVacation.add(getlblIdAtelier());
			jContentAjouterVacation.add(getlblDateDebut());
			jContentAjouterVacation.add(getlblVacation());
			jContentAjouterVacation.add(getlblDateFin());
			
			jContentAjouterVacation.add(gettextIdVacation());
			jContentAjouterVacation.add(gettextDateDebut());
			jContentAjouterVacation.add(gettextDateFin());
			
			jContentAjouterVacation.add(getcbx_Atelier());
		}
		return jContentAjouterVacation;
	}
	
	// ################
	// ## Components ##
	// ################
	public JRadioButton getrdbtnAtelier() {
		if (rdbtnAtelier == null) {
		rdbtnAtelier = new JRadioButton("Atelier");
		rdbtnAtelier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jContentAjouterAtelier.setVisible(true);
					jContentAjouterTheme.setVisible(false);
					jContentAjouterVacation.setVisible(false);
				}
			});
		}
		return rdbtnAtelier;
	}
	
	public JRadioButton getrdbtnThme() {
		if (rdbtnThme == null) {
			rdbtnThme = new JRadioButton("Th\u00E8me");
		rdbtnThme.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jContentAjouterAtelier.setVisible(false);
					jContentAjouterTheme.setVisible(true);
					jContentAjouterVacation.setVisible(false);
				}
			});
		}
		return rdbtnThme;
	}
	
	public JRadioButton getrdbtnVacation() {
		if (rdbtnVacation == null) {
			rdbtnVacation = new JRadioButton("Vacation");
			rdbtnVacation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jContentAjouterAtelier.setVisible(false);
					jContentAjouterTheme.setVisible(false);
					jContentAjouterVacation.setVisible(true);
				}
			});
		}
		return rdbtnVacation;
	}
	
	public JLabel getlblIdDeLatelier() {
		if (lblIdDeLatelier == null) {
			lblIdDeLatelier = new JLabel("ID de l'atelier ");
			lblIdDeLatelier.setBounds(10, 38, 97, 14);	
		}
		return lblIdDeLatelier;
	}
	
	public JLabel getlblIdParticipant() {
		if (lblIdParticipant  == null) {
			lblIdParticipant = new JLabel("ID participant");
			lblIdParticipant.setBounds(10, 72, 97, 14);
		}
		return lblIdParticipant;
	}
	
	public JLabel getlblLabellDeLatelier() {
		if (lblLabellDeLatelier == null) {
			lblLabellDeLatelier = new JLabel("Labell\u00E9 de l'atelier");
			lblLabellDeLatelier.setBounds(10, 106, 97, 14);
		}
		return lblLabellDeLatelier;
	}

	public JLabel getlblNombreDePlaces() {
		if (lblNombreDePlaces == null) {
			lblNombreDePlaces = new JLabel("Nombre de places ");
			lblNombreDePlaces.setBounds(10, 140, 97, 14);	
		}
		return lblNombreDePlaces;
	}
	
	public JTextField gettextIdParticipant() {
		if (textIdParticipant == null) {
			textIdParticipant = new JTextField();
			textIdParticipant.setBounds(117, 69, 86, 20);
			
			textIdParticipant.setColumns(10);
		}
		return textIdParticipant;
	}
	
	public JTextField gettextLibelleAtelier() {
		if (textLibelleAtelier == null) {
			textLibelleAtelier = new JTextField();
			textLibelleAtelier.setBounds(117, 103, 290, 20);
			textLibelleAtelier.setColumns(10);
		}
		return textLibelleAtelier;
	}
	
	public JTextField gettextNbPlaces() {
		if (textNbPlaces == null) {
			textNbPlaces = new JTextField();
			textNbPlaces.setBounds(117, 137, 37, 20);
			textNbPlaces.setColumns(10);
		}
		return textNbPlaces;
	}
	
	public JTextField gettextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(117, 35, 86, 20);
			textField.setColumns(10);
		}
		return textField; 
	}
	
	public JLabel getlblIdAtelierTheme() {
		if (lblIdAtelierTheme == null) {
			lblIdAtelierTheme = new JLabel("ID de l'atelier ");
			lblIdAtelierTheme.setBounds(10, 38, 97, 14);
			
		}
		return lblIdAtelierTheme;
	}
	
	public JLabel getlblLibelleTheme() {
		if (lblLibelleTheme == null) {
			lblLibelleTheme = new JLabel("Libell\u00E9 du th\u00E8me");
			lblLibelleTheme.setBounds(10, 106, 97, 14);
		}
		return lblLibelleTheme;
	}
	
	public JTextField gettextIdTheme() {
		if (textIdTheme == null) {
			textIdTheme = new JTextField();
			textIdTheme.setColumns(10);
			textIdTheme.setBounds(117, 69, 134, 20);
		}
		return textIdTheme;
	}
	
	public JTextField gettextIdLibelleTheme() {
		if (textIdLibelleTheme == null) {
			textIdLibelleTheme = new JTextField();
			textIdLibelleTheme.setColumns(10);
			textIdLibelleTheme.setBounds(117, 103, 290, 20);
		}
		return textIdLibelleTheme;
	}
	
	public JLabel getlblIdTheme() {
		if (lblIdTheme == null) {
			lblIdTheme = new JLabel("ID du th\u00E8me");
			lblIdTheme.setBounds(10, 72, 59, 14);
		}
		return lblIdTheme;
	}
	
	public JComboBox getcomboBoxAtelier() {
		if (cbx_AtelierTheme == null) {
			cbx_AtelierTheme = new JComboBox();
			cbx_AtelierTheme.setBounds(117, 35, 134, 20);
		}
		return cbx_AtelierTheme;
	}
	
	
	public JLabel getlblIdAtelier() {
		if (lblIdAtelier == null) {
			lblIdAtelier = new JLabel("ID de l'atelier");
			lblIdAtelier.setBounds(10, 38, 97, 14);
		}
		return lblIdAtelier;
	}
	
	public JLabel getlblVacation() {
		if (lblVacation == null) {
			lblVacation = new JLabel("ID vacation");
			lblVacation.setBounds(10, 72, 97, 14);
			
		}
		return lblVacation;
	}
	
	public JLabel getlblDateDebut() {
		if (lblDateDebut == null) {
			lblDateDebut = new JLabel("Date de début");
			lblDateDebut.setBounds(10, 106, 97, 14);
			
		}
		return lblDateDebut;
	}
	
	public JLabel getlblDateFin() {
		if (lblDateFin == null) {
			lblDateFin = new JLabel("Date de fin");
			lblDateFin.setBounds(10, 134, 97, 14);
			
		}
		return lblDateFin;
	}
	
	public JTextField gettextIdVacation() {
		if (textIdVacation == null) {
			textIdVacation = new JTextField();
			textIdVacation.setColumns(10);
			textIdVacation.setBounds(117, 69, 134, 20);
		}
		return textIdVacation; 
	}
	
	public JTextField gettextDateDebut() {
		if (textDateDebut == null) {
			textDateDebut = new JTextField();
			textDateDebut.setColumns(10);
			textDateDebut.setBounds(117, 103, 64, 20);
		}
		return textDateDebut;
	}
	
	
	public JTextField gettextDateFin() {
		if (textDateFin == null) {
			textDateFin = new JTextField();
			textDateFin.setColumns(10);
			textDateFin.setBounds(117, 134, 64, 20);
		}
		return textDateFin;
	}
	
	public JComboBox<String> getcbx_Atelier() {
		if (cbx_AtelierVacation == null) {
			cbx_AtelierVacation = new JComboBox<String>();
			cbx_AtelierVacation.setBounds(117, 35, 134, 20);
		}
		return cbx_AtelierVacation;
	}
	
	public JButton getbtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.setBounds(108, 289, 102, 47);
		}
		return btnAnnuler;
	}
	
	public JButton getbtnEnregistrer() {
		if (btnEnregistrer == null) {
			btnEnregistrer = new JButton("Enregistrer");
			btnEnregistrer.setBounds(220, 289, 102, 47);
		}
		return btnEnregistrer ;
	}
	
	public JButton getbtnQuitter() {
		if (btnQuitter == null) {
			btnQuitter = new JButton("Quitter");
			btnQuitter.setBounds(332, 289, 102, 47);
		}
		return btnQuitter;
	}
	
	/**
	 * Create the panel.
	 */
	public FenAjouter() {
		setLayout(null);
		
		JPanel JContentPrincipal = new JPanel();
		JContentPrincipal.setBounds(0, 0, 592, 509);
		add(JContentPrincipal);
		JContentPrincipal.setLayout(null);
		JContentPrincipal.add(getjContentAjouterVacation());
		JContentPrincipal.add(getjContentAjouterTheme());
		
		JContentPrincipal.add(getjContentChoixAjouter());
		JContentPrincipal.add(getjContentAjouterAtelier());
		
		JContentPrincipal.add(getbtnAnnuler());
		JContentPrincipal.add(getbtnEnregistrer());
		JContentPrincipal.add(getbtnQuitter());
		
		regroupeboutton();
	}
}
