<%-- 
    Document   : allCustomer
    Created on : Dec 16, 2014, 9:39:13 PM
    Author     : eyadof
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="container">
    <c:forEach var="customer" items="${Customers}" varStatus="itr">
        <c:if test="itr.index % 3 == 0" >
            <div class="row">
            </c:if>
            <div class="col-md-4">
                <div class="well">
                    <p style="font-size: 20px; color: #009587;">${customer.name} ${customer.surname}</p>
                    <p>Username:&nbsp${customer.username}</p>
                    <form method="POST" action="/e-commerce/customers">
                        <input type="hidden" name="CustomerID" value="${customer.idCustomer}"/>
                        <input type="submit" name="delete" value="delete"  class="btn btn-flat btn-danger btn-block">
                    </form>
                </div>
            </div>

        <c:if test="itr.index % 3 == 0" >
            </div>
        </c:if>
    </c:forEach>
</div>