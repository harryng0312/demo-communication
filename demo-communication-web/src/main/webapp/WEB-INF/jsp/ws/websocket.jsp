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
<%--    <link href="<c:url value='/css/socketio.css'/>" rel="stylesheet" type="text/css"/>--%>
    <script src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<%--    <script src="<c:url value='/js/socket.io.js'/>"></script>--%>
</head>
<body>
<ul id="messages"></ul>
<form id="chat-form" action="">
    <table>
        <tr>
            <td colspan="2">
                <input type="text" id="username" placeholder="Username"/>
                <button type="button" onclick="connect();" >Connect</button>
            </td>
        </tr>
        <tr>
            <td>
                <textarea readonly="true" rows="10" cols="80" id="log"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" size="51" id="msg" placeholder="Message"/>
                <button type="button" onclick="send();" >Send</button>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    let ws;

    function connect() {
        let username = document.getElementById("username").value;

        let host = document.location.host;
        let pathname = document.location.pathname;

        ws = new WebSocket("ws://" +host + "/web/ws/chat/" + username);
        // ws = new WebSocket("ws://" + host + "/web/ws/chat");
        ws.onmessage = function(event) {
            let log = document.getElementById("log");
            console.log(event.data);
            let message = JSON.parse(event.data);
            log.innerHTML += message.from + " : " + message.content + "\n";
        };
    }

    function send() {
        let content = document.getElementById("msg").value;
        let json = JSON.stringify({
            "content":content
        });

        ws.send(json);
    }
</script>
</body>
</html>
