package modell.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import modell.beans.User;

public class DataUser {

	public User getUser(String login, String password) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet resultSet = null;

		User usuario = null;
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ptmt = conn.prepareStatement(" SELECT * FROM USER WHERE LOGIN = ? AND PASSWORD=?");
			ptmt.setString(1, login);
			ptmt.setString(2, password);
			resultSet = ptmt.executeQuery(); //ptmt.executeUpdate() //for insert,update,delete

			if (resultSet.next()) {
				usuario = new User();
				usuario.setIduser(resultSet.getInt("iduser"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setPassword(resultSet.getString("password"));
			}
			resultSet.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("DataUser.getUser:"
					+ e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return usuario;
	}

	
}
