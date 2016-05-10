/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nap.napmidtermapp.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicholas
 */
@Entity
@Table(name = "animes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Animes.findAll", query = "SELECT a FROM Animes a"),
    @NamedQuery(name = "Animes.findByProductId", query = "SELECT a FROM Animes a WHERE a.productId = :productId"),
    @NamedQuery(name = "Animes.findByProductName", query = "SELECT a FROM Animes a WHERE a.productName = :productName"),
    @NamedQuery(name = "Animes.findByProductCategory", query = "SELECT a FROM Animes a WHERE a.productCategory = :productCategory"),
    @NamedQuery(name = "Animes.findByProductPrice", query = "SELECT a FROM Animes a WHERE a.productPrice = :productPrice"),
    @NamedQuery(name = "Animes.findByProductImage", query = "SELECT a FROM Animes a WHERE a.productImage = :productImage"),
    @NamedQuery(name = "Animes.findByProductStock", query = "SELECT a FROM Animes a WHERE a.productStock = :productStock"),
    @NamedQuery(name = "Animes.findByProductStudio", query = "SELECT a FROM Animes a WHERE a.productStudio = :productStudio")})
public class Animes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
    private Integer productId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "product_name")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "product_category")
    private String productCategory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_price")
    private double productPrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "product_image")
    private String productImage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_stock")
    private int productStock;
    @JoinColumn(name = "product_studio", referencedColumnName = "studio_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Studios productStudio;

    public Animes() {
    }

    public Animes(Integer productId) {
        this.productId = productId;
    }

//    public Animes(Integer productId, String productName, String productCategory, double productPrice, String productImage, int productStock, int productStudio) {
//        this.productId = productId;
//        this.productName = productName;
//        this.productCategory = productCategory;
//        this.productPrice = productPrice;
//        this.productImage = productImage;
//        this.productStock = productStock;
//        this.productStudio = productStudio;
//    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public Studios getProductStudio() {
        return productStudio;
    }

    public void setProductStudio(Studios productStudio) {
        this.productStudio = productStudio;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Animes)) {
            return false;
        }
        Animes other = (Animes) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.wctc.nap.napmidtermapp.model.Animes[ productId=" + productId + " ]";
    }
    
}
