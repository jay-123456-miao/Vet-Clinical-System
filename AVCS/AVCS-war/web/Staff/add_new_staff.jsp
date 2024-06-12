<%-- 
    Document   : add_new_staff
    Created on : Feb 23, 2024, 6:59:46 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Staff Registration Page</title>
    </head>
    <body>
        <jsp:include page = "staffHomePage.jsp"/>
        <h1>New Staff Registration Page</h1>
        <br>
        <br>
        <p><b>New Staff Registration</b><p>
        <br>
        <p> Take note : Maximum 10 characters allowed for username and 6 characters allowed for password. Only integer accepted for password</p>
        <form method = "POST" action = "Add_new_staff">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type = "text" name = "username" size = "20"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type = "text" name = "password" size = "20"></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type = "text" name = "email" size = "20"></td>
                </tr>
                <tr>
                    <td>
                        <label for "gender"> Gender: </label>
                        <select id = "gender" name ="gender">
                            <option value = "M">Female</option>
                            <option value = "F">Male</option>
                        </select>
                    </td>
                </tr>
            </table>
            <p><input type="submit" value="Add"></p>
        </form>
    </body>
</html>
