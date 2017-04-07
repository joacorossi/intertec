/**
 * 
 */
package com.intertec.dto;

import com.intertec.domain.UsernameAvailable;

import lombok.Data;

/**
 * @author joaco
 *
 */
public @Data class UsernameValidationDto {
	
	private Boolean usernameAvailable;
	private String message;
	
	public UsernameValidationDto() {
	}

	public UsernameValidationDto(UsernameAvailable usernameStatus) {
		super();
		this.usernameAvailable = usernameStatus.equals(UsernameAvailable.AVAILABLE);
		this.message = usernameStatus.getMessage();
	}

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

	/**
	 * @return the usernameAvailable
	 */
	public Boolean getUsernameAvailable() {
		return usernameAvailable;
	}

	/**
	 * @param usernameAvailable the usernameAvailable to set
	 */
	public void setUsernameAvailable(Boolean usernameAvailable) {
		this.usernameAvailable = usernameAvailable;
	}
}
