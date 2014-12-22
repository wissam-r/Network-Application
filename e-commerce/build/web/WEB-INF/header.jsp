<%-- 
    Document   : header
    Created on : Dec 20, 2014, 10:04:02 PM
    Author     : eyadof
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome!</title>
        <link   rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>
        <link   rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ripples.min.css"/>
        <link   rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/material-wfont.min.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/material.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ripples.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {

                $.material.init();
                $('.pull-down').each(function () {
                    $(this).css('margin-top', $(this).parent().height() - $(this).height())
                });
            });

        </script>
    </head>
    <body>
        <div class="navbar navbar-default">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/e-commerce/login">SU-SHOP</a>
            </div>
            <div class="navbar-collapse collapse navbar-responsive-collapse">
                <c:if test="${sessionScope.CustomerID != null}">
                    <ul class="nav navbar-nav navbar-left">
                        <li class="dropdown">
                            <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Carts <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="carts?id=${sessionScope.ShoppingCartID}">Current Cart</a></li>
                                <li><a href="carts">All Carts</a></li>
                            </ul>
                        </li>
                        <li><a href="accounts">Accounts</a></li>
                        <c:if test="${sessionScope.role == 0}">
                            <li class="dropdown">
                                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Admin <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="/e-commerce/admin/customers">View Customers</a></li>
                                    <li><a href="/e-commerce/admin/accounts">View Accounts</a></li>
                                    <li><a href="/e-commerce/admin/carts">View Carts</a></li>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </c:if>
                <c:if test="${sessionScope.CustomerID == null}">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="signup">SIGN UP</a></li>
                    </ul>
                </c:if>
            </div>
        </div>
