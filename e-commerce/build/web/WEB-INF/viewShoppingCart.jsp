<%-- 
    Document   : viewShoppingCart
    Created on : Dec 16, 2014, 9:36:52 PM
    Author     : eyadof
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="container">            
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Cart #${cart.idShoppingCart}</h3>
                </div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <thead>
                        <th>#</th>
                        <th>Product</th>
                        <th>Price</th>
                        </thead>
                        <c:forEach var="product" items="${cart.getProductCollection()}" varStatus="itr">
                            <tr>
                                <td>${itr.index}</td>
                                <td>${product.title}</td>
                                <td>${product.price}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="row">
                        <div class="col-md-4">
                            Total Balance: ${cart.getTotalBalance()}
                        </div>
                        <form class="" method="POST" action="/e-commerce/checkouts">
                            <div class="col-md-4">
                                <input type="hidden" name="CartID" value="${cart.getIdShoppingCart()}"/>
                                <div class="form-group">
                                    <label class="control-label">Choose an Account:</label>
                                    <c:forEach items="${Accounts}" var="account">
                                        <div class="radio radio-primary">
                                            <label>
                                                <input type="radio" checked="true" name="AccountID" value="${account.idAccount}"/>
                                                ${account.accountNum}&nbsp Balance:&nbsp ${account.balance}
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <c:if test="${cart.getPayed() == 1}">
                                    <c:set var="paymentStatus" value="disabled"></c:set>
                                </c:if>
                                <input class="btn btn-flat btn-primary btn-block ${paymentStatus} " type="submit" value="pay">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>