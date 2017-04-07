/**
 * 
 */
package com.intertec.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author joaco
 *
 */
public class UsernameTest {
	
	private List<String> invalidWords;
	private Username username;
	private int count = 14;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// fullfil with invalid words
		invalidWords = new ArrayList<String>();
		invalidWords.add("cannabis");
		invalidWords.add("abuse");
		invalidWords.add("crack");
		invalidWords.add("damn");
		invalidWords.add("drunk");
		invalidWords.add("grass");
	}

	/**
	 * Test method for {@link com.intertec.domain.Username#containWords(java.util.Collection)}.
	 */
	@Test
	public void testContainWords() {
		// create a Username instancek with a valid word
		username = new Username("Lebron");
		assertFalse("this username doesn't contain an invalid word and fail", username.containWords(invalidWords));
		
		// create a Username instancek with a invalid word
		username = new Username("cannabis");
		assertTrue("this username does contain an exact invalid word and must fail", username.containWords(invalidWords));
		
		// create a Username instancek with a word containing an invalid word
		username = new Username("someCannabisSome");
		assertTrue("this username does contain an invalid word and must fail", username.containWords(invalidWords));
	}

	/**
	 * Test method for {@link com.intertec.domain.Username#getAlternatives(java.lang.Integer, java.util.Collection)}.
	 */
	@Test
	public void testGetAlternatives() {
		username = new Username("Lebron");
		@SuppressWarnings("unchecked")
		List<String> alternatives = (List<String>) username.getAlternatives(this.count, this.invalidWords);
		assertEquals(this.count, alternatives.size());
	}

}
