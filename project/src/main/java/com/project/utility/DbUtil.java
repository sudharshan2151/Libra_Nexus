package com.project.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DbUtil {
	static EntityManagerFactory emf ;
	
	static {
		emf = Persistence.createEntityManagerFactory("project");
		
	}
	public static EntityManager getConnection() {
		return emf.createEntityManager();
	}

}
