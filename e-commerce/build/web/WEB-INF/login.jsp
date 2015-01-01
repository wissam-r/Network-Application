<%-- 
    Document   : login
    Created on : Dec 18, 2014, 9:18:44 AM
    Author     : eyadof
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-2">
        </div>
        <div class="col-md-8">
            <div class="well bs-component">
                <p>Welcome To SU-SHOP</p>
                <br/>
                <form class="form-horizontal" action="?" method="POST">
                    <input class="form-control floating-label" name="username" type="text" placeholder="username"/>
                    <br/>
                    <input class="form-control floating-label" name="password" type="password" placeholder="password"/>
                    <br/>
                    <input class="btn btn-primary btn-flat btn-group-justified" type="submit" value="Login"/>
                </form> 
            </div>       

        </div>
    </div>
</div>
