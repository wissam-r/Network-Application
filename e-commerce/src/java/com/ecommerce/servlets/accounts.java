/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerce.servlets;

import com.ecommerce.entities.Account;
import com.ecommerce.entities.Customer;
import com.ecommerce.sessions.AccountFacade;
import com.ecommerce.sessions.CustomerFacade;
import java.io.IOException;
import java.io.PrintWriter;
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
public class accounts extends HttpServlet {

    @EJB
    private CustomerFacade customer;
    @EJB
    private AccountFacade account;

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
        //get CustomerID from session
        HttpSession session = request.getSession();
        //check login
        if (session.getAttribute("CustomerID") == null) {
            response.sendRedirect("/e-commerce/login");
            return;
        }
        int CustomerID = (int) session.getAttribute("CustomerID");
        //get the customer from the DB
        Customer c = customer.find(CustomerID);

        request.setAttribute("Accounts", c.getAccountCollection());
        request.getRequestDispatcher("WEB-INF/allAccounts.jsp").forward(request, response);

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
        
        if (request.getParameter("delete") != null) {
            //get ProductID from request
            int AccountID = Integer.parseInt(request.getParameter("AccountID"));
            //get product to delete it
            Account ac = account.find(AccountID);
            account.remove(ac);
            response.sendRedirect("/e-commerce/admin/accounts");
        }
        else {
            //create new account
            Account ac = new Account();
            //get account name
            String accountName = request.getParameter("name");
            //get accound realted customer
            int CustomerID = (int) session.getAttribute("CustomerID");
            Customer c = customer.find(CustomerID);
            try{
                //get account balance
                int Balance = Integer.parseInt(request.getParameter("balance"));
                //assgine the parameters to object
                ac.setAccountNum(accountName);
                ac.setCustomerID(c);
                ac.setBalance(Balance);
                //store account into DB
                account.create(ac);
                //add account to user collection
                c.getAccountCollection().add(ac);
                c.setAccountCollection(c.getAccountCollection());
                request.setAttribute("success","Account Created!");
            }
            catch (NumberFormatException e ){
                request.setAttribute("errors", "Account Balance is not correct!");
            }
            
            request.setAttribute("Accounts", c.getAccountCollection());
            request.getRequestDispatcher("WEB-INF/allAccounts.jsp").forward(request, response);
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
