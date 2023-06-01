package com.stackroute.orderservice.entity;

public class UserDTO{

   
	private Integer id;
	
	private String firstName;
	
    private String lastName;
	
    private String email;
	
    
    private String password;
    private UserType userType;

    
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
    
    

    public UserDTO(Integer id, String firstName, String lastName, String email, String password, UserType userType) {
    	 this.id = id;
    	 this.firstName = firstName;
    	 this.lastName = lastName;
    	 this.email = email;
    	 this.password = password;
    	 this.userType = userType;
    	 }

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
enum UserType {

	BUYER, SELLER
}
