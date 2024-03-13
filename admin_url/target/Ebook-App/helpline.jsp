<%-- <%@page import="com.entity.BookDtls"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Help 24*7</title>
<%@ include file="all_component/allCss.jsp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.*" %>
<%@page import="com.DB.DBConnect" %>
<%@page import="com.entity.*" %>

</head>
<body style="background-color: #f7f7f7;">
<%@include file="all_component/navbar.jsp" %>
		
<c:if test="${empty userObj}">
	<c:redirect url="login.jsp" />
</c:if>

	<div class="container">
	
		<div class="row p-5">
			
			<div class="col-md-4 offset-md-4 text-center">
				
				<div class="text-success">				
					<i class="fa-solid fa-phone-volume fa-5x" style="color: #1ac15a;"></i>
				</div>
				<h1>Emergency Helpline</h1>
        <p>If you are in need of immediate assistance, please contact our helpline:</p>
        <p class="helpline">Helpline: 123-456-7890</p>
        <p>Our helpline is available 24/7 to provide support and assistance.</p>
				<a href="index.jsp" class="btn btn-primary">Home</a>
			</div>
			
		</div>
	</div>
		<%@include file="all_component/footer.jsp" %>
</body>
</html> --%>

<%-- 
<%@page import="com.entity.BookDtls"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Help 24*7</title>
    <%@ include file="all_component/allCss.jsp" %>
    <%@ page import="java.sql.Connection" %>
    <%@ page import="java.util.*" %>
    <%@page import="com.DB.DBConnect" %>
    <%@page import="com.entity.*" %>
     <style>
        body {
            background-image: url('img/helpline.png');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }
        .container {
            padding: 50px 20px;
        }
        .helpline-icon {
            color: #1ac15a;
            font-size: 5em;
        }
        .helpline-title {
            font-size: 2em;
            margin-top: 20px;
            margin-bottom: 10px;
        }
        .helpline-info {
            font-size: 1.2em;
            line-height: 1.6;
            margin-bottom: 20px;
        }
        .helpline-number {
            font-size: 1.5em;
            font-weight: bold;
            color: #1ac15a;
            margin-bottom: 10px;
        }
        .btn-home {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 1.2em;
            text-decoration: none;
            transition: background-color 0.3s;
        }
        .btn-home:hover {
            background-color: #0056b3;
        }
        /* Align text to right end */
        .text-right {
            text-align: right;
        }
    </style>
</head>
<body>
    <%@include file="all_component/navbar.jsp" %>
        
    <c:if test="${empty userObj}">
        <c:redirect url="login.jsp" />
    </c:if>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 text-center">
                <div class="text-success helpline-icon">
                    <i class="fa-solid fa-phone-volume"></i>
                </div>
                <h1 class="helpline-title">Emergency Helpline</h1>
                <p class="helpline-info">If you are in need of immediate assistance, please contact our helpline:</p>
                <p class="helpline-number">Helpline: 123-456-7890</p>
                <p class="helpline-info">Our helpline is available 24/7 to provide support and assistance.</p>
                <a href="index.jsp" class="btn btn-home">Home</a>
            </div>
        </div>
    </div>
    <%@include file="all_component/footer.jsp" %>
</body>
</html> --%>



<%@page import="com.entity.BookDetails"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Help 24*7</title>
    <%@ include file="all_component/allCss.jsp" %>
    <%@ page import="java.sql.Connection" %>
    <%@ page import="java.util.*" %>
    <%@page import="com.DB.DBConnect" %>
    <%@page import="com.entity.*" %>
    <style>
        body {
            background-image: url('img/helpline.png');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }
        .container {
            padding: 50px 20px;
        }
        .helpline-icon {
            color: #1ac15a;
            font-size: 5em;
        }
        .helpline-content {
            text-align: center;
        }
        .helpline-title {
            font-size: 2em;
            margin-top: 20px;
            margin-bottom: 10px;
        }
        .helpline-info {
            font-size: 1.2em;
            line-height: 1.6;
            margin-bottom: 20px;
        }
        .helpline-number {
            font-size: 1.5em;
            font-weight: bold;
            color: #1ac15a;
            margin-bottom: 10px;
        }
        .helpline-additional-info {
            font-size: 1.2em;
            line-height: 1.6;
            margin-bottom: 20px;
        }
        .btn-home {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 1.2em;
            text-decoration: none;
            transition: background-color 0.3s;
        }
        .btn-home:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <%@include file="all_component/navbar.jsp" %>
        
    <c:if test="${empty userObj}">
        <c:redirect url="login.jsp" />
    </c:if>

    <div class="container">
        <div class="row justify-content-end">
            <div class="col-md-6 helpline-content">
                <div class="text-success helpline-icon">
                    <i class="fa-solid fa-phone-volume"></i>
                </div>
                <h1 class="helpline-title">Emergency Helpline</h1>
                <p class="helpline-info">If you are in need of immediate assistance, please contact our helpline</p>
                <p class="helpline-number">Helpline: 123-456-7890</p>
                <p class="helpline-additional-info">Our helpline is available 24/7 to provide support and assistance.</p>
                <a href="index.jsp" class="btn btn-home">Home</a>
            </div>
        </div>
    </div>
    <%@include file="all_component/footer.jsp" %>
</body>
</html>