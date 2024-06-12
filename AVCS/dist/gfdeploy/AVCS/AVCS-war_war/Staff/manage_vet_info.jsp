<%-- 
    Document   : manage_vet_info
    Created on : Feb 25, 2024, 5:23:03 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Vet Information Management Page</title>
        <style>
            table,th,td
            {
             border: 1px solid black;
             border-collapse: collapse;
            }
        </style>
    </head>
    <body>
        <jsp:include page = "staffHomePage.jsp"/>
        <h1>vet Information Page</h1>
        <p> Please that not the following guidelines when editing: </p>
        <p>* The maximum number of character for username, password is 10!</p>
        <p>* The maximum number of character for email is 25!</p>
        <p>* Only numbers are accepted for password, contact number and age!</p>
        <p>* Please provide reasonable age number !</p>
        <br>
        <br>
        <p><b>Search for the vet username</b><p>
        <br>
        <p> Take note : Please enter the correct vet username!</p>
        <form method = "POST" action = "Search_vet_info">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type = "text" name = "vet_username" size = "20"></td>
                </tr>
            </table>
            <p><input type="submit" value="Search"></p>
        </form>      
    </body>
</html>
