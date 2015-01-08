<%-- 
    Document   : allProducts
    Created on : Dec 16, 2014, 9:39:26 PM
    Author     : eyadof
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<c:if test="${sessionScope.role != 0}">
<button class="btn btn-fab btn-raised btn-primary pull-right" data-toggle="modal" data-target="#new-product-modal" data-placement="left" title="Add Product">
    <i class="mdi-content-add-circle-outline"></i>
</button>
</c:if>
<div class="container">
    <c:forEach var="product" items="${Products}" varStatus="itr">
        <c:if test="itr.index % 3 == 0" >
            <div class="row">
            </c:if>
            <div class="col-md-4">
                <div class="well">
                    <p style="font-size: 20px; color: #009587;">${product.title}</p>
                    <p>Price:&nbsp${product.price}&nbsp$</p>
                    <div class="row">
                        <c:set var="col" value="6"></c:set>
                        <c:if test="${sessionScope.role != 0}">
                            <c:set var="block" value="btn-block"></c:set>
                            <c:set var="col" value="12"></c:set>
                        </c:if>
                        <div class="col-md-${col}">
                            <form method="POST" action="/e-commerce/carts?id=${sessionScope.ShoppingCartID}">
                                <input type="hidden" name="ProductID" value="${product.idProduct}"/>
                                <input type="submit" value="buy" class="btn btn-primary btn-flat ${block}">
                            </form>
                        </div>
                        <c:if test="${sessionScope.role == 0}">
                            <div class="col-md-6">
                                <form method="POST" action="?">
                                    <input type="hidden" name="ProductID" value="${product.idProduct}"/>
                                    <input type="submit" name="delete" value="delete" class="btn btn-danger btn-flat pull-right">
                                </form>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <c:if test="itr.index % 3 == 0" >
            </div>
        </c:if>
    </c:forEach>
    <div class="modal" id="new-product-modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Add New Product</h4>
                </div>
                <form method="POST" action="?"  enctype="multipart/form-data">
                    <div class="modal-body">
                        <input class="form-control floating-label" type="text" name="title" placeholder="Product Title"/>
                        <br/>
                        <input class="form-control floating-label" type="text" name="price" placeholder="Product Price"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Create</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>