package dialogue;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Panel;
import controle.GestionDemandes;
import entite.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenModification extends JPanel {
	private JTextField textDateDebut;
	private JTextField textDateFin;
	private JComboBox<String> cbx_ateliers;
	private JComboBox<String> cbx_vacations;
	private GestAtelierList listeAtel;
	private GestVacationList listVac;
	private GestionDemandes gestionBD = new GestionDemandes();
	private JButton btnQuitter;
	
	
	/**
	 * Create the panel.
	 */
	public FenModification() {
		setLayout(null);
		
		JPanel JContentModification = new JPanel();
		JContentModification.setBorder(new TitledBorder(null, "Modification", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JContentModification.setBounds(10, 11, 394, 249);
		add(JContentModification);
		JContentModification.setLayout(null);
		
		JLabel lblListeAteliers = new JLabel("Liste des ateliers");
		lblListeAteliers.setBounds(10, 48, 105, 14);
		JContentModification.add(lblListeAteliers);
		
		JContentModification.add(getCbx_ateliers());
		
		JLabel lblListeDesVacations = new JLabel("Liste des vacations");
		lblListeDesVacations.setBounds(10, 93, 105, 14);
		JContentModification.add(lblListeDesVacations);
		
		//JContentModification.add(getCbx_vacations());
		
		JLabel lblDateDbut = new JLabel("Date de d\u00E9but");
		lblDateDbut.setBounds(10, 131, 90, 14);
		JContentModification.add(lblDateDbut);
		
		JLabel lblDateDeFin = new JLabel("Date de fin");
		lblDateDeFin.setBounds(10, 165, 105, 14);
		JContentModification.add(lblDateDeFin);
		

		JContentModification.add(getTextDateDebut());
		JContentModification.add(getTextDateFin());
		this.add(getBtnquitter());

		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBounds(228, 277, 89, 42);
		add(btnEnregistrer);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(142, 277, 89, 42);
		add(btnAnnuler);
	}
	
	private JComboBox<String> getCbx_ateliers() {
		cbx_ateliers = new JComboBox<String>();
		cbx_ateliers.setBounds(125, 45, 243, 20);
		listeAtel=gestionBD.chargeAtelier();
		int taille = listeAtel.Nbelement();
		cbx_ateliers.setMaximumRowCount(taille);
		for (int ind = 0; ind < taille; ind ++) {
			cbx_ateliers.addItem(listeAtel.elt(ind).getNoatelier()+ " " +listeAtel.elt(ind).getLibelleatelier());
		}
		cbx_ateliers.setSelectedIndex(0);
		return cbx_ateliers;
	}
	
	private JComboBox<String> getCbx_vacations() {
		cbx_vacations = new JComboBox<String>();
		cbx_vacations.setBounds(125, 90, 243, 20);
		char indexSelectionne = ((String)cbx_ateliers.getSelectedItem()).charAt(0);
		listVac=gestionBD.chargeVacation(Character.getNumericValue(indexSelectionne));
		int taille = listVac.Nbelement();
		for (int ind = 0; ind < taille; ind ++) {
			cbx_vacations.addItem(listVac.elt(ind).getNovacation().toString());
		}
		return cbx_vacations;
	}
	
	private JTextField getTextDateDebut() {
		textDateDebut = new JTextField();
		textDateDebut.setBounds(125, 128, 86, 20);
		textDateDebut.setColumns(10);
		return textDateDebut;
	}
	
	private JTextField getTextDateFin() {
		textDateFin = new JTextField();
		textDateFin.setBounds(125, 162, 86, 20);
		textDateFin.setColumns(10);
		return textDateFin;
	}
	
	private JButton getBtnquitter() {
		btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(315, 277, 89, 42);
		return btnQuitter;
	}
}
