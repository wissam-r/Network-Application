<%-- 
    Document   : allCarts
    Created on : Dec 18, 2014, 10:31:55 PM
    Author     : eyadof
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="container">
    <c:forEach var="cart" items="${Carts}" varStatus="itr">
        <c:if test="itr.index % 3 == 0" >
            <div class="row">
            </c:if>
            <div class="col-md-4">
                <div class="well">
                    <p style="font-size: 20px; color: #009587;">${cart.idShoppingCart}</p>
                    <p>Total:&nbsp${cart.totalBalance}&nbsp$</p>
                    <a class="btn btn-flat btn-primary" href="/e-commerce/carts?id=${cart.idShoppingCart}">View</a>
                    <c:if test="${sessionScope.role == 0}">
                        <form method="POST" action="/e-commerce/carts">
                            <div class="input-group">
                                <input type="hidden" name="CartID" value="${cart.idShoppingCart}"/>
                                <input class="btn btn-flat btn-danger " type="submit" name="delete" value="DELETE"/>
                            </div>
                        </form>
                    </c:if>
                </div>
            </div>
            <c:if test="itr.index % 3 == 0" >
            </div>
        </c:if>
    </c:forEach>
</div>
