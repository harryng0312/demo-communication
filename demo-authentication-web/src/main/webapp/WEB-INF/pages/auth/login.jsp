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
        .center {
            max-width: 600px;
            margin: auto;
        }
    </style>
</head>
<body>
<div class="center">
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
            </tr>
            <tr>
                <td><input id="txtUsername" name="txtUsername" placeholder="Username"/></td>
            </tr>
            <tr>
                <td>Password</td>
            </tr>
            <tr>
                <td><input type="password" id="txtPassword" name="txtPassword" placeholder="Password"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td><input type="button" value="Login" name="submit"/></td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>
</body>
</html>
