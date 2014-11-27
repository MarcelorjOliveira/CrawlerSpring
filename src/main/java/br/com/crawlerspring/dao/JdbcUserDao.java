package br.com.crawlerspring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.crawlerspring.connection.ConnectionFactory;
import br.com.crawlerspring.model.User;
import org.apache.commons.codec.digest.DigestUtils;

public class JdbcUserDao {
	private Connection connection;

	public JdbcUserDao() {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean existUser(User usuario) {
		
		if(usuario == null) {
			throw new IllegalArgumentException("Usuário não deve ser nulo");
		}
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from Users where login = ? and password = ?");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, DigestUtils.md5Hex(usuario.getPassword()) );
			ResultSet rs = stmt.executeQuery();

			boolean encontrado = rs.next();
			rs.close();
			stmt.close();

			return encontrado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

    public void createUser(User user) {
   		
		if(user == null) {
			throw new IllegalArgumentException("Usuário não deve ser nulo");
		}
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("insert into Users " +
                                "(login, password) " +
                                " values (?, ?)" );
			stmt.setString(1, user.getLogin());
			stmt.setString(2, DigestUtils.md5Hex(user.getPassword()));
                        
                        stmt.execute();
                        stmt.close();
                        
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
 }
}

