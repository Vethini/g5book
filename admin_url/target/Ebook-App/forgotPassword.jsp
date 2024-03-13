<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
</head>
<body>
    <h2>Forgot Password</h2>
    <form action="resetPassword" method="post">
        Enter your email address: <input type="email" name="email" required><br>
        <input type="submit" value="Reset Password">
    </form>
</body>
</html> --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password</title>
    <style>
       /*  body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-image: url('img/reset.jpg');*//* Replace 'background_image.jpg' with your image path */
          /*background-size: cover;
            background-position: center;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #fff;
        }
        .container {
            background-color: rgba(0, 0, 255, 0.7);*/ /*just transparency as needed */
           /*adding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            text-align: center;
        }
        h2 {
            margin-top: 0;
        }
        input[type="email"] {
            padding: 5px;
            width: 100%;
            margin-bottom: 10px;
            border-radius: 5px;
            border: none;
            outline: none;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        } */ 
        body {
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
    background-image: url('img/reset.jpg'); /* Replace 'background_image.jpg' with your image path */
    background-size: cover;
    background-position: top; /* Change to top */
    height: 100vh;
    color: #fff;
}
.container {
    background-color: rgba(135, 206, 255, 0.7);/*t transparency as needed */
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
    text-align: center;
    position: absolute;
    top: 10%;/*ition container at the top */
    left: 50%;
    transform: translateX(-50%);
}
h2 {
    margin-top: 0;
}
input[type="email"] {
    padding: 5px;
    width: 100%;
    margin-bottom: 10px;
    border-radius: 5px;
    border: none;
    outline: none;
}
input[type="submit"] {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}
input[type="submit"]:hover {
    background-color: #0056b3;
}
        
    </style>
</head>
<body>
    <div class="container">
        <h2>Forgot Password</h2>
        <form action="resetPassword" method="post">
            <label for="email">Enter your email address:</label><br>
            <input type="email" id="email" name="email" required><br>
            <input type="submit" value="Reset Password">
        </form>
    </div>
</body>
</html>
