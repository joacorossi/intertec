/**
 * 
 */
package com.intertec.dto;

import java.util.ArrayList;
import java.util.List;

import com.intertec.domain.UsernameAvailable;

import lombok.Data;

/**
 * @author joaco
 *
 */
public @Data class UsernameValidationWithOptionsDto extends UsernameValidationDto {

	private List<String> alternativeUsernames;
	
	public UsernameValidationWithOptionsDto(){
		this.setAlternativeUsernames(new ArrayList<String>());
	}

	public UsernameValidationWithOptionsDto(UsernameAvailable usernameStatus, List<String> alternatives) {	
		super(usernameStatus);
		alternatives.sort((p1, p2) -> p1.compareTo(p2));
		this.alternativeUsernames = alternatives;
	}

	/**
	 * @return the alternativeUsernames
	 */
	public List<String> getAlternativeUsernames() {
		return alternativeUsernames;
	}

	/**
	 * @param alternativeUsernames the alternativeUsernames to set
	 */
	public void setAlternativeUsernames(List<String> alternativeUsernames) {
		this.alternativeUsernames = alternativeUsernames;
	}
}
