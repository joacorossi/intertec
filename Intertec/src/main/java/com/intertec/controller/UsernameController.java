/**
 * 
 */
package com.intertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intertec.dto.UsernameValidationDto;
import com.intertec.service.IUsernameService;

/**
 * @author joaco
 *
 */
@RestController
@RequestMapping("/saveusername")
public class UsernameController {
	
	@Autowired
	private IUsernameService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public UsernameValidationDto saveUsername(@RequestParam (value ="username") String username){
		UsernameValidationDto answer;
		answer = this.service.saveUsername(username);
		return answer;
	}

}
