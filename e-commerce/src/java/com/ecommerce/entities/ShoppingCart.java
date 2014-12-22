/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eyadof
 */
@Entity
@Table(name = "ShoppingCart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShoppingCart.findAll", query = "SELECT s FROM ShoppingCart s"),
    @NamedQuery(name = "ShoppingCart.findByIdShoppingCart", query = "SELECT s FROM ShoppingCart s WHERE s.idShoppingCart = :idShoppingCart"),
    @NamedQuery(name = "ShoppingCart.findByTotalBalance", query = "SELECT s FROM ShoppingCart s WHERE s.totalBalance = :totalBalance")})
public class ShoppingCart implements Serializable {
    @Column(name = "payed")
    private Integer payed;
    @JoinColumn(name = "CustomerID", referencedColumnName = "idCustomer")
    @ManyToOne(optional = false)
    private Customer customerID;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idShoppingCart")
    private Integer idShoppingCart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TotalBalance")
    private int totalBalance;
    @JoinTable(name="ShoppingCart_has_Product", 
            joinColumns =
                @JoinColumn(name="Product", referencedColumnName = "idProduct")
            ,
            inverseJoinColumns = 
                @JoinColumn(name="ShoppingCart", referencedColumnName = "idShoppingCart")
            )
    @ManyToMany(mappedBy = "shoppingCartCollection")
    private Collection<Product> productCollection;

    public ShoppingCart() {
    }

    public ShoppingCart(Integer idShoppingCart) {
        this.idShoppingCart = idShoppingCart;
    }

    public ShoppingCart(Integer idShoppingCart, int totalBalance) {
        this.idShoppingCart = idShoppingCart;
        this.totalBalance = totalBalance;
    }

    public Integer getIdShoppingCart() {
        return idShoppingCart;
    }

    public void setIdShoppingCart(Integer idShoppingCart) {
        this.idShoppingCart = idShoppingCart;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idShoppingCart != null ? idShoppingCart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShoppingCart)) {
            return false;
        }
        ShoppingCart other = (ShoppingCart) object;
        if ((this.idShoppingCart == null && other.idShoppingCart != null) || (this.idShoppingCart != null && !this.idShoppingCart.equals(other.idShoppingCart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecommerce.entities.ShoppingCart[ idShoppingCart=" + idShoppingCart + " ]";
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    public Integer getPayed() {
        return payed;
    }

    public void setPayed(Integer payed) {
        this.payed = payed;
    }
    
}
