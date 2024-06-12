<%-- 
    Document   : create_working_rotation
    Created on : Mar 11, 2024, 10:41:53 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Working Rotation Page</title>
    </head>
    <body>
        <a href="Logout">Log Out</a>
        <h1>Working Rotation Page</h1>
        <p>Please enter the date for generating the working rotation table<p>
        <br><br>
        <p>**Take note only date starting on Monday are allowed**<p>
        <form method = "POST" action = "Load_working_rotation">
            <table>
                <tr>
                    <td>Year: </td>
                    <td><input type = text name = year size = 20></td>
                </tr>
                <tr>
                    <td>Month: </td>
                    <td><input type = text name = month size = 20></td>
                </tr>
                <tr>
                    <td>Day: </td>
                    <td><input type = text name = day size = 20></td>
                </tr>
            </table>
            <p><input type="submit" value="Create"></p>
        </form>
    </body>
</html>