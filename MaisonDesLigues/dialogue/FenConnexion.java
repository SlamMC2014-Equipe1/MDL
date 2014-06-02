package dialogue;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
//permet d établir le lien avec la classe ControleConnexion
import controle.ControleConnexion;
public class FenConnexion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton btn_connecter = null;
	private JButton btn_quitter = null;
	private JLabel jLabel_titre = null;
	private JLabel jLabel_identifiant = null;
	private JLabel jLabel_mdp = null;
	private JLabel jLabel_BD = null;
	private JLabel jLabel_driver = null;
	private JTextField txt_login = null;
	private JPasswordField txt_mdp = null;
	private JLabel lbl_serverBD = null;
	private JLabel lbl_driverSGBD = null;
	/** Cette methode initialise Btn_connecter @return javax.swing.JButton	*/
	private JButton getBtn_connecter() {
		if (btn_connecter == null) {
			btn_connecter = new JButton();
			btn_connecter.setBounds(new Rectangle(75, 110, 100, 30));
			btn_connecter.setText("Connexion");
			btn_connecter.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
				if(ControleConnexion.getControleConnexion().controle(txt_login.getText(),String.valueOf(txt_mdp.getPassword())))
				{
					ControleConnexion.getControleConnexion().connect();
					if(ControleConnexion.getControleConnexion().getEtatControleConnexion())
					{				
						FenConnexion.this.dispose() ;
						FenParticipant LaFenParticipant = new FenParticipant() ;
						LaFenParticipant.setVisible(true) ;
					}
				}
			}});
		}
		return btn_connecter;
	}
	/** Cette methode initialise Btn_quitter @return javax.swing.JButton	*/
	private JButton getBtn_quitter() {
		if (btn_quitter == null) {
			btn_quitter = new JButton();
			btn_quitter.setBounds(new Rectangle(200, 110, 100, 30));
			btn_quitter.setText("Quitter");
			btn_quitter.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FenConnexion.this.dispose() ;
					System.exit(0); 
				}
			});
			btn_quitter.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) 
				{
					if (e.getKeyCode()==10) 
					{
						FenConnexion.this.dispose() ;
						System.exit(0) ;
					}
				}
			});
		}
		return btn_quitter;
	}
	/** Cette methode initialise Txt_identifiant @return javax.swing.JTextField	*/
	private JTextField getTxt_login() {
		if (txt_login == null) {
			txt_login = new JTextField();
			txt_login.setBounds(new Rectangle(130, 30, 150, 30));
			txt_login.setText("GR1"); // à retirer
		}
		return txt_login;
	}
	/** Cette methode initialise Txt_mdp @return javax.swing.JPasswordField	*/
	private JPasswordField getTxt_mdp() {
		if (txt_mdp == null) {
			txt_mdp = new JPasswordField();
			txt_mdp.setBounds(new Rectangle(130, 60, 150, 30));
			txt_mdp.setText("GR1"); // à retirer
		}
		return txt_mdp;
	}
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FenConnexion thisClass = new FenConnexion();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
	/** default constructor */
	public FenConnexion() {
		super();
		initialize();
	}
	/*** Cette methode initialise this @return void */
	private void initialize() {
		this.setSize(397, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Maison des Ligues");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent e) {
				entite.Parametres lecontroleParametres = ControleConnexion.getControleConnexion().getParametres();
				// récuperation du serveur via ODBC et du driver
				lbl_serverBD.setText(lecontroleParametres.getServeurBD());
				lbl_driverSGBD.setText(lecontroleParametres.getDriverSGBD());
				// mise en cache des infos
				lbl_driverSGBD.setVisible(false);
				lbl_serverBD.setVisible(false);
				jLabel_driver.setVisible(false);
				jLabel_BD.setVisible(false);
			}
		});
	}
	/*** This method initializes jContentPane @return javax.swing.JPanel */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel_titre = new JLabel();
			jLabel_titre.setBounds(new Rectangle(10, 1, 300, 30));
			jLabel_titre.setText("Application Assises de l'Escrime");
			
			jLabel_identifiant = new JLabel();
			jLabel_identifiant.setBounds(new Rectangle(10, 30, 100, 30));
			jLabel_identifiant.setText("Login : ");
			
			jLabel_mdp = new JLabel();
			jLabel_mdp.setBounds(new Rectangle(10, 60, 100, 30));
			jLabel_mdp.setText("Mot de passe : ");
			
			jLabel_BD = new JLabel();
			jLabel_BD.setBounds(new Rectangle(10, 110, 100, 30));
			jLabel_BD.setText("Base de données");
			
			lbl_serverBD = new JLabel();
			lbl_serverBD.setBounds(new Rectangle(130, 110, 150, 30));
			lbl_serverBD.setText("");
			
			jLabel_driver = new JLabel();
			jLabel_driver.setBounds(new Rectangle(10,160, 100,30));
			jLabel_driver.setText("Driver");
			
			lbl_driverSGBD = new JLabel();
			lbl_driverSGBD.setBounds(new Rectangle(130, 160, 150, 30));
			lbl_driverSGBD.setText("");
						
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtn_connecter(), null);
			jContentPane.add(getBtn_quitter(), null);
			jContentPane.add(jLabel_titre, null);
			jContentPane.add(jLabel_identifiant, null);
			jContentPane.add(jLabel_mdp, null);
			jContentPane.add(jLabel_BD, null);
			jContentPane.add(jLabel_driver, null);
			jContentPane.add(getTxt_login(), null);
			jContentPane.add(getTxt_mdp(), null);
			jContentPane.add(lbl_serverBD, null);
			jContentPane.add(lbl_driverSGBD, null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
