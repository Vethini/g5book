<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.DAO.BookDAOImpl"%>
<%@page import="com.entity.BookDetails"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Old Book</title>
<%@ include file="all_component/allCss.jsp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.*" %>
<%@page import="com.DB.DBConnect" %>
<style type=text/css>
	
	.card-ho:hover{
		background-color: #fcf7f7;
	}
	::-webkit-scrollbar {
    display: none;
	}
	body {
		background-image: url('img/book3.png');
		background-size: cover;
		background-repeat: no-repeat;
	}
</style>
</head>
<body>
	<%@include file="all_component/navbar.jsp" %>
	
	<div class="container-fluid p-4" >
		
		<div class="row d-flex align-items-center">
		
			
			
			<%
				BookDAOImpl dao3 = new BookDAOImpl(DBConnect.getConn());
				List<BookDetails> list3 = dao3.getAllOldBooks();
				for(BookDetails b : list3){
			%>	
			<div class="col-md-3 m-0.5 pb-2">
				
				<div class="card card-ho">
					<div class="card-body text-center">
						 <p><%= b.getRefId() %></p>
						<p><%= b.getBookName() %></p>
						<p><%= b.getAuthor() %></p>
						<p> Category: <%= b.getBookCategory() %> </p>
						<div class="row d-flex justify-content-around">
							<a href="view_books.jsp?bid=<%= b.getBookId() %>" class="btn btn-success btn-sm mr-1">View Details</a>
							<a href="" class="btn btn-danger btn-sm"><i class="fa-solid fa-indian-rupee-sign"></i> <%= b.getPrice() %></a>
						</div>
					</div>
				</div>
					
			</div>
					
			<%	}
			%>
				
			
		</div>
	</div>
	
</body>
</html>
