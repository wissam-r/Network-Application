/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerce.servlets;

import com.ecommerce.entities.Customer;
import com.ecommerce.entities.ShoppingCart;
import com.ecommerce.sessions.CustomerFacade;
import com.ecommerce.sessions.ShoppingCartFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eyadof
 */
public class login extends HttpServlet {

    @EJB
    private CustomerFacade customer;
    @EJB
    private ShoppingCartFacade cart;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("CustomerID") != null) {
            if((int) session.getAttribute("role") == 0){
                response.sendRedirect("/e-commerce/admin/products");
            }
            else
                response.sendRedirect("/e-commerce/Products");
        } else {
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Customer c = customer.findByUsername(username);
            //if customer information not found return error and redirect to login page

            //else compare passwords
            if (c.getPassword().equals(password)) {
                //create new session and store CustomerID and role within it
                HttpSession session = request.getSession();
                session.setAttribute("CustomerID", c.getIdCustomer());
                session.setAttribute("role", c.getRole());
                if (c.getRole() == 0) {
                    response.sendRedirect("/e-commerce/admin/products");
                } 
                else {
                    //create new shopping cart for user and store it within session also
                    ShoppingCart sc = new ShoppingCart();
                    sc.setCustomerID(c);
                    sc.setTotalBalance(0);
                    //add cart to DB
                    cart.create(sc);
                    //store cart id within session
                    session.setAttribute("ShoppingCartID", sc.getIdShoppingCart());
                    response.sendRedirect("/e-commerce/Products");
                }
            } //if password mismatch
            else {
                request.setAttribute("errors", "password mismatch!");
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        } catch (IOException | ServletException | NoResultException | EJBException e) {
            request.setAttribute("errors", "username not found!");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
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
