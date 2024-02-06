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
    <title>Web Socket STOMP chat</title>
    <%--    <link href="<c:url value='/css/socketio.css'/>" rel="stylesheet" type="text/css"/>--%>
    <script src="<c:url value='/META-INF/js/jquery-3.4.1.min.js'/>"></script>
    <script src="<c:url value='/META-INF/js/sockjs.min.js'/>"></script>
    <script src="<c:url value='/META-INF/js/stomp.min.js'/>"></script>
    <%--    <script src="<c:url value='/js/socket.io.js'/>"></script>--%>
</head>
<body onload="disconnect()">
<div>
    <div>
        <input type="text" id="from" placeholder="Choose a nickname"/>
    </div>
    <br/>
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">
            Disconnect
        </button>
    </div>
    <br/>
    <div id="conversationDiv">
        <input type="text" id="text" placeholder="Write a message..."/>
        <button id="sendMessage" onclick="sendMessage();">Send</button>
        <p id="response"></p>
    </div>
</div>
<script type="text/javascript">
    let stompClient = null;
    let sockEndpoint    = "/web/ws/chat";
    let sendEndpoint    = "/app/ws/chat";
    let listenEndpoint  = "/topic/messages";

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        document.getElementById('response').innerHTML = '';
    }

    function connect() {
        let socket = new SockJS(sockEndpoint);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe(listenEndpoint, function (messageOutput) {
                showMessageOutput(JSON.parse(messageOutput.body));
            });
        });
    }

    function disconnect() {
        if (stompClient != null) {
            stompClient.unsubscribe();
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    function sendMessage() {
        let from = document.getElementById('from').value;
        let text = document.getElementById('text').value;
        stompClient.send(sendEndpoint, {},
            JSON.stringify({'from': from, "to": "ALL", 'content': text}));
        document.getElementById("text").value = "";
    }

    function showMessageOutput(messageOutput) {
        let response = document.getElementById('response');
        let p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(messageOutput.from + " to " + messageOutput.to + ": "
            + messageOutput.content + " (" + messageOutput.time + ")"));
        response.appendChild(p);
    }
</script>
</body>
</html>
