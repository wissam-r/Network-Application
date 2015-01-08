/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerce.servlets;

import com.ecommerce.sessions.AccountFacade;
import com.ecommerce.sessions.CustomerFacade;
import com.ecommerce.sessions.ProductFacade;
import com.ecommerce.sessions.ShoppingCartFacade;
import java.io.IOException;
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
public class admin extends HttpServlet {
    @EJB
    private CustomerFacade customer;
    @EJB
    private AccountFacade account;
    @EJB
    private ShoppingCartFacade cart;
    @EJB
    private ProductFacade product;
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
        String path = request.getRequestURI();
        //check login
        HttpSession session = request.getSession();
        if(session.getAttribute("CustomerID") == null){
            response.sendRedirect("/e-commerce/login");
            return;
        }
        //check role
        if((int) session.getAttribute("role") != 0){
            response.sendRedirect("/e-commerce/Products");
            return;
        }
        if (path.equals("/e-commerce/admin/customers")) {
            request.setAttribute("Customers", customer.findAll());
            request.getRequestDispatcher("/WEB-INF/allCustomer.jsp").forward(request, response);
        }
        else if(path.equals("/e-commerce/admin/accounts")){
            request.setAttribute("Accounts", account.findAll());
            request.getRequestDispatcher("/WEB-INF/allAccounts.jsp").forward(request, response);
        }
        else if(path.equals("/e-commerce/admin/carts")){
            request.setAttribute("Carts", cart.findAll());
            request.getRequestDispatcher("/WEB-INF/allCarts.jsp").forward(request, response);
        }
        else if (path.equals("/e-commerce/admin/products")){
            request.setAttribute("Products", product.findAll());
            request.getRequestDispatcher("/WEB-INF/allProducts.jsp").forward(request, response);
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
