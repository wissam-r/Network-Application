package com.ecommerce.entities;

import com.ecommerce.entities.Account;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ShoppingCart;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-22T08:38:47")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> username;
    public static volatile SingularAttribute<Customer, Integer> idCustomer;
    public static volatile SingularAttribute<Customer, String> name;
    public static volatile SingularAttribute<Customer, String> surname;
    public static volatile SingularAttribute<Customer, Integer> role;
    public static volatile CollectionAttribute<Customer, ShoppingCart> shoppingCartCollection;
    public static volatile CollectionAttribute<Customer, Product> productCollection;
    public static volatile SingularAttribute<Customer, String> password;
    public static volatile CollectionAttribute<Customer, Account> accountCollection;

}