<%--
  Created by IntelliJ IDEA.
  User: harryng
  Date: 4/22/20
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Socket.IO chat</title>
    <link href="<c:url value='/META-INF/css/socketio.css'/>" rel="stylesheet" type="text/css"/>
    <script src="<c:url value='/META-INF/js/jquery-3.4.1.min.js'/>"></script>
    <script src="<c:url value='/META-INF/js/socket.io.js'/>"></script>
</head>
<body>
<ul id="messages"></ul>
<form id="chat-form" action="">
    <input id="m" autocomplete="off"/>
    <button>Send</button>
</form>
<script type="text/javascript">
    $(function () {
        let socket = io();
        $('#chat-form').submit(function (e) {
            e.preventDefault(); // prevents page reloading
            socket.emit('chat message', $('#m').val());
            $('#m').val('');
            return false;
        });
        socket.on('chat message', function(msg){
            $('#messages').append($('<li>').text(msg));
        });
    });
</script>
</body>
</html>
