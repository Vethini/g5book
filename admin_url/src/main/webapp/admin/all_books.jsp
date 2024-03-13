<%@ page import="com.entity.User" %>
<%@ page import="com.entity.BookDetails" %>
<%@ page import="com.DB.DBConnect" %>
<%@ page import="com.DAO.BookDAOImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.entity.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin: All Books</title>
<%@ include file='allCss.jsp' %>
<script>
function goToEditBook(bookId, displayedBookId) {
    var sessionBookId = '<%= session.getAttribute("editBookId") %>';
    if (displayedBookId === sessionBookId) {
        var confirmEdit = confirm("Are you sure you want to edit this book?");
        if (confirmEdit) {
            window.location.href = 'edit_books.jsp?bookId=' + bookId;
        }
    } else {
        window.location.href = '../logout';
    }
}

function deleteBook(bookId, displayedBookId) {
    var sessionBookId = '<%= session.getAttribute("deleteBookId") %>';
    if (displayedBookId === sessionBookId) {
        var confirmDelete = confirm("Are you sure you want to delete this book?");
        if (confirmDelete) {
            window.location.href = '../delete?bookId=' + bookId;
        }
    } else {
        window.location.href = '../logout';
    }

</script>

</head>
<body>
<%@ include file="navbar.jsp" %>

<c:if test="${empty userObj }">
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

<%
    User userObj = (User) session.getAttribute("userObj");
    BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
    List<BookDetails> list = dao.getAllBooks();

    // Set book ID and details in session only if admin owns the book
    if (userObj != null && userObj.getName().equals("Admin") ) {
%>
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
            <button type="button" class="btn btn-sm btn-primary" onclick="goToEditBook('<%= b.getBookId() %>', '<%= b.getBookId() %>')">
                <i class="fa-solid fa-pen-to-square"></i> Edit
            </button>
            <button type="button" class="btn btn-sm btn-danger" onclick="deleteBook('<%= b.getBookId() %>', '<%= b.getBookId() %>')">
                <i class="fa-solid fa-trash"></i> Delete
            </button>
        </td>
    </tr>
<%
    }
%>
</tbody>
</table>
<%
    } else {
        response.sendRedirect("../logout");
    }
%>

<div class="container-fluid fixed-bottom">
    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
