<%-- 
    Document   : allAccounts
    Created on : Dec 19, 2014, 1:45:22 PM
    Author     : eyadof
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<c:if test="${sessionScope.role != 0}">
<button class="btn btn-fab btn-raised btn-primary pull-right" data-toggle="modal" data-target="#new-account-modal" data-placement="left" title="Add Account">
    <i class="mdi-content-add-circle-outline"></i>
</button>
</c:if>
<div class="container">
    <c:forEach var="account" items="${Accounts}" varStatus="itr">
        <c:if test="itr.index % 3 == 0" >
            <div class="row">
            </c:if>
            <div class="col-md-4">
                <div class="well">
                    <div class="row">
                        <div class="col-md-12">
                            <p style="font-size: 20px; color: #009587;"> ${account.accountNum}</p>
                            <p> Total:&nbsp${account.balance}&nbsp$</p>
                            <c:if test="${sessionScope.role == 0}">
                                <form method="POST" action="/e-commerce/accounts">
                                    <input type="hidden" name="AccountID" value="${account.idAccount}"/>
                                    <input type="submit" name="delete" value="delete" class="btn btn-danger btn-flat btn-block">
                                </form>
                            </c:if>
                        </div>
                    </div>
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
            <form method="POST" action="/e-commerce/accounts">
                <div class="modal-body">
                    <input class="form-control floating-label" type="text" name="name" placeholder="Account Name"/>
                    <br/>
                    <input class="form-control floating-label" type="text" name="balance" placeholder="Account Balance"/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Create</button>
                </div>
            </form>
        </div>
    </div>
</div>
