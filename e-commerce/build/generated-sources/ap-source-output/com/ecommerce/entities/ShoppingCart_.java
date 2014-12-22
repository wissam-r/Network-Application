package com.ecommerce.entities;

import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-22T08:38:47")
@StaticMetamodel(ShoppingCart.class)
public class ShoppingCart_ { 

    public static volatile SingularAttribute<ShoppingCart, Integer> payed;
    public static volatile SingularAttribute<ShoppingCart, Integer> totalBalance;
    public static volatile CollectionAttribute<ShoppingCart, Product> productCollection;
    public static volatile SingularAttribute<ShoppingCart, Customer> customerID;
    public static volatile SingularAttribute<ShoppingCart, Integer> idShoppingCart;

}