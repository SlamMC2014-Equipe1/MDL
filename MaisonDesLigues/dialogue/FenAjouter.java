package dialogue;

import java.awt.Button;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import controle.GestionAjouter;
import javax.swing.ButtonGroup;

public class FenAjouter extends JPanel {
	private JTextField textIdParticipant;
	private JTextField textLibelleAtelier;
	private JTextField textNbPlaces;
	private JTextField textIdVacation;
	private JTextField textDateDebut;
	private JTextField textIdTheme;
	private JTextField textIdLibelleTheme;
	private ButtonGroup groupeboutons = new ButtonGroup(); 
	private JRadioButton rdbtnAtelier = null;
	private JRadioButton rdbtnThme = null;
	private JRadioButton rdbtnVacation = null;
	private JPanel JContentPrincipal = null;
	
	public JTextField getTextIdParticipant() {
		return textIdParticipant;
	}

	public JTextField getTextLibelleAtelier() {
		return textLibelleAtelier;
	}

	public JTextField getTextNbPlaces() {
		return textNbPlaces;
	}

	public JTextField getTextIdVacation() {
		return textIdVacation;
	}

	public JTextField getTextDateDebut() {
		return textDateDebut;
	}

	public JTextField getTextIdTheme() {
		return textIdTheme;
	}

	public JTextField getTextIdLibelleTheme() {
		return textIdLibelleTheme;
	}

	public JTextField getTextDateFin() {
		return textDateFin;
	}

	private JTextField textDateFin;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public FenAjouter() {
		setLayout(null);
		
		JPanel JContentPrincipal = new JPanel();
		JContentPrincipal.setBounds(0, 0, 592, 509);
		add(JContentPrincipal);
		JContentPrincipal.setLayout(null);
		
		JPanel jContentChoixAjouter = new JPanel();
		jContentChoixAjouter.setBounds(98, 5, 430, 56);
		JContentPrincipal.add(jContentChoixAjouter);
		jContentChoixAjouter.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JRadioButton rdbtnAtelier_1 = new JRadioButton("Atelier");
		jContentChoixAjouter.add(rdbtnAtelier_1);
		
		JRadioButton rdbtnThme_1 = new JRadioButton("Th\u00E8me");
		jContentChoixAjouter.add(rdbtnThme_1);
		
		JRadioButton rdbtnVacation_1 = new JRadioButton("Vacation");
		jContentChoixAjouter.add(rdbtnVacation_1);
		
		JPanel jContentAjouterAtelier = new JPanel();
		jContentAjouterAtelier.setBounds(98, 84, 430, 164);
		JContentPrincipal.add(jContentAjouterAtelier);
		jContentAjouterAtelier.setVisible(false);
		jContentAjouterAtelier.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter un atelier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jContentAjouterAtelier.setLayout(null);
		
		JLabel lblIdDeLatelier = new JLabel("ID de l'atelier ");
		lblIdDeLatelier.setBounds(10, 38, 97, 14);
		jContentAjouterAtelier.add(lblIdDeLatelier);
		
		JLabel lblIdParticipant = new JLabel("ID participant");
		lblIdParticipant.setBounds(10, 72, 97, 14);
		jContentAjouterAtelier.add(lblIdParticipant);
		
		JLabel lblLibellDeLatelier = new JLabel("Libell\u00E9 de l'atelier");
		lblLibellDeLatelier.setBounds(10, 106, 97, 14);
		jContentAjouterAtelier.add(lblLibellDeLatelier);
		
		JLabel lblNombreDePlaces = new JLabel("Nombre de places ");
		lblNombreDePlaces.setBounds(10, 140, 97, 14);
		jContentAjouterAtelier.add(lblNombreDePlaces);
		
		textIdParticipant = new JTextField();
		textIdParticipant.setBounds(117, 69, 86, 20);
		jContentAjouterAtelier.add(textIdParticipant);
		textIdParticipant.setColumns(10);
		
		textLibelleAtelier = new JTextField();
		textLibelleAtelier.setBounds(117, 103, 290, 20);
		jContentAjouterAtelier.add(textLibelleAtelier);
		textLibelleAtelier.setColumns(10);
		
		textNbPlaces = new JTextField();
		textNbPlaces.setBounds(117, 137, 37, 20);
		jContentAjouterAtelier.add(textNbPlaces);
		textNbPlaces.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(117, 35, 86, 20);
		jContentAjouterAtelier.add(textField);
		textField.setColumns(10);
		
		JPanel jContentAjouterTheme = new JPanel();
		jContentAjouterTheme.setBounds(98, 84, -430, 194);
		JContentPrincipal.add(jContentAjouterTheme);
		jContentAjouterTheme.setLayout(null);
		jContentAjouterTheme.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter un thème", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblIdAtelierTheme = new JLabel("ID de l'atelier ");
		lblIdAtelierTheme.setBounds(10, 38, 97, 14);
		jContentAjouterTheme.add(lblIdAtelierTheme);
		
		JLabel lblLibelleTheme = new JLabel("Libell\u00E9 du th\u00E8me");
		lblLibelleTheme.setBounds(10, 106, 97, 14);
		jContentAjouterTheme.add(lblLibelleTheme);
		
		textIdTheme = new JTextField();
		textIdTheme.setColumns(10);
		textIdTheme.setBounds(117, 69, 134, 20);
		jContentAjouterTheme.add(textIdTheme);
		
		textIdLibelleTheme = new JTextField();
		textIdLibelleTheme.setColumns(10);
		textIdLibelleTheme.setBounds(117, 103, 290, 20);
		jContentAjouterTheme.add(textIdLibelleTheme);
		
		JLabel lblIdTheme = new JLabel("ID du th\u00E8me");
		lblIdTheme.setBounds(10, 72, 59, 14);
		jContentAjouterTheme.add(lblIdTheme);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(117, 35, 134, 20);
		jContentAjouterTheme.add(comboBox);
		
		
		JPanel jContentAjouterVacation = new JPanel();
		jContentAjouterVacation.setBounds(98, 276, 430, -165);
		JContentPrincipal.add(jContentAjouterVacation);
		jContentAjouterVacation.setLayout(null);
		jContentAjouterVacation.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter une vacation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblIdAtelier = new JLabel("ID de l'atelier");
		lblIdAtelier.setBounds(10, 38, 97, 14);
		jContentAjouterVacation.add(lblIdAtelier);
		
		JLabel lblVacation = new JLabel("ID vacation");
		lblVacation.setBounds(10, 72, 97, 14);
		jContentAjouterVacation.add(lblVacation);
		
		JLabel lblDateDebut = new JLabel("Date de début");
		lblDateDebut.setBounds(10, 106, 97, 14);
		jContentAjouterVacation.add(lblDateDebut);
		
		JLabel lblDateFin = new JLabel("Date de fin");
		lblDateFin.setBounds(10, 134, 97, 14);
		jContentAjouterVacation.add(lblDateFin);
		
		textIdVacation = new JTextField();
		textIdVacation.setColumns(10);
		textIdVacation.setBounds(117, 69, 134, 20);
		jContentAjouterVacation.add(textIdVacation);
		
		textDateDebut = new JTextField();
		textDateDebut.setColumns(10);
		textDateDebut.setBounds(117, 103, 64, 20);
		jContentAjouterVacation.add(textDateDebut);
		
		textDateFin = new JTextField();
		textDateFin.setColumns(10);
		textDateFin.setBounds(117, 134, 64, 20);
		jContentAjouterVacation.add(textDateFin);
		
		JComboBox cbx_Atelier = new JComboBox();
		cbx_Atelier.setBounds(117, 35, 134, 20);
		jContentAjouterVacation.add(cbx_Atelier);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(203, 442, 102, 47);
		JContentPrincipal.add(btnAnnuler);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBounds(303, 442, 102, 47);
		JContentPrincipal.add(btnEnregistrer);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(403, 442, 102, 47);
		JContentPrincipal.add(btnQuitter);
		
	}
}
