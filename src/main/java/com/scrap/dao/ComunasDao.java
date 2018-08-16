/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrap.dao;

import javax.persistence.EntityManager;

/**
 *
 * @author Bryan
 */
public class ComunasDao {
    private EntityManager _entity;
    public ComunasDao(EntityManager entity){
        this._entity = entity; 
    }
}
