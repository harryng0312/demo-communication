<%--
  Created by IntelliJ IDEA.
  User: Harry Nguyen
  Date: 10/1/2019
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
<%--    ${pageContext.request.contextPath}--%>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/welcome.css'/>"/>
    <script type="text/javascript" src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jose.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/util.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/crypto.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/pbkdf2.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/auth.js'/>"></script>
</head>
<body>
<div class="center">
    <form id="authForm" method="post" action="login" onsubmit="return false;">
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
                <td><input id="txtUsername" name="txtUsername" placeholder="Username" autocomplete="off"/></td>
            </tr>
            <tr>
                <td>Password</td>
            </tr>
            <tr>
                <td><input type="password" id="txtPassword" name="txtPassword" placeholder="Password"
                           autocomplete="off"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td><input type="submit" id="submit" value="Login" name="submit"/><input id="tokenId" name="tokenId"
                                                                                         type="hidden"/></td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#submit').bind("click", function () {
            Authenticator.loginByUnamePasswd($('#txtUsername').val(), $('#txtPassword').val(), function (result) {
                if (result) {
                    $(location).prop('href', 'afterLogin?tokenId=' + $('#txtUsername').val());
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
