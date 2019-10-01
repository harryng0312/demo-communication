<%--
  Created by IntelliJ IDEA.
  User: Harry Nguyen
  Date: 10/1/2019
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style type="text/css">
        body {
            align-content: center;
        }
    </style>
</head>
<body>
<form method="post" action="" enctype="multipart/form-data">
    <table border="0">
        <thead>
        <tr>
            <td>LOGIN</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Username</td>
            <td><input id="txtUsername" name="txtUsername" placeholder="Username"/></td>

            <td>Password</td>
            <td><input id="txtPassword" name="txtPassword" placeholder="Password"/></td>

            <td>&nbsp;</td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td><input type="submit" value="Login" name="submit"/></td>
        </tr>
        </tfoot>
    </table>
</form>
</body>
</html>
