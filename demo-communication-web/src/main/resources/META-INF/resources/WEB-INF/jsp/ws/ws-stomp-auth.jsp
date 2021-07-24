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
    <script src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
    <script src="<c:url value='/js/sockjs.min.js'/>"></script>
    <script src="<c:url value='/js/stomp.min.js'/>"></script>
    <%--    <script src="<c:url value='/js/socket.io.js'/>"></script>--%>
</head>
<body onload="disconnect()">
<div>
    <div>
        <input type="text" id="from" placeholder="Choose a nickname"/><br/>
        <input type="text" id="txtTo" placeholder="To"/><br/>
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
    let sockEndpoint = "/web/ws/chat";
    let sendEndpoint = "/app/ws/chat/user";
    let listenEndpoint = "/topic/messages";

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        document.getElementById('response').innerHTML = '';
    }

    function connect() {
        let socket = new SockJS(sockEndpoint);
        stompClient = Stomp.over(socket);
        stompClient.connect({"user": document.getElementById('from').value}, frame => {
            setConnected(true);
            let from = document.getElementById('from').value;
            console.log('Connected: ' + frame);
            stompClient.subscribe(listenEndpoint + "/", receivedMessage);
            stompClient.subscribe(listenEndpoint + "/" + from, receivedMessage);
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

    function receivedMessage(messageOutput) {
        showMessageOutput(JSON.parse(messageOutput.body));
    }

    function sendMessage() {
        let from = document.getElementById('from').value;
        let to = document.getElementById("txtTo").value;
        let text = document.getElementById('text').value;
        let msg = JSON.stringify({'from': from, "to": to, 'content': text});
        stompClient.send(sendEndpoint, {}, msg);
        document.getElementById("text").value = "";
    }

    function showMessageOutput(messageOutput) {
        let response = document.getElementById('response');
        let text = document.createTextNode(
            messageOutput.from + " to " + messageOutput.to + ": "
            + messageOutput.content + " (" + messageOutput.time + ")");
        let p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(text);
        response.appendChild(p);
    }
</script>
</body>
</html>
