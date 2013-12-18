package net.simplicite_mc.roblikescake.simpliciteaddons.database;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;

import net.simplicite_mc.roblikescake.simpliciteaddons.config.Config;
import org.bukkit.plugin.Plugin;

import java.sql.*;

public class MariaDB {
	private final String user;
	private final String password;
	private final String host;
	private final int port;
	private final String database;

	private Connection connection;

	Plugin plugin = SimpliciteAddons.p;

	public MariaDB(String user, String password, String host, int port, String database) {
		this.user = Config.getDBUser;
		this.password = Config.getDBPassword;
		this.host = Config.getDBHost;
		this.port = Config.getDBPort;
		this.database = Config.getDBDatabase;
		this.connection = null;
	}

	public Connection openConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mariadb://" + host + port, user, password);
		} catch (ClassNotFoundException e) {
			plugin.getLogger().severe("Could not connect to MariaDb! Cause: " + e.getMessage());
		} catch (SQLException e) {
			plugin.getLogger().severe("JDBC Driver not found!");
		}
		return connection;
	}

	public boolean checkConnection() {
		return connection != null;
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				plugin.getLogger().severe("Error closing the MariaDB Connection!");
				e.printStackTrace();
			}
		}
	}

	public ResultSet querySQL(String query) {
		Connection con = null;

		if (checkConnection()) {
			con = getConnection();
		} else {
			con = openConnection();
		}

		Statement st = null;

		try {
			st = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		ResultSet rs = null;

		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeConnection();

		return rs;
	}

	public void updateSQL(String update) {

		Connection con = null;

		if (checkConnection()) {
			con = getConnection();
		} else {
			con = openConnection();
		}

		Statement st = null;

		try {
			st = con.createStatement();
			st.executeUpdate(update);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		closeConnection();
	}
}
