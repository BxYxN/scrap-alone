/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrap.dao;

import com.scrap.model.RawPage;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Bryan
 */
public class RawPageDao {
    
    private EntityManager _entity;
    
    public RawPageDao(EntityManager entity){
       this._entity = entity;
    }
    
    
    public void SaveRawYapo(RawPage page){
        _entity.getTransaction().begin();
        _entity.persist(page);
        _entity.getTransaction().commit();   
    }
    
    
    public List<RawPage> getAllRaw(){
      Query q =  _entity.createNamedQuery("RawPage.findAll",RawPage.class);
      return q.getResultList();
    }
    
}
