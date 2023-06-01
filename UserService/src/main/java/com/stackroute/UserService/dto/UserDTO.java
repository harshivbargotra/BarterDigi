package com.stackroute.UserService.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class UserDTO{
	@Id
    @NotBlank(message = "id name can't be blank.")
    @NotNull(message = "id name can't be null.")
	private Integer id;
	@NotBlank(message = "First name can't be blank.")
	@NotNull(message = "First name can't be null.")
	private String firstName;
	@NotBlank(message = "Last name can't be blank.")
	@NotNull(message = "Last name can't be null.")
    private String lastName;
	@Email
    @NotNull(message = "Email should not be null")
    private String email;
	@NotBlank(message = "Password should not be blank.")
    @NotNull(message = "Password should not be null.")
    private String password;
    private UserType userType;

    public UserDTO() {
    }

    public UserDTO(Integer id, String firstName, String lastName, String email, String password, UserType userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
    
    @Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", userType=" + userType + "]";
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
enum UserType {

	BUYER, SELLER
}
