package com.intertec.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.intertec.domain.Username;
import com.intertec.dto.UsernameValidationDto;
import com.intertec.dto.UsernameValidationWithOptionsDto;
import com.intertec.repositories.IUsernameRepository;

public class UsernameServiceTest {
	
	private static final int TRYCOUNT = 3;
	private static final int MINIMUNALTERNATIVESCOUNT = 14;
	
	private List<String> invalidWords;
	
	private String NAMEALREADYTAKEN = "jordan";
	private String NONTAKENNAME = "lebron";
	private String FORBIDEN = "pot";
	
	private Username usernameMock;
	
	@Mock
	IUsernameRepository usernameRepository;
	
	UsernameServiceImpl usernameService;

	@Before
	public void setUp() throws Exception {
		invalidWords = new ArrayList<String>();
		invalidWords.add("cannabis");
		invalidWords.add("pot");
		invalidWords.add("abuse");
		
		MockitoAnnotations.initMocks(this);
		
		usernameService = new UsernameServiceImpl();
		usernameService.setUsernameRepository(this.usernameRepository);		
		
		usernameService.setMINIMUNALTERNATIVESCOUNT(MINIMUNALTERNATIVESCOUNT);
		usernameService.setTRYCOUNT(TRYCOUNT);
		
		usernameService.setInvalidWords(invalidWords);
		
		usernameMock = new Username(NAMEALREADYTAKEN);
		List<Username> dbReturn = new ArrayList<>();
		List<Username> dbReturnBusy = new ArrayList<>();
		dbReturnBusy.add(usernameMock);
		
		when(usernameRepository.findByName(NAMEALREADYTAKEN)).thenReturn(dbReturnBusy);
		when(usernameRepository.findByName(NONTAKENNAME)).thenReturn(dbReturn);
	}

	@Test 
	public void testSaveTakenUsername() { 
		 UsernameValidationDto answerWithAlternatives = usernameService.saveUsername(NAMEALREADYTAKEN);
		 assertFalse("this must be an unsuccesfull scenario", answerWithAlternatives.getUsernameAvailable());
		 assertEquals("this must be a taken username and should generate 14 alternatives", MINIMUNALTERNATIVESCOUNT, ((UsernameValidationWithOptionsDto)answerWithAlternatives).getAlternativeUsernames().size());
	
	}
	
	@Test
	public void testSaveAvailableUsername() {	 
		UsernameValidationDto okAnswer = usernameService.saveUsername(NONTAKENNAME);
		assertTrue("this must be an unsuccesfull scenario", okAnswer.getUsernameAvailable());
	}
	
	@Test
	public void testSaveForbidenUsername() {
		UsernameValidationDto invalidAnswer = usernameService.saveUsername(FORBIDEN);
		 assertFalse("this must be an unsuccesfull scenario", invalidAnswer.getUsernameAvailable());
		 assertEquals("this must be a taken username and should generate 14 alternatives", MINIMUNALTERNATIVESCOUNT, ((UsernameValidationWithOptionsDto)invalidAnswer).getAlternativeUsernames().size());
		 
	}

}
