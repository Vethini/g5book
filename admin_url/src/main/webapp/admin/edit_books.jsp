<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="com.entity.BookDetails" %>
<%@ page import="com.DB.DBConnect" %>
<%@ page import="com.DAO.BookDAOImpl" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin: Edit Books</title>
<%@ include file='allCss.jsp' %>
</head>
<body style="background-color: #f0f2f2;">
<%@ include file="navbar.jsp" %>

<c:if test="${empty userObj}">
    <c:redirect url="../login.jsp" />
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card m-3">
                <div class="card-body">
                <h4 class="text-center text-success">Edit Books</h4>
						
                    <% 
                        // Retrieve bookId from request parameter
                        String bookId = request.getParameter("bookId");
                        System.out.println("Book ID Parameter: " + bookId); // Print book ID parameter
                        BookDetails b = null;
                        if (bookId != null) {
                            int id = Integer.parseInt(bookId);
                            BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
                            b = dao.getBookById(id);
                        }
                    %>

                    <form action="../editBooks" method="post">
                        <div class="form-group">
                            <input type="hidden" name="id" id="bookId" value="<%= (b != null) ? b.getBookId() : "" %>" />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Book Name*</label> 
                            <input name="bname" type="text" class="form-control" id="exampleInputEmail1" value="<%= (b != null) ? b.getBookName() : "" %>" aria-describedly="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Author Name*</label> 
                            <input name="author" type="text" class="form-control" id="exampleInputEmail1" value="<%= (b != null) ? b.getAuthor() : "" %>" aria-describedly="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Price*</label> 
                            <input name="price" type="number" class="form-control" id="exampleInputEmail1" value="<%= (b != null) ? b.getPrice() : "" %>" aria-describedly="emailHelp">
                        </div>
                        
                        <div class="form-group">
                            <label for="exampleInputEmail1">Book Status</label> 
                            <select id="inputState" name="status" class="form-control">
                                <option value="Active" <%= (b != null && b.getStatus().equals("Active")) ? "selected" : "" %>>Active</option>
                                <option value="Inactive" <%= (b != null && b.getStatus().equals("Inactive")) ? "selected" : "" %>>Inactive</option>
                            </select>
                        </div>
                        
                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid" style="margin-top: 15px;">
    <%@include file="footer.jsp" %>
</div>


</body>
</html>
