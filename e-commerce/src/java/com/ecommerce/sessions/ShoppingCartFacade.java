/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerce.sessions;

import com.ecommerce.entities.Product;
import com.ecommerce.entities.ShoppingCart;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author eyadof
 */
@Stateless
public class ShoppingCartFacade extends AbstractFacade<ShoppingCart> {
    @PersistenceContext(unitName = "e-commercePU")
    private EntityManager em;
    @EJB
    private ProductFacade product;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShoppingCartFacade() {
        super(ShoppingCart.class);
    }
    
    public void addProduct(int CartID,int ProductID){
        em.createNativeQuery("INSERT INTO ShoppingCart_has_Product VALUES(?, ?)")
                .setParameter(1, CartID)
                .setParameter(2, ProductID)
                .executeUpdate();
    }
    
    public Collection<Product> getProduts(int CartID){
        Query q = em.createNativeQuery("SELECT p.Title, p.Price FROM Product p INNER JOIN ShoppingCart_has_Product sp ON  p.idProduct = sp.Product_idProduct WHERE ShoppingCart_idShoppingCart = ?")
                .setParameter(1, CartID);
        return q.getResultList();
        
        

      
    }
}
