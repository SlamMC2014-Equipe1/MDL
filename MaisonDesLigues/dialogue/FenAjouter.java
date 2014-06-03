package dialogue;

import java.awt.Button;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;
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
	private JLabel lblIdParticipant = null;
	private JLabel lblLabellDeLatelier = null;
	private JLabel lblNombreDePlaces = null;
	private JTextField textIdParticipant;
	private JTextField textLibelleAtelier;
	private JTextField textNbPlaces;
	
	// JContentAjoutTheme
	private JLabel lblIdAtelierTheme = null;
	private JLabel lblLibelleTheme = null;
	private JTextField textIdLibelleTheme;
	public JComboBox<String> cbx_AtelierTheme;
	
	// JContentAjouterVacation
	private JLabel lblIdAtelier;
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
	/**
	 * Create the panel.
	 */
	public FenAjouter() {
		setLayout(null);
		
		JPanel JContentPrincipal = new JPanel();
		JContentPrincipal.setBounds(0, 0, 592, 509);
		add(JContentPrincipal);
		JContentPrincipal.setLayout(null);
		JContentPrincipal.add(getjContentAjouterTheme());
		JContentPrincipal.add(getjContentAjouterVacation());
		JContentPrincipal.add(getjContentAjouterAtelier());
		
		JContentPrincipal.add(getjContentChoixAjouter());
		
		JContentPrincipal.add(getbtnAnnuler());
		JContentPrincipal.add(getbtnEnregistrer());
		
		jContentAjouterAtelier.setVisible(false);
		jContentAjouterVacation.setVisible(false);
		jContentAjouterTheme.setVisible(false);
		
		listeAtel = gestionBD.chargeAtelier();
		
		// Initialise le contenu des listes déroulantes
		alimentationListes();
		
		regroupeboutton();
	}
	
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
			jContentAjouterTheme.setBounds(98, 84, 430, 100);
			
			jContentAjouterTheme.setLayout(null);
			jContentAjouterTheme.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter un thème", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
			jContentAjouterTheme.add(getlblIdAtelierTheme());
			jContentAjouterTheme.add(getlblLibelleTheme());
			jContentAjouterTheme.add(gettextIdLibelleTheme());
			jContentAjouterTheme.add(getcbx_AtelierTheme());
		}
		return jContentAjouterTheme;
	}
	
	public JPanel getjContentAjouterAtelier() {
		if (jContentAjouterAtelier == null) {
			jContentAjouterAtelier = new JPanel();
			jContentAjouterAtelier.setBounds(98, 84, 430, 124);
			jContentAjouterAtelier.setVisible(false);
			jContentAjouterAtelier.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter un atelier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			jContentAjouterAtelier.setLayout(null);
			jContentAjouterAtelier.add(getlblIdParticipant());
			jContentAjouterAtelier.add(getlblLabellDeLatelier());
			jContentAjouterAtelier.add(getlblNombreDePlaces());
			
			jContentAjouterAtelier.add(gettextIdParticipant());
			jContentAjouterAtelier.add(gettextLibelleAtelier());
			jContentAjouterAtelier.add(gettextNbPlaces());
		}
		return jContentAjouterAtelier;
	}
	
	public JPanel getjContentAjouterVacation() {
		if (jContentAjouterVacation == null) {
			jContentAjouterVacation = new JPanel();
			jContentAjouterVacation.setBounds(98, 84, 430, 130);
			jContentAjouterVacation.setLayout(null);
			jContentAjouterVacation.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter une vacation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
			jContentAjouterVacation.add(getlblIdAtelier());
			jContentAjouterVacation.add(getlblDateDebut());
			jContentAjouterVacation.add(getlblDateFin());
			jContentAjouterVacation.add(gettextDateDebut());
			jContentAjouterVacation.add(gettextDateFin());
			
			jContentAjouterVacation.add(getcbx_AtelierVacation());
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
	
	public JLabel getlblIdParticipant() {
		if (lblIdParticipant  == null) {
			lblIdParticipant = new JLabel("ID participant");
			lblIdParticipant.setBounds(10, 24, 97, 14);
		}
		return lblIdParticipant;
	}
	
	public JLabel getlblLabellDeLatelier() {
		if (lblLabellDeLatelier == null) {
			lblLabellDeLatelier = new JLabel("Labell\u00E9 de l'atelier");
			lblLabellDeLatelier.setBounds(10, 58, 97, 14);
		}
		return lblLabellDeLatelier;
	}

	public JLabel getlblNombreDePlaces() {
		if (lblNombreDePlaces == null) {
			lblNombreDePlaces = new JLabel("Nombre de places ");
			lblNombreDePlaces.setBounds(10, 92, 97, 14);	
		}
		return lblNombreDePlaces;
	}
	
	public JTextField gettextIdParticipant() {
		if (textIdParticipant == null) {
			textIdParticipant = new JTextField();
			textIdParticipant.setBounds(117, 21, 86, 20);
			
			textIdParticipant.setColumns(10);
		}
		return textIdParticipant;
	}
	
	public JTextField gettextLibelleAtelier() {
		if (textLibelleAtelier == null) {
			textLibelleAtelier = new JTextField();
			textLibelleAtelier.setBounds(117, 55, 290, 20);
			textLibelleAtelier.setColumns(10);
		}
		return textLibelleAtelier;
	}
	
	public JTextField gettextNbPlaces() {
		if (textNbPlaces == null) {
			textNbPlaces = new JTextField();
			textNbPlaces.setBounds(117, 89, 37, 20);
			textNbPlaces.setColumns(10);
		}
		return textNbPlaces;
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
			lblLibelleTheme.setBounds(10, 66, 97, 14);
		}
		return lblLibelleTheme;
	}
	
	public JTextField gettextIdLibelleTheme() {
		if (textIdLibelleTheme == null) {
			textIdLibelleTheme = new JTextField();
			textIdLibelleTheme.setColumns(10);
			textIdLibelleTheme.setBounds(117, 63, 290, 20);
		}
		return textIdLibelleTheme;
	}
	
	public JComboBox<String> getcbx_AtelierTheme() {
		if (cbx_AtelierTheme == null) {
			cbx_AtelierTheme = new JComboBox<String>();
			cbx_AtelierTheme.setBounds(117, 35, 200, 20);
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
	
	public JLabel getlblDateDebut() {
		if (lblDateDebut == null) {
			lblDateDebut = new JLabel("Date de début");
			lblDateDebut.setBounds(10, 66, 97, 14);
			
		}
		return lblDateDebut;
	}
	
	public JLabel getlblDateFin() {
		if (lblDateFin == null) {
			lblDateFin = new JLabel("Date de fin");
			lblDateFin.setBounds(10, 91, 97, 14);
			
		}
		return lblDateFin;
	}
	
	public JTextField gettextDateDebut() {
		if (textDateDebut == null) {
			textDateDebut = new JTextField();
			textDateDebut.setColumns(10);
			textDateDebut.setBounds(117, 63, 134, 20);
		}
		return textDateDebut;
	}
	
	
	public JTextField gettextDateFin() {
		if (textDateFin == null) {
			textDateFin = new JTextField();
			textDateFin.setColumns(10);
			textDateFin.setBounds(117, 91, 134, 20);
		}
		return textDateFin;
	}
	
	public JComboBox<String> getcbx_AtelierVacation() {
		if (cbx_AtelierVacation == null) {
			cbx_AtelierVacation = new JComboBox<String>();
			cbx_AtelierVacation.setBounds(117, 35, 200, 20);
		}
		return cbx_AtelierVacation;
	}
	
	public JButton getbtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.setBounds(208, 225, 102, 47);
			btnAnnuler.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					raz();
				}
			});
		}
		return btnAnnuler;
	}
	
	public JButton getbtnEnregistrer() {
		if (btnEnregistrer == null) {
			btnEnregistrer = new JButton("Enregistrer");
			btnEnregistrer.setBounds(320, 225, 102, 47);
			btnEnregistrer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (verification()) {
						// Cas Atelier
						if (rdbtnAtelier.isSelected()) {
							Integer idParticipant = gettextIdParticipant().getText().equals("") ? 0 : Integer.parseInt(gettextIdParticipant().getText());
							
							if (gestionBD.enregistrerAtelier(idParticipant, 
														 	textLibelleAtelier.getText(), 
														 	Integer.parseInt(textNbPlaces.getText()))) {
								JOptionPane.showMessageDialog(null,"L'atelier " + textLibelleAtelier.getText() + " a bien été ajouté" 
										,"Ajout atelier",JOptionPane.INFORMATION_MESSAGE);
								// Tiens à jour les listes déroulantes
								alimentationListes();
								raz();
							}
							else 
								JOptionPane.showMessageDialog(null,"L'insertion de l'atelier " + textLibelleAtelier.getText() + " ne s'est pas correctement déroulé, veuillez réessayer" 
										,"Erreur : Ajout atelier",JOptionPane.INFORMATION_MESSAGE);
						}
						
						// Cas Vacation
						else if (rdbtnVacation.isSelected()) {
							String strLabel[] = ((String)getcbx_AtelierVacation().getSelectedItem()).split(" ");
							int idAtelier = Integer.parseInt(strLabel[0]);
							
							if (gestionBD.enregistrerVacation(idAtelier, 
														 	gettextDateDebut().getText(), 
														 	gettextDateFin().getText())) {
								JOptionPane.showMessageDialog(null,"La vacation a bien été ajouté", "Ajout vacation",JOptionPane.INFORMATION_MESSAGE);
								// Tiens à jour les listes déroulantes
								raz();
							}
							else 
								JOptionPane.showMessageDialog(null,"L'insertion de la vacation ne s'est pas correctement déroulé, veuillez réessayer" 
										,"Erreur : Ajout vacation",JOptionPane.ERROR_MESSAGE);
						}
						
						// Cas Theme
						else {
							String strLabel[] = ((String)getcbx_AtelierTheme().getSelectedItem()).split(" ");
							int idAtelier = Integer.parseInt(strLabel[0]);
							
							if (gestionBD.enregistrerTheme(idAtelier, 
														 	gettextIdLibelleTheme().getText() )) {
								JOptionPane.showMessageDialog(null,"La thème " + gettextIdLibelleTheme().getText() + " a bien été ajouté", "Ajout thème",JOptionPane.INFORMATION_MESSAGE);
								// Tiens à jour les listes déroulantes
								raz();
							}
							else 
								JOptionPane.showMessageDialog(null,"L'insertion du thème ne s'est pas correctement déroulé, veuillez réessayer" 
										,"Erreur : Ajout thème",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			
		}
		return btnEnregistrer ;
	}
	
	// ################
	// ### Methodes ###
	// ################
	public void raz() {
		gettextLibelleAtelier().setText("");
		gettextIdParticipant().setText("");
		gettextNbPlaces().setText("");
		
		getcbx_AtelierVacation().setSelectedIndex(0);
		gettextDateDebut().setText("");
		gettextDateFin().setText("");
		
		getcbx_AtelierTheme().setSelectedIndex(0);
		gettextIdLibelleTheme().setText("");
	}
	
	/**
	 * Alimente les listes déroulantes des onglets Theme et Vacation
	 */
	public void alimentationListes() {
		int taille = listeAtel.Nbelement();
		
		getcbx_AtelierVacation().removeAllItems();
		getcbx_AtelierTheme().removeAllItems();
		
		getcbx_AtelierVacation().setMaximumRowCount(taille);
		getcbx_AtelierTheme().setMaximumRowCount(taille);
		for (int ind = 0 ; ind < taille; ind ++ )
		{
			getcbx_AtelierVacation().addItem(listeAtel.elt(ind).getNoatelier()+ " " +listeAtel.elt(ind).getLibelleatelier());
			getcbx_AtelierTheme().addItem(listeAtel.elt(ind).getNoatelier()+ " " +listeAtel.elt(ind).getLibelleatelier());
		}
	}
	
	public boolean verification() {
		if (getrdbtnAtelier().isSelected()) {
			if (gettextLibelleAtelier().getText().isEmpty() || gettextNbPlaces().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,"Vous devez saisir le libellé et le nombre de places maximum de l'atelier", "",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		if (getrdbtnVacation().isSelected()) {
			if (gettextDateDebut().getText().isEmpty() || gettextDateFin().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,"Vous devez saisir la date de début et de fin de la vacation", "",JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				if(!gettextDateDebut().getText().matches("[0-9]+") || !gettextDateDebut().getText().matches("[0-9]+")); {
					JOptionPane.showMessageDialog(null,"La date doit être du format YYYY-MM-JJ", "",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		
		if (getrdbtnThme().isSelected()) {
			if (gettextIdLibelleTheme().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,"Vous devez saisir le libellé du thème", "",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		return true;
	}
	
	
}
