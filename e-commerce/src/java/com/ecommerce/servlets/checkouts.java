/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerce.servlets;

import com.ecommerce.entities.Account;
import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ShoppingCart;
import com.ecommerce.sessions.AccountFacade;
import com.ecommerce.sessions.CustomerFacade;
import com.ecommerce.sessions.ShoppingCartFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eyadof
 */
@WebServlet(name = "checkouts", urlPatterns = {"/checkouts"})
public class checkouts extends HttpServlet {

    @EJB
    private ShoppingCartFacade cart;
    @EJB
    private CustomerFacade customer;
    @EJB
    private AccountFacade account;
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //check login
        if (session.getAttribute("CustomerID") == null) {
            response.sendRedirect("/e-commerce/login");
            return;
        }
        //get the CustomerID from session
        int CustomerID = (int) session.getAttribute("CustomerID");

        //retrieve the Customer from the DB
        Customer c = customer.find(CustomerID);

        //retrieve the Cart from the DB
        int CartID = Integer.parseInt(request.getParameter("CartID"));
        ShoppingCart sc = cart.find(CartID);

        int AccountID = Integer.parseInt(request.getParameterValues("AccountID")[0]);
        Account ac = null;
        //find selected Account
        for (Account tmp : c.getAccountCollection()) {
            if (tmp.getIdAccount() == AccountID) {
                ac = tmp;
            }
        }

        // return error if shopping cart total balance > selected account balance
        if (sc.getTotalBalance() > ac.getBalance()) {
            request.setAttribute("errors", "Account balance is insufficient please select another account");
            response.sendRedirect("/e-commerce/Products");
            return;
        }

        // process the checkout
        for (Product pro : sc.getProductCollection()) {
            //update the merchant account (the first one !)
            Account merchantAccount = (Account) pro.getCustomerID().getAccountCollection().toArray()[0];
            merchantAccount.setBalance(merchantAccount.getBalance() + pro.getPrice());
            //update the customer balance
            ac.setBalance(ac.getBalance() - pro.getPrice());
            //update merchant and buyer accounts into DB
            account.edit(ac);
            account.edit(merchantAccount);
        }
        sc.setPayed(1);
        cart.edit(sc);
        //if customer payed current cart
        //create new cart for this session and assign its id to session
        if ((int) session.getAttribute("ShoppingCartID") == CartID) {
            ShoppingCart tmpCart = new ShoppingCart();
            tmpCart.setCustomerID(c);
            cart.create(tmpCart);
            session.setAttribute("ShoppingCartID", tmpCart.getIdShoppingCart());
        }
        //return the customer to product page
        response.setStatus(200);
        response.sendRedirect("Products");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
