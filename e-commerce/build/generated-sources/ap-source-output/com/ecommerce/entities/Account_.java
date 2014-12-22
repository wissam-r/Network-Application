package com.ecommerce.entities;

import com.ecommerce.entities.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-22T08:38:47")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, Integer> balance;
    public static volatile SingularAttribute<Account, Integer> idAccount;
    public static volatile SingularAttribute<Account, String> accountNum;
    public static volatile SingularAttribute<Account, Customer> customerID;

}