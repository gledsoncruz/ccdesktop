package sqltool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Acesso {

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;

	public Connection getDerbyConnection() throws ClassNotFoundException,
			SQLException {

		Connection connection = null;
		connection = DriverManager
				.getConnection("jdbc:derby:derby/CC_DB;create=true");
		return connection;

	}

	public Connection getOracleConnection() throws ClassNotFoundException,
			SQLException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String dbURL = "jdbc:oracle:thin:@labserver04:1521:ACAD";
		String usuario = "scott";
		String senha = "tiger";

		Class.forName(driver);
		connection = DriverManager.getConnection(dbURL, usuario, senha);
		
		return connection;
	}

	public Connection getAccessConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		
		String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
		String dbURL = "jdbc:odbc:nomeBanco";
		String usuario = "";
		String senha = "";

		Class.forName(driver);
		connection = DriverManager.getConnection(dbURL, usuario, senha);
		
		return connection;

	}

	public Connection getMySQLConnection() throws ClassNotFoundException,
			SQLException {

		String driver = "org.gjt.mm.mysql.Driver";
		String dbURL = "jdbc:mysql://localhost/exemplo?autoReconnet=true";
		String usuario = "";
		String senha = "";
		Class.forName(driver);
		connection = DriverManager.getConnection(dbURL, usuario, senha);
		return connection;
	}

	public ResultSet consultar(String sql) throws ClassNotFoundException,
			SQLException {

		connection = this.getDerbyConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

		return resultSet;

	}

	public int atualizar(String sql) throws ClassNotFoundException,
			SQLException {

		Connection connection = null;
		Statement statement = null;

		connection = this.getDerbyConnection();
		statement = connection.createStatement();
		int resultado = statement.executeUpdate(sql);
		return resultado;

	}

	public void close() throws ClassNotFoundException, SQLException {

		if (resultSet != null) {
			resultSet.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}
