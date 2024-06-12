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
        <title>Registration Page</title>
    </head>
    <body>
        <div style = "background-color: yellow">
            <a href="login.jsp">Login Page</a>
            <p> Please that not the following guidelines when registering: </p>
            <p>* The maximum number of character for username, password is 10!</p>
            <p>* The maximum number of character for email is 25!</p>
            <p>* Only numbers are accepted for password, contact number and age!</p>
            <p>* Please provide reasonable age number !</p>
            <br><br><br>
            <form action="Register" method="POST">
                <table>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="username" size="20"></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="text" name="password" size="20"></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="email" size="20"></td>
                    </tr>
                    <tr>
                        <td>Contact Number:</td>
                        <td><input type="text" name="contactnumber" size="20"></td>
                    </tr>
                    <tr>
                        <td>Age:</td>
                        <td><input type="text" name="age" size="20"></td>
                    </tr>
                    <tr>
                        <td>Gender:</td>
                        <td>
                            <select id = "gender" name ="gender">
                                <option value = "male">Male</option>
                                <option value = "female">Female</option>
                                <option value = "confidential">Confidential</option>
                            </select>
                        <td/>    
                    <tr/>
                     <tr>
                        <td>User Type:</td>
                        <td>
                            <select id = "usertype" name ="usertype">
                                <option value = "vet">Vet</option>
                                <option value = "receptionist">Receptionist</option>
                            </select>
                        <td/>    
                    <tr/>
                </table>
                <p><input type="submit" value="Register"></p>
            </form>
        </div>
    </body>
</html>
