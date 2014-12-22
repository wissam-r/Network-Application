package com.ecommerce.entities;

import com.ecommerce.entities.Customer;
import com.ecommerce.entities.ShoppingCart;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-22T08:38:47")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, String> title;
    public static volatile SingularAttribute<Product, Integer> price;
    public static volatile CollectionAttribute<Product, ShoppingCart> shoppingCartCollection;
    public static volatile SingularAttribute<Product, Integer> idProduct;
    public static volatile SingularAttribute<Product, Customer> customerID;

}