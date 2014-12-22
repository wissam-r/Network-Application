/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerce.servlets;

import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ShoppingCart;
import com.ecommerce.sessions.CustomerFacade;
import com.ecommerce.sessions.ProductFacade;
import com.ecommerce.sessions.ShoppingCartFacade;
import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eyadof
 */
public class carts extends HttpServlet {

    @EJB
    private ShoppingCartFacade cart;
    @EJB
    private ProductFacade product;
    @EJB
    private CustomerFacade customer;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get customerID from session
        HttpSession session = request.getSession();
        //check login
        if (session.getAttribute("CustomerID") == null) {
            response.sendRedirect("/e-commerce/login");
            return;
        }
        int CustomerID = (int) session.getAttribute("CustomerID");
        //retrive the customer from the DB
        Customer c = customer.find(CustomerID);
        //if there is not an id set show all carts for user
        if (request.getParameter("id") == null) {
            request.setAttribute("Carts", c.getShoppingCartCollection());
            request.getRequestDispatcher("WEB-INF/allCarts.jsp").forward(request, response);
        } //else if there is an id list the cart item
        else {
            int CartID = Integer.parseInt(request.getParameter("id"));
            ShoppingCart sc = null;
            for (ShoppingCart cr : c.getShoppingCartCollection()) {
                if (cr.getIdShoppingCart() == CartID) {
                    sc = cr;
                }
            }
            request.setAttribute("cart", sc);
            request.setAttribute("Accounts", c.getAccountCollection());
            request.getRequestDispatcher("WEB-INF/viewShoppingCart.jsp").forward(request, response);
        }
    }

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
        //save product into cart
        //get productID from post data
        int ProductID = Integer.parseInt(request.getParameter("ProductID"));
        //get product object from bean;
        Product pro = product.find(ProductID);
        //get cart id from get data
        int CartID = Integer.parseInt(request.getParameter("id"));
        ShoppingCart c = cart.find(CartID);
        //add product to cart
        cart.addProduct(CartID, ProductID);
        c.getProductCollection().add(pro);
        //set total balance
        c.setTotalBalance(c.getTotalBalance() + pro.getPrice());
        //update cart information
        cart.edit(c);
        //return to product list
        response.sendRedirect("/e-commerce/Products");
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
