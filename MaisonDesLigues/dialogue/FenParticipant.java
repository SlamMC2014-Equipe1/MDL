package dialogue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JFrame;


//permet d �tablir le lien avec la classe GestionDemandes
import controle.GestionDemandes;
import entite.*;
import vues.*;

public class FenParticipant extends JFrame {
	private static final long serialVersionUID = 1L;
	// propri�t� pour �tablir le lien avec la classe GestionDemandes
	private GestionDemandes gestionBD = new GestionDemandes();  
	private JPanel jContentInscription = null;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu mnGestion;
	private JMenuItem mntmQuitter;

	/*** This is the default constructor */
	public FenParticipant() {
		super();
		initialize();
	}
	
	/** This method initializes this @return void */
	private void initialize() {
		this.setSize(810, 870);
		setJMenuBar(getMenuBar_1());
		this.setContentPane(getJContentInscription());
		this.setTitle("Maison des Ligues : Inscription ");
	}
	
	private JPanel getJContentInscription() {
		if (jContentInscription == null) {
			jContentInscription = new JContentInscription();
		}
		return jContentInscription;
	}
	
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMenu());
			menuBar.add(getMnGestion());
		}
		return menuBar;
	}
	private JMenu getMenu() {
		if (menu == null) {
			menu = new JMenu("Accueil");
			menu.add(getMntmQuitter());
		}
		return menu;
	}
	private JMenu getMnGestion() {
		if (mnGestion == null) {
			mnGestion = new JMenu("Gestion");
			
			JMenuItem mntmAjout = new JMenuItem("Ajout");
			mntmAjout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getContentPane().revalidate();
					getContentPane().repaint();
					setContentPane(new FenAjouter());
					
				}
			});
			mnGestion.add(mntmAjout);
			
			JMenuItem mntmModification = new JMenuItem("Modification");
			mntmModification.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getContentPane().revalidate();
					getContentPane().repaint();
					setContentPane(new FenModification());
				}
			});
			mnGestion.add(mntmModification);
		}
		return mnGestion;
	}
	
	private JMenuItem getMntmQuitter() {
		if (mntmQuitter == null) {
			mntmQuitter = new JMenuItem("Quitter");
			mntmQuitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Recopie des collections dans la base de donn�es
					/*if (gestionBD.majAtelier(listeAtel))
					{	
						System.out.println("mise a jour de la base effectu�e");
					}*/
					FenParticipant.this.dispose() ;
	//				controle.ControleConnexion.getControleConnexion().fermetureSession();
					System.exit(0);
				}
			});
		}
		return mntmQuitter;
	}
}  //  @jve:decl-index=0:visual-constraint="16,5"
