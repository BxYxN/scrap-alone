/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrap.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistenceManager {
  INSTANCE;
  
  private EntityManagerFactory emFactory;
  private PersistenceManager() {

    emFactory = Persistence.createEntityManagerFactory("SCRAP");
  }
  public EntityManager getEntityManager() {
    return emFactory.createEntityManager();
  }
  public void close() {
      if (emFactory!=null){
        emFactory.close();
      }
  }
}
