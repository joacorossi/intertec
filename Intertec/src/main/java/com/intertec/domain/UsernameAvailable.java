/**
 * 
 */
package com.intertec.domain;

/**
 * @author joaco
 *
 */
public enum UsernameAvailable {
	
	AVAILABLE("The username is available"),
	TAKEN("The username has been already taken"),
	FORBIDDEN("The username contain a forbidden word");
	
	private String message;
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	UsernameAvailable(String message){
		this.message = message;
	}

}
