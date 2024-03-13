<%@page import="com.entity.BookDetails"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BOOK OLX: Index</title>
<%@ include file="all_component/allCss.jsp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.*" %>
<%@page import="com.DB.DBConnect" %>
<%@page import="com.entity.*" %>
<style type=text/css>
	.back-img{
		height: 50%;
		width: 100%;
		overflow: hidden;
	}
	.carousel-content {
	  position: absolute;
	  top: 20%;
	  left: 50%;
	  transform: translate(-50%, -50%);
	  z-index: 20;
	  color: white;
	  text-shadow: 0 1px 2px rgba(0,0,0,.6);
	}
	.card-ho{
		width: 250px;
	}
	.card-ho:hover{
		background-color: #fcf7f7;
	}
	::-webkit-scrollbar {
    display: none;
	}
</style>

</head>
<body style="background-color: #f7f7f7;">

<%User u = (User)session.getAttribute("userObj"); %>

	<%@include file="all_component/navbar.jsp" %>
	
	<div class="container-fluid back-img">
		<div id="carouselExampleControls" class="carousel slide position-relative" data-ride="carousel">
			<div class="carousel-content">
				<h2>Ebook Management System</h2>
	      	</div>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img class="d-block w-100" src="img/165818.jpg" alt="First slide">
		    </div>
		    <div class="carousel-item">
		      <img class="d-block w-100" src="img/f.jpg" alt="Second slide">
		    </div>
		    <div class="carousel-item">
		      <img class="d-block w-100" src="img/k.webp" alt="Third slide">
		    </div>
		  </div>
		  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>
		
	</div>

	
	
</body>
</html>