<%@page import="com.entity.BookDetails"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin: All Books</title>
<%@include file='allCss.jsp' %>
</head>
<body>
<%@include file="navbar.jsp" %>
    
    <c:if test="${empty userObj}">
        <c:redirect url="../login.jsp" />
    </c:if>
    
    <h3 class="text-center">Hello Admin</h3>
    
    <c:if test="${not empty succMsg }">
        <h5 class="text-center text-success">${succMsg }</h5>
        <c:remove var="succMsg" scope="session" />
    </c:if>
    
    <c:if test="${not empty failedMsg }">
        <h5 class="text-center text-danger">${failedMsg }</h5>
        <c:remove var="failedMsg" scope="session" />
    </c:if>
    
    <table class="table table-striped">
    <thead class="bg-primary text-white">
        <tr>
            <th scope="col">Book ID</th>
            <th scope="col">Book Name</th>
            <th scope="col">Reference ID</th>
            <th scope="col">Author Name</th>
            <th scope="col">Price</th>
            <th scope="col">Categories</th>
            <th scope="col">Status</th>
            <th scope="col">Action</th>
        </tr>
    </thead>
    <tbody>
        <% 
        BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
        List<BookDetails> list = dao.getAllBooks();
        for (BookDetails b : list) {
        %>
        <tr>
            <td><%= b.getBookId() %></td>
            <td><%= b.getBookName() %></td>
            <td><%= b.getRefId() %></td>
            <td><%= b.getAuthor() %></td>
            <td><%= b.getPrice() %></td>
            <td><%= b.getBookCategory() %></td>
            <td><%= b.getStatus() %></td>
            <td>
                <form action="edit_books.jsp" method="post">
                    <input type="hidden" name="bookId" value="<%= b.getBookId() %>">
                    <button type="submit" class="btn btn-sm btn-primary">
                        <i class="fa-solid fa-pen-to-square"></i> Edit
                    </button>
                </form>
                <form action="../delete" method="post">
                    <input type="hidden" name="bookId" value="<%= b.getBookId() %>">
                    <button type="submit" class="btn btn-sm btn-danger">
                        <i class="fa-solid fa-trash"></i> Delete
                    </button>
                </form>
            </td>
        </tr>
        <% } %>
    </tbody>
    </table>

<div class="container-fluid fixed-bottom">
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
