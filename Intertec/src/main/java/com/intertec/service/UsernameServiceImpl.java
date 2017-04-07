/**
 * 
 */
package com.intertec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.intertec.domain.Username;
import com.intertec.domain.UsernameAvailable;
import com.intertec.dto.UsernameValidationDto;
import com.intertec.dto.UsernameValidationWithOptionsDto;
import com.intertec.repositories.IUsernameRepository;

/**
 * @author joaco
 *
 */
@Service
public class UsernameServiceImpl implements IUsernameService{
	
	@Value("${alternativesCount}")
	private int MINIMUNALTERNATIVESCOUNT;
	
	@Value("${trycount}")
	private int TRYCOUNT;
	
	@Value("${forbidenWords}")
	private List<String> invalidWords;
	
	@Autowired
	private IUsernameRepository usernameRepository;
	
	@Override
	public UsernameValidationDto saveUsername(String username) {
		UsernameValidationDto result;
		Username uname = new Username(username);
		UsernameAvailable usernameStatus = this.validateUsername(uname);		
		if(usernameStatus.equals(UsernameAvailable.AVAILABLE)){
			System.out.println("valid username - saving..");
			result = new UsernameValidationDto(usernameStatus);		
			this.getUsernameRepository().save(uname);	
		}else{
			result = new UsernameValidationWithOptionsDto(usernameStatus,this.getAlternatives(uname));
			System.out.println("invalid username - generating alternatives..");
		}
		return result;
		
	}
	
	private List<String> getAlternatives(Username username){
		int i = 0;
		List<String> alternatives = new ArrayList<String>();
		do{
			alternatives.addAll(username.getAlternatives(MINIMUNALTERNATIVESCOUNT, invalidWords));
			i++;
		}while(alternatives.size()<MINIMUNALTERNATIVESCOUNT && i<TRYCOUNT);
		return alternatives;
	}
	
	private UsernameAvailable validateUsername(Username username){
		if(this.isUsernameAvailable(username)){
			return this.containInvalidWords(username);
		}else{
			return UsernameAvailable.TAKEN;
		}
	}
	
	
	private UsernameAvailable containInvalidWords(Username username) {
		if(username.containWords((List<String>)this.invalidWords)){
			return UsernameAvailable.FORBIDDEN;
		}else{
			return UsernameAvailable.AVAILABLE;
		}
	}


	private Boolean isUsernameAvailable(Username username){
		List<Username> result = (List<Username>) this.getUsernameRepository().findByName(username.getName());
		return result.isEmpty();
	}
	
	// Setters and Getters

	/**
	 * @return the usernameRepository
	 */
	public IUsernameRepository getUsernameRepository() {
		return usernameRepository;
	}

	/**
	 * @param usernameRepository the usernameRepository to set
	 */
	public void setUsernameRepository(IUsernameRepository usernameRepository) {
		this.usernameRepository = usernameRepository;
	}


	/**
	 * @return the invalidWords
	 */
	public List<String> getInvalidWords() {
		return invalidWords;
	}


	/**
	 * @param invalidWords the invalidWords to set
	 */
	public void setInvalidWords(List<String> invalidWords) {
		this.invalidWords = invalidWords;
	}

	/**
	 * @return the mINIMUNALTERNATIVESCOUNT
	 */
	public int getMINIMUNALTERNATIVESCOUNT() {
		return MINIMUNALTERNATIVESCOUNT;
	}

	/**
	 * @param mINIMUNALTERNATIVESCOUNT the mINIMUNALTERNATIVESCOUNT to set
	 */
	public void setMINIMUNALTERNATIVESCOUNT(int mINIMUNALTERNATIVESCOUNT) {
		MINIMUNALTERNATIVESCOUNT = mINIMUNALTERNATIVESCOUNT;
	}

	/**
	 * @return the tRYCOUNT
	 */
	public int getTRYCOUNT() {
		return TRYCOUNT;
	}

	/**
	 * @param tRYCOUNT the tRYCOUNT to set
	 */
	public void setTRYCOUNT(int tRYCOUNT) {
		TRYCOUNT = tRYCOUNT;
	}


}
