<%-- 
    Document   : manage_appointments.jsp
    Created on : Feb 29, 2024, 4:47:35 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Appointments Page</title>
    </head>
    <body>
        <jsp:include page = "receptionistHome.jsp"/>
        <h1>Welcome to the manage appointment page</h1>
        <form action = "Create_appointment" method="POST">
            <table border="1" width="80%">
                <tr>
                    <th colspan="2">Appointment Info</th>
                    <th colspan="2">Customer Info</th>
                    <th colspan="2">Pet Info</th>
                </tr>
                <tr>
                    <td>Year: </td>
                    <td><input type="text" name="year" size="20"></td>
                    <td>Username: </td>
                    <td><input type="text" name="customerUsername" size="20"></td>
                    <td>Pet Name: </td>
                    <td><input type="text" name="petUsername" size="20"></td>
                </tr>
                <tr>
                    <td>Month: </td>
                    <td><input type="text" name="month" size="20"></td>
                    <td>Gender: </td>
                    <td>
                        <select id = "gender" name ="gender">
                            <option value = "male">Male</option>
                            <option value = "female">Female</option>
                            <option value = "confidential">Confidential</option>
                        </select>
                    </td>
                    <td>Species: </td>
                    <td>
                        <select id = "species" name ="species">
                            <option value = "-">-</option>
                            <option value = "Dogs">Dogs</option>
                            <option value = "Cat">Cat</option>
                            <option value = "Fish">Fish</option>
                            <option value = "Birds">Birds</option>
                            <option value = "Reptiles">Reptiles</option>
                            <option value = "Horse">Horse</option>
                            <option value = "Tortoise">Tortoise</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Day: </td>
                    <td><input type="text" name="day" size="20"></td>
                    <td>Email Address: </td>
                    <td><input type="text" name="emailAddress" size="20"></td>
                </tr> 
                    <td>Hour: </td>
                    <td><input type="text" name="hour" size="20"></td>
                    <td>Contact Number: </td>
                    <td><input type="text" name="contactNumber" size="20"></td>
                <tr>
                    <td>Assigned vet: </td>
                    <td><input type="text" name="vetUsername" size="20"></td>
                    <td>Age: </td>
                    <td><input type="text" name="age" size="20"></td>
                </tr>    
            </table>  
            <p><input type = "submit" name ="create" value="create"></p>
        </form>
        <br><br>
        <p>Please find the appointment details below:</p>
        <br>
    </body>
</html>
