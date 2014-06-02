package dialogue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JFrame;



//permet d établir le lien avec la classe GestionDemandes
import controle.GestionDemandes;
import entite.*;
import vues.*;

public class FenParticipant extends JFrame {
	private static final long serialVersionUID = 1L;
	// propriété pour établir le lien avec la classe GestionDemandes
	private GestionDemandes gestionBD = new GestionDemandes();  
	private JContentInscription jContentInscription = null;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu mnGestion;
	private JMenuItem mntmQuitter;
	private JMenu mnAtelier;
	private JMenuItem mntmInscription;

	/*** This is the default constructor */
	public FenParticipant() {
		super();
		initialize();
	}
	
	/** This method initializes this @return void */
	private void initialize() {
		this.setSize(810, 920);
		setJMenuBar(getMenuBarPrincipal());
		this.setContentPane(getJContentInscription());
		this.setTitle("Maison des Ligues : Inscription ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JPanel getJContentInscription() {
		if (jContentInscription == null) {
			jContentInscription = new JContentInscription();
		}
		return jContentInscription;
	}
	
	private JMenuBar getMenuBarPrincipal() {
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
			menu.add(getMntmInscription());
			menu.add(getMntmQuitter());
		}
		return menu;
	}
	private JMenu getMnGestion() {
		if (mnGestion == null) {
			mnGestion = new JMenu("Gestion");
			mnGestion.add(getMnAtelier());
		}
		return mnGestion;
	}
	
	private JMenuItem getMntmQuitter() {
		if (mntmQuitter == null) {
			mntmQuitter = new JMenuItem("Quitter");
			mntmQuitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Recopie des collections dans la base de données
					if (!jContentInscription.getListeAtel().equals(null)) {
						if (gestionBD.majAtelier(jContentInscription.getListeAtel()))
						{	
							System.out.println("mise a jour de la base effectuée");
						}
					}
					FenParticipant.this.dispose();
	//				controle.ControleConnexion.getControleConnexion().fermetureSession();
					System.exit(0);
				}
			});
		}
		return mntmQuitter;
	}
	private JMenu getMnAtelier() {
		if (mnAtelier == null) {
			mnAtelier = new JMenu("Atelier");
			
			JMenuItem mntmAjout = new JMenuItem("Ajout");
			mnAtelier.add(mntmAjout);
			mntmAjout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getContentPane().revalidate();
					getContentPane().repaint();
					setContentPane(new FenAjouter());
				}
			});
			
			JMenuItem mntmModification = new JMenuItem("Modification");
			mnAtelier.add(mntmModification);
			mntmModification.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getContentPane().revalidate();
					getContentPane().repaint();
					setContentPane(new FenModification());
				}
			});
		}
		return mnAtelier;
	}
	private JMenuItem getMntmInscription() {
		if (mntmInscription == null) {
			mntmInscription = new JMenuItem("Inscription");
			mntmInscription.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getContentPane().revalidate();
					getContentPane().repaint();
					setContentPane(new JContentInscription());
				}
			});
		}
		return mntmInscription;
	}
}  //  @jve:decl-index=0:visual-constraint="16,5"
