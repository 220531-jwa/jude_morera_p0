package dev.morera.models;

public class Account {

	private int id;
	private boolean savings;
	private double balance;
	private int owner_id;
	
	public Account() {
		super();
	}
	
	public Account(int id, boolean savings, double balance, int owner_id) {
		super();
		this.id = id;
		this.savings = savings;
		this.balance = balance;
		this.owner_id = owner_id;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean isSavings() {
		return savings;
	}


	public void setSavings(boolean savings) {
		this.savings = savings;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public int getOwner_id() {
		return owner_id;
	}


	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
}
