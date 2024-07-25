package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
public class user {

    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private long uid;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;
    @Override
	public String toString() {
		return "user [uid=" + uid + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
	private String email;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 8,message = "Password must be at least 6 characters long")
	private String password;
   
}