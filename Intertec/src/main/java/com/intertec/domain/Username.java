/**
 * 
 */
package com.intertec.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.intertec.util.RandomString;

import lombok.Data;

/**
 * @author joaco
 *
 */
@Entity
public @Data class Username {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	// constructors
	
	public Username(String username) {
		this.name = username;
	}
	
	public Username() {
	}
	
	// business rules

	public Boolean containWords(List<String> list){
		return list.stream().anyMatch(s -> this.getName().toUpperCase().indexOf(s.toUpperCase())!=-1);
	}
	
	public Collection<? extends String> getAlternatives(Integer count, Collection<String> invalidWords) {
		Username newUsername;
		String fixedUsername = this.getFixedName(this.getName(), invalidWords);
		List<String> alternatives = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			newUsername = new Username(this.getAlternative(fixedUsername));
			if(!newUsername.containWords((List<String>)invalidWords)){
				alternatives.add(newUsername.getName());
			}
		}
		return alternatives;
	}
	
	private String getAlternative(String username){
		RandomString generator = new RandomString(6);
		return username.concat(generator.nextString());
	}
	
	private String getFixedName(String name, Collection<String> invalidWords){
		if(name.length()>1){
			if(this.containWords((List<String>)invalidWords)){
				return getFixedName(name.substring(0, name.length()-1),invalidWords);
			}else{
				return name;	
			}
		}else{
			return new String("");
		}
	}
	
	
	// setters and getters

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
