//connecting to our signaling server 
// let conn = new WebSocket('ws://localhost:9090/socket');
let add = $.trim($("#add").prop("innerHTML"));
let protocol = $.trim($("#protocol").prop("innerHTML"));
let conn = new WebSocket(protocol + "://" + add + "/ws/chat-basic");

conn.onopen = function () {
    console.log("Connected to the signaling server");
    initialize();
};

conn.onmessage = function (msg) {
    console.log("Got message", msg.data);
    let content = JSON.parse(msg.data);
    let data = content.data;
    switch (content.event) {
        // when somebody wants to call us
        case "offer":
            handleOffer(data);
            break;
        case "answer":
            handleAnswer(data);
            break;
        // when a remote peer sends an ice candidate to us
        case "candidate":
            handleCandidate(data);
            break;
        default:
            break;
    }
};

function send(message) {
    conn.send(JSON.stringify(message));
}

let peerConnection;
let dataChannel;
let input = document.getElementById("messageInput");

function initialize() {
    let configuration = {
        "iceServers": [
            {"url": "stun.l.google.com:19302"},
            {"url": "stun1.l.google.com:19302"},
            {"url": "stun2.l.google.com:19302"},
            {"url": "stun3.l.google.com:19302"},
            {"url": "stun4.l.google.com:19302"}
        ]
    };

    // peerConnection = new RTCPeerConnection(configuration, {
    //     optional: [{
    //         RtpDataChannels: true
    //     }]
    // });
    peerConnection = new RTCPeerConnection(configuration);

    // Setup ice handling
    peerConnection.onicecandidate = function (event) {
        if (event.candidate) {
            send({
                event: "candidate",
                data: event.candidate
            });
        }
    };

    // creating data channel
    dataChannel = peerConnection.createDataChannel("dataChannel", {
        reliable: true
    });

    dataChannel.onerror = function (error) {
        console.log("Error occured on datachannel:", error);
    };

    // when we receive a message from the other peer, printing it on the console
    dataChannel.onmessage = function (event) {
        console.log("message:", event.data);
    };

    dataChannel.onclose = function () {
        console.log("data channel is closed");
    };
}

function createOffer() {
    peerConnection.createOffer(function (offer) {
        send({
            event: "offer",
            data: offer
        });
        peerConnection.setLocalDescription(offer);
    }, function (error) {
        alert("Error creating an offer");
    });
}

function handleOffer(offer) {
    peerConnection.setRemoteDescription(new RTCSessionDescription(offer));

    // create and send an answer to an offer
    peerConnection.createAnswer(function (answer) {
        peerConnection.setLocalDescription(answer);
        send({
            event: "answer",
            data: answer
        });
    }, function (error) {
        alert("Error creating an answer");
    });

};

function handleCandidate(candidate) {
    peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
};

function handleAnswer(answer) {
    peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
    console.log("connection established successfully!!");
};

function sendMessage() {
    dataChannel.send(input.value);
    input.value = "";
}
