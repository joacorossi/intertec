/**
 * 
 */
package com.intertec.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@Validated
@RestController
@RequestMapping("/saveusername")
public class UsernameController {

	@Autowired
	private IUsernameService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public UsernameValidationDto saveUsername(@Size(min=6, message="{$sizeErrorMessage}") @RequestParam (value ="username", required=true) String username){
		UsernameValidationDto answer;
		answer = this.service.saveUsername(username);
		return answer;
	}

}
