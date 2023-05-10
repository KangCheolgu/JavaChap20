package ch20.sec09.exam01;

import java.util.Objects;

public class User {

	private String userId;
	private String userName;
	private String userPassword;
	private int userAge;
	private String userEmail;
	
	public User() {
	}

	public User(String userId, String userName, String userPassword, int userAge, String userEmail) {
		
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userAge = userAge;
		this.userEmail = userEmail;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public int getUserAge() {
		return userAge;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userAge, userEmail, userId, userName, userPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return userAge == other.userAge && Objects.equals(userEmail, other.userEmail)
				&& Objects.equals(userId, other.userId) && Objects.equals(userName, other.userName)
				&& Objects.equals(userPassword, other.userPassword);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userAge="
				+ userAge + ", userEmail=" + userEmail + "]";
	}
	
	
}
