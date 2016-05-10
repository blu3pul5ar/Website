/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nap.napmidtermapp.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicholas
 */
@Entity
@Table(name = "studios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Studios.findAll", query = "SELECT s FROM Studios s"),
    @NamedQuery(name = "Studios.findByStudioId", query = "SELECT s FROM Studios s WHERE s.studioId = :studioId"),
    @NamedQuery(name = "Studios.findByStudioName", query = "SELECT s FROM Studios s WHERE s.studioName = :studioName")})
public class Studios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "studio_id")
    private Integer studioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "studio_name")
    private String studioName;
    @OneToMany(mappedBy = "productStudio", cascade = CascadeType.ALL)
    private Set<Animes> animeSet;
            

    public Studios() {
    }

    public Studios(Integer studioId) {
        this.studioId = studioId;
    }

    public Studios(Integer studioId, String studioName) {
        this.studioId = studioId;
        this.studioName = studioName;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studioId != null ? studioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Studios)) {
            return false;
        }
        Studios other = (Studios) object;
        if ((this.studioId == null && other.studioId != null) || (this.studioId != null && !this.studioId.equals(other.studioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.wctc.nap.napmidtermapp.model.Studios[ studioId=" + studioId + " ]";
    }
    
}
