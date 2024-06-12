<%-- 
    Document   : edit_vet_individual_profile
    Created on : Feb 27, 2024, 6:52:17 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Vet Individual Profile Page</title>
    </head>
    <body>
        <jsp:include page = "vetHomePage.jsp"/>
        <h1>Welcome to your profile management page!</h1>
        <p> Please that not the following guidelines when registering: </p>
        <p>* The maximum number of character for username, password is 10!</p>
        <p>* The maximum number of character for email is 25!</p>
        <p>* Only numbers are accepted for password, contact number and age!</p>
        <p>* Do not edit the same type of expertise!</p>
        <p>* Please provide reasonable age number !</p>
        <br>
        <br>
        <p>Please click the edit button below to edit your profile</p>
    </body>
</html>
