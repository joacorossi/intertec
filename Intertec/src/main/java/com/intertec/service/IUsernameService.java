/**
 * 
 */
package com.intertec.service;

import com.intertec.dto.UsernameValidationDto;

/**
 * @author joaco
 *
 */
public interface IUsernameService {
	
	public UsernameValidationDto saveUsername(String username);

}
