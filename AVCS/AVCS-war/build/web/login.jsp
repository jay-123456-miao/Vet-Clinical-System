<%-- 
    Document   : login
    Created on : Jan 10, 2024, 9:04:50 AM
    Author     : guan.kiat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <div style = "text-align: center; background-color: yellow">
            <h1> AVCS Login Page </h1>
            <br><br><br>
            <form action="Login" method="POST"> 
                <label for "usertype"> User Type: </label>
                <select id = "usertype" name = "usertype">
                    <option value = "receptionist">Receptionist</option>
                    <option value = "vet">Vet</option>
                    <option value = "managingStaff">Managing Staff</option>
                </select>
                <center>
                     <table>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="username" size="20"></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="text" name="password" size="20"></td>
                    </tr>
                </table>
                </center>
                <p><input type="submit" value="Login"></p>
            </form>
            <a href="register.jsp">New User Registration</a>
        </div>
    </body>
</html>
