package controller.login;

import javax.swing.JOptionPane;

import modell.beans.User;
import modell.factory.DataUser;
import view.Login;
import view.admin.Admin;

public class UserController {

	Login loginUI;

	public UserController(Login login) {
		this.loginUI = login;
		;
	}

	public void verificarCuenta(String login, String clave) {
		DataUser factUsuario = new DataUser();
		User usuario = factUsuario.getUser(login.trim(), clave);
		if (usuario != null) {
			Admin adminUi = new Admin(usuario);
			loginUI.setVisible(false);

		} else {
			JOptionPane
					.showMessageDialog(loginUI, "Login o Clave Incorrectos!");
		}
	}
}
