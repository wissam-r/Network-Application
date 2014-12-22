<%-- 
    Document   : signup
    Created on : Dec 16, 2014, 9:33:44 PM
    Author     : eyadof
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-md-2">
        </div>
        <div class="col-md-8">
            <c:if test="${errors != null}"> 
                <div class="alert alert-dismissable alert-danger">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    ${errors}
                </div>
            </c:if>

            <div class="well bs-component">
                <p>Welcome To SU-SHOP</p>
                <br/>
                <form class="form-horizontal" action="?" method="POST">
                    <input class="form-control" name="name" type="text" placeholder="name"/>
                    <br/>
                    <input class="form-control" name="surname" type="text" placeholder="surname"/>
                    <br/>
                    <input class="form-control" name="username" type="text" placeholder="username"/>
                    <br/>
                    <input class="form-control" name="password" type="password" placeholder="password"/>
                    <br/>
                    <input class="btn btn-primary btn-flat btn-group-justified" type="submit" value="Sign Up"/>
                </form> 
            </div>       

        </div>
    </div>
</div>
