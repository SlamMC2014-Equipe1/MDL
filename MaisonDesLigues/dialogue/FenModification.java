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
import javax.swing.JOptionPane;

public class FenModification extends JPanel {
	private JTextField textDateDebut = null;
	private JTextField textDateFin = null;
	private JComboBox<String> cbx_ateliers = null;
	private JComboBox<String> cbx_vacations = null;
	private GestAtelierList listeAtel = null;
	private GestVacationList listVac = null;
	private GestionDemandes gestionBD = new GestionDemandes();
	private JButton btnAnnuler = null;
	private JButton btnEnregistrer = null;
	
	
	
	/**
	 * Create the panel.
	 */
	public FenModification() {
		setLayout(null);
		
		JPanel JContentModification = new JPanel();
		JContentModification.setBorder(new TitledBorder(null, "Modification", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JContentModification.setBounds(10, 11, 436, 249);
		add(JContentModification);
		JContentModification.setLayout(null);
		
		JLabel lblListeAteliers = new JLabel("Liste des ateliers");
		lblListeAteliers.setBounds(10, 48, 153, 14);
		JContentModification.add(lblListeAteliers);
		
		JContentModification.add(getCbx_ateliers());
		
		JLabel lblListeDesVacations = new JLabel("Liste des vacations");
		lblListeDesVacations.setBounds(10, 93, 153, 14);
		JContentModification.add(lblListeDesVacations);
		
		JContentModification.add(getCbx_vacations());
		
		JLabel lblDateDbut = new JLabel("Date de d\u00E9but");
		lblDateDbut.setBounds(10, 131, 153, 14);
		JContentModification.add(lblDateDbut);
		
		JLabel lblDateDeFin = new JLabel("Date de fin");
		lblDateDeFin.setBounds(10, 165, 153, 14);
		JContentModification.add(lblDateDeFin);
		

		JContentModification.add(getTextDateDebut());
		JContentModification.add(getTextDateFin());
		this.add(getBtnAnnuler());
		this.add(getBtnEnregistrer());
		
		
	}
	
	private JComboBox<String> getCbx_ateliers() {
		if (cbx_ateliers == null) {
		cbx_ateliers = new JComboBox<String>();
		cbx_ateliers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char idat = ((String)cbx_ateliers.getSelectedItem()).charAt(0);
				listVac=gestionBD.chargeVacation(Character.getNumericValue(idat));
				int taille = listVac.Nbelement();
				getCbx_vacations().removeAllItems();
				for (int ind = 0; ind < taille; ind ++) {
					String idvac = listVac.elt(ind).getNovacation().toString();
					try {
						getCbx_vacations().addItem(idvac);
					}
					catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			}
		});
		cbx_ateliers.setBounds(173, 45, 243, 20);
		listeAtel=gestionBD.chargeAtelier();
		int taille = listeAtel.Nbelement();
		cbx_ateliers.setMaximumRowCount(taille);
		for (int ind = 0; ind < taille; ind ++) {
			cbx_ateliers.addItem(listeAtel.elt(ind).getNoatelier()+ " " +listeAtel.elt(ind).getLibelleatelier());
		}
		}
		
		return cbx_ateliers;
	}
	
	private JComboBox<String> getCbx_vacations() {
		if(cbx_vacations == null) {
			cbx_vacations = new JComboBox<String>();
			cbx_vacations.setBounds(173, 90, 86, 20);
			cbx_vacations.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					char idat = ((String)getCbx_ateliers().getSelectedItem()).charAt(0);
					char idvac = cbx_vacations.getSelectedItem() == null ? 'v' : ((String)cbx_vacations.getSelectedItem()).charAt(0);
					
					if (idvac != 'v') {
						Vacation laVac = gestionBD.chargeDate(Character.getNumericValue(idat), Character.getNumericValue(idvac));
					
							String dated = laVac.getdated();
							String datef = laVac.getdatef();		
							try {
								getTextDateDebut().setText(dated);
								getTextDateFin().setText(datef);
							}
							catch (Exception ex) {
								System.out.println(ex.getMessage());
							}
						}
					}
			});
		}
		return cbx_vacations;
	}
	
	private JTextField getTextDateDebut() {
		if (textDateDebut==null) {
		textDateDebut = new JTextField();
		textDateDebut.setBounds(173, 128, 86, 20);
		textDateDebut.setColumns(10);
		}
		return textDateDebut;
	}
	
	private JTextField getTextDateFin() {
		if (textDateFin==null) {
		textDateFin = new JTextField();
		textDateFin.setBounds(173, 162, 86, 20);
		textDateFin.setColumns(10);
		}
		return textDateFin;
	}
	
	private JButton getBtnAnnuler() { 
		if (btnAnnuler==null) {
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getCbx_ateliers().setSelectedIndex(0);
				getCbx_vacations().setSelectedIndex(0);	
				textDateDebut.setText("");
				textDateFin.setText("");
			}
		});
		btnAnnuler.setBounds(242, 277, 99, 42);
		}
		return btnAnnuler;
	}
	
	private JButton getBtnEnregistrer() {
		if (btnEnregistrer==null){
		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBounds(341, 277, 105, 42);
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (getTextDateDebut().getText().equals("") || getTextDateFin().getText().equals("") || 
					!getTextDateDebut().getText().matches("[0-9]*" )|| !getTextDateFin().getText().matches("[0-9]*" ))
				{
					JOptionPane.showMessageDialog(null,"Impossible de faire la modification, vérifiez les champs !","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					String idat = Character.toString(((String)getCbx_ateliers().getSelectedItem()).charAt(0));	
					String idvac = Character.toString(((String)getCbx_vacations().getSelectedItem()).charAt(0));		
					String dated = getTextDateDebut().getText();
					String datef = getTextDateFin().getText();	
					gestionBD.majVacation(idat, idvac, dated, datef);
					textDateDebut.setText("");
					textDateFin.setText("");
					getCbx_ateliers().setSelectedIndex(0);
					getCbx_vacations().setSelectedIndex(0);
					JOptionPane.showMessageDialog(null,"Modification effectuée");
				}
			}
		});
		}
		return btnEnregistrer;
	}
}
