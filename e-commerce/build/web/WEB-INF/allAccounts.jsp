<%-- 
    Document   : allAccounts
    Created on : Dec 19, 2014, 1:45:22 PM
    Author     : eyadof
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<button class="btn btn-fab btn-raised btn-primary pull-right" data-toggle="modal" data-target="#new-account-modal" data-placement="left" title="Add Account">
    <i class="mdi-content-add-circle-outline"></i>
</button>
<div class="container">
    <c:forEach var="account" items="${Accounts}" varStatus="itr">
        <c:if test="itr.index % 3 == 0" >
            <div class="row">
        </c:if>
        <div class="col-md-4">
            <div class="well">
                <p style="font-size: 20px; color: #009587;">${account.accountNum}</p>
                <p>Total:&nbsp${account.balance}&nbsp$</p>
            </div>
        </div>
        <c:if test="itr.index % 3 == 0" >
            </div>
        </c:if>
    </c:forEach>
</div>
<div class="modal" id="new-account-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Add New Account</h4>
            </div>
            <form method="POST" action="?">
                <div class="modal-body">
                    <input class="form-control" type="text" name="name" placeholder="Account Name"/>
                    <br/>
                    <input class="form-control" type="text" name="balance" placeholder="Account Balance"/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Create</button>
                </div>
            </form>
        </div>
    </div>
</div>
