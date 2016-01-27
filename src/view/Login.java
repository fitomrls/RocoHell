package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import controller.login.UserController;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanelRoot;
	private JLabel jLMensaje;
	private JLabel jLClave;
	private JButton jBok;
	private JPasswordField jTFclave;
	private JLabel jLLogin;
	private JTextField jTfLogin;
	UserController userController;

	public Login() {
		super("Login");
		/*
		 * final Toolkit toolkit = Toolkit.getDefaultToolkit(); final Dimension
		 * screenSize = toolkit.getScreenSize(); final int x = (screenSize.width
		 * - loginUI.getWidth()) / 2; final int y = (screenSize.height -
		 * loginUI.getHeight()) / 2; loginUI.setLocation(x, y);
		 * loginUI.setVisible(true); loginUI.setLocationRelativeTo(null);
		 */
		initGUI();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		pack();
		setVisible(true);
	}

	private void initGUI() {

		jPanelRoot = new JPanel();
		getContentPane().add(jPanelRoot, BorderLayout.CENTER);
		jPanelRoot.setLayout(null);
		jPanelRoot.setPreferredSize(new java.awt.Dimension(353, 216));
		jPanelRoot.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		jPanelRoot.setForeground(new java.awt.Color(255, 255, 255));
		jPanelRoot.setBackground(new java.awt.Color(0, 128, 255));

		jLMensaje = new JLabel();
		jPanelRoot.add(jLMensaje);
		jLMensaje.setText("Autentificacion Requerida");
		jLMensaje.setBounds(75, 36, 239, 16);
		jLMensaje.setFont(new java.awt.Font("Segoe UI", 1, 16));

		jTfLogin = new JTextField();
		jPanelRoot.add(jTfLogin);
		jTfLogin.setText("admin");
		jTfLogin.setBounds(157, 82, 99, 29);

		jLLogin = new JLabel();
		jPanelRoot.add(jLLogin);
		jLLogin.setText("LOGIN :");
		jLLogin.setBounds(60, 88, 75, 16);

		jLClave = new JLabel();
		jPanelRoot.add(jLClave);
		jLClave.setText("CLAVE :");
		jLClave.setBounds(60, 128, 75, 16);

		jTFclave = new JPasswordField("admin");
		jPanelRoot.add(jTFclave);
		jTFclave.setBounds(157, 120, 99, 30);

		jBok = new JButton();
		jPanelRoot.add(jBok);
		jBok.setText("OK");
		jBok.setBounds(245, 167, 88, 31);
		jBok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificarCuenta();
			}
		});

	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			// @Override
			public void run() {
				Login login = new Login();
			}
		});
	}

	public void verificarCuenta() {
		String hash = "";
		try {
			hash = byteArrayToHexString(computeHash(jTFclave.getText() ));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 userController = new UserController(this);
		 userController.verificarCuenta(jTfLogin.getText(), hash);

	}

	private byte[] computeHash(String x) throws Exception {
		java.security.MessageDigest d = null;
		d = java.security.MessageDigest.getInstance("SHA-1");
		d.reset();
		d.update(x.getBytes());
		return d.digest();
	}

	private String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}
}
