<%--
  Created by IntelliJ IDEA.
  User: Harry Nguyen
  Date: 10/1/2019
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/welcome.css"/>
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="js/crypto.js"></script>
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
                <td><input type="submit" id="submit" value="Login" name="submit"/></td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#submit').bind("click", function () {
            alert("User click");
            return false;
        });
    });
</script>
</body>
</html>
