package dev.morera.repositories;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import dev.morera.models.Client;
import dev.morera.utils.ConnectionUtility;

public class ClientDAO {

	private static ConnectionUtility cu = ConnectionUtility.getConnectionUtility();

	public List<Client> getAllClients(){

		List<Client> clients = new ArrayList<>();

		String sql = "select * from clients";
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String pass_word = rs.getString("pass_word");

				Client c = new Client (id, username, pass_word);
				clients.add(c);
			}
			return clients;

		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Client getClientById(int id) {

		String sql = "select * from clients where id = ?";

		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Client(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("pass_word")
						);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return null;
	}

	public Client createClient(Client c) {

		String sql = "insert into clients values (default, ? , ? ) returning *";

		try(Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getUsername());
			ps.setString(2, c.getpass_word());
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Client(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("pass_word")
						);

			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
