package dialogue;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class FenGestion2 extends JPanel {

	/**
	 * Create the panel.
	 */
	public FenGestion2() {
		setLayout(null);
		
		JPanel JContentChoixAjouter = new JPanel();
		JContentChoixAjouter.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JContentChoixAjouter.setBounds(10, 11, 485, 76);
		add(JContentChoixAjouter);
		
		JRadioButton rdbtnAtelier = new JRadioButton("Atelier");
		JContentChoixAjouter.add(rdbtnAtelier);
		
		JRadioButton rdbtnThme = new JRadioButton("Th\u00E8me");
		JContentChoixAjouter.add(rdbtnThme);
		
		JRadioButton rdbtnVacation = new JRadioButton("Vacation");
		JContentChoixAjouter.add(rdbtnVacation);
		
		JPanel JContentAjouterAtelier = new JPanel();
		JContentAjouterAtelier.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter un atelier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JContentAjouterAtelier.setBounds(10, 115, 485, 297);
		add(JContentAjouterAtelier);
		JContentAjouterAtelier.setLayout(null);

	}
}
