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
//permet d établir le lien avec la classe GestionDemandes
import controle.GestionDemandes;
import entite.*;
import vues.*;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class FenParticipant extends JFrame {
	private static final long serialVersionUID = 1L;
	// propriété pour établir le lien avec la classe GestionDemandes
	private GestionDemandes gestionBD = new GestionDemandes();  
	private JPanel jContentInscription = null;

	/*** This is the default constructor */
	public FenParticipant() {
		super();
		initialize();
	}
	
	/** This method initializes this @return void */
	private void initialize() {
		this.setSize(810, 870);
		this.setContentPane(getJContentInscription());
		this.setTitle("Maison des Ligues : Inscription ");
	}
	
	private JPanel getJContentInscription() {
		if (jContentInscription == null) {
			jContentInscription = new JContentInscription();
		}
		return jContentInscription;
	}
	
}  //  @jve:decl-index=0:visual-constraint="16,5"
