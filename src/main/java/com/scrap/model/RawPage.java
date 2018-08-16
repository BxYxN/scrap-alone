/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrap.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bryan
 */
@Entity
@Table(name = "RAW_PAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RawPage.findAll", query = "SELECT r FROM RawPage r")
    , @NamedQuery(name = "RawPage.findByIdAd", query = "SELECT r FROM RawPage r WHERE r.idAd = :idAd")
    , @NamedQuery(name = "RawPage.findByDeta", query = "SELECT r FROM RawPage r WHERE r.deta = :deta")})
public class RawPage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_AD")
    private Long idAd;
    @Column(name = "DETA")
    private String deta;
    @Lob
    @Column(name = "RAW")
    private byte[] raw;

    public RawPage() {
    }

    public RawPage(Long idAd) {
        this.idAd = idAd;
    }

    public Long getIdAd() {
        return idAd;
    }

    public void setIdAd(Long idAd) {
        this.idAd = idAd;
    }

    public String getDeta() {
        return deta;
    }

    public void setDeta(String deta) {
        this.deta = deta;
    }

    public byte[] getRaw() {
        return raw;
    }

    public void setRaw(byte[] raw) {
        this.raw = raw;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAd != null ? idAd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RawPage)) {
            return false;
        }
        RawPage other = (RawPage) object;
        if ((this.idAd == null && other.idAd != null) || (this.idAd != null && !this.idAd.equals(other.idAd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scrap.model.RawPage[ idAd=" + idAd + " ]";
    }
    
}
