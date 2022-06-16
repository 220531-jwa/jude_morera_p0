package dev.morera.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.morera.models.Account;
import dev.morera.models.Client;
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
	
	public List<Account> getAccountsFromClient(int passedId, String greater, String lesser){
		List<Account> accounts = new ArrayList<>();
		String sql  = "select * from accounts where owner_id = ?";
		boolean hasG = false;
		if (greater != null ) {
			sql += " and balance > ?";
			hasG = true;
		}
		if (lesser !=null ) {
			sql += "and balance < ?";
		}
		
//		else {
//		 sql = "select * from accounts where owner_id = ?";
//		}
		
		try (Connection conn = cu.getConnection()){

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, passedId);
				
			if (greater != null ) {
				Double greaterSQL = Double.parseDouble(greater);
				ps.setDouble(2, greaterSQL);
			}//grt
			if (lesser !=null ) {
				Double lesserSQL = Double.parseDouble(lesser);
				if (!hasG) {
					ps.setDouble(2, lesserSQL);
				}else {
					ps.setDouble(3, lesserSQL);
				}
			}//less

			
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

		}//end try
		catch(SQLException e) {
			e.printStackTrace();
		return null;
		}
		
		
	}

	public Account createAccount(int passedId, Account a) {

		String sql = "insert into Accounts values (default, ?, ?, ?) returning *";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, a.isSavings());
			ps.setDouble(2, a.getBalance());
			ps.setInt(3, passedId);
			
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

	public Account getSpecAccount(int passedId, int passedAid) {
		
		String sql = "select * from accounts where accounts.owner_id = ? and accounts.id = ? ";
		
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, passedId);
			ps.setInt(2, passedAid);
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				//				this.id = id;
				//				this.savings = savings;
				//				this.balance = balance;
				//				this.owner_id = owner_id;				

				int id = rs.getInt("id");
				boolean savings = rs.getBoolean("savings");
				double balance = rs.getDouble("balance");
				int owner_id = rs.getInt("owner_id");
			return new Account(id, savings, balance, owner_id);}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateAccount(int id, int aid, Account aChanged) {
		String sql = "update accounts set savings = ?, balance = ? where id = ?";
		
		try(Connection conn = cu.getConnection()){
			if ( getSpecAccount(id, aid) == null){
				return false;
			}
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, aChanged.isSavings());
			ps.setDouble(2, aChanged.getBalance());
			ps.setInt(3, aid);
			
			ps.executeUpdate();
			return true;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}return false;
		
		
		
	}

	public boolean deleteAccount(int id,int aid) {
		String sql = "delete from accounts where id = ?";
		try (Connection conn = cu.getConnection()){
			
			if (getSpecAccount(id, aid)==null) {
				return false;
			}
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, aid); //because these are primary, rest should be irrelevant
			ps.execute();
			return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
	}	
	
	public Account addMoney (int id, int aid, String[] parsedAction) {
		String sql = "update accounts set balance = balance + ? where id = ? returning *";
		
		try(Connection conn = cu.getConnection()){
			String cleaned = parsedAction[1].replace("-", "");
			double maths = Double.parseDouble(cleaned);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, maths);
			ps.setInt(2, aid);
			
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Account(
						rs.getInt("id"),
						rs.getBoolean("savings"),
						rs.getDouble("balance"),
						rs.getInt("owner_id")
						);
			}
			else {return null;}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
}//file


