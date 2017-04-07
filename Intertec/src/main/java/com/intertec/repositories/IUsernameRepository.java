/**
 * 
 */
package com.intertec.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intertec.domain.Username;

/**
 * @author joaco
 *
 */
public interface IUsernameRepository extends JpaRepository<Username, Long> {
	
	Collection<Username> findByName(String name);
}
