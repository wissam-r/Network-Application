/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerce.servlets;

import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Product;
import com.ecommerce.sessions.CustomerFacade;
import com.ecommerce.sessions.ProductFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eyadof
 */
@MultipartConfig
public class Products extends HttpServlet {

    @EJB
    private ProductFacade product;
    @EJB
    private CustomerFacade customer;

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
        response.setStatus(200);
        request.setAttribute("Products", product.findAll());
        request.getRequestDispatcher("/WEB-INF/allProducts.jsp").forward(request, response);
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
        //get customer id from session
        HttpSession session = request.getSession();
        int CustomerID = (int) session.getAttribute("CustomerID");
        //get customer from db
        Customer c = customer.find(CustomerID);
        //handle delete
        if (request.getParameter("delete") != null) {
            //get ProductID from request
            int ProductID = Integer.parseInt(request.getParameter("ProductID"));
            //get product to delete it
            Product p = product.find(ProductID);
            product.remove(p);
        } 
        else {
            //get price and title from request
            String title = request.getParameter("title");
            try {
                int price = Integer.parseInt(request.getParameter("price"));
                //init new product object
                Product p = new Product();
                p.setCustomerID(c);
                p.setTitle(title);
                p.setPrice(price);

                //save product into db
                product.create(p);
                request.setAttribute("success", "Product Created!");
            }
            catch(NumberFormatException e){
                request.setAttribute("errors", "Product Price Error !");
            }
        }

        request.setAttribute("Products", product.findAll());
        request.getRequestDispatcher("/WEB-INF/allProducts.jsp").forward(request, response);
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
