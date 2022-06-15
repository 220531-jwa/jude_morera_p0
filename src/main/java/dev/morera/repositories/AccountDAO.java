package dev.morera.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.morera.models.Account;

import dev.morera.utils.ConnectionUtility;

public class AccountDAO {

	private static ConnectionUtility cu = ConnectionUtility.getConnectionUtility();

	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<>();

		String sql = "select * from accounts";

		try (Connection conn = cu.getConnection()){

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				//				this.id = id;
				//				this.savings = savings;
				//				this.balance = balance;
				//				this.owner_id = owner_id;				

				int id = rs.getInt("id");
				boolean savings = rs.getBoolean("savings");
				double balance = rs.getDouble("balance");
				int owner_id = rs.getInt("owner_id");

				Account a = new Account(id, savings, balance, owner_id);
				System.out.println(a);
				accounts.add(a);


			}
			return accounts;

		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Account> getAccountsFromClient(int passedId){
		List<Account> accounts = new ArrayList<>();
		
		String sql = "select * from accounts where owner_id = ?";
		
		
		try (Connection conn = cu.getConnection()){

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, passedId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				//				this.id = id;
				//				this.savings = savings;
				//				this.balance = balance;
				//				this.owner_id = owner_id;				

				int id = rs.getInt("id");
				boolean savings = rs.getBoolean("savings");
				double balance = rs.getDouble("balance");
				int owner_id = rs.getInt("owner_id");

				Account a = new Account(id, savings, balance, owner_id);
				System.out.println(a);
				accounts.add(a);


			}
			return accounts;

		} catch(SQLException e) {
			e.printStackTrace();
		return null;
		}
	}

	public Account createAccount(Account a) {

		String sql = "insert into Accounts values (default, ?, ?, ?) returning *";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, a.isSavings());
			ps.setDouble(2, a.getBalance());
			ps.setInt(3, a.getOwner_id());
			
			System.out.println(ps);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return new Account(
						rs.getInt("id"),
						rs.getBoolean("savings"),
						rs.getDouble("balance"),
						rs.getInt("owner_id")
						);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}	
}//file


