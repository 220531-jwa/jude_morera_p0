package dev.morera.models;

public class Client {
	
	private int id;
	private String username;
	private String pass_word;
	
	public Client() {
		super();
	}
	
	public Client(int id, String username, String pass_word) {
		super();
		this.id = id;
		this.username = username;
		this.pass_word = pass_word;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getpass_word() {
		return pass_word;
	}

	public void setpass_word(String pass_word) {
		this.pass_word = pass_word;
	}


//	@Override
//	public String toString() {
//		return "Client [id=" + id+"]";
//	}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", username=" + username + ", pass_word=" + pass_word + "]";
	}
	
}
