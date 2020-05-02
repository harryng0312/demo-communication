//connecting to our signaling server 
// let conn = new WebSocket('ws://localhost:9090/socket');
let add = $.trim($("#add").prop("innerHTML"));
let protocol = $.trim($("#protocol").prop("innerHTML"));
let conn = new WebSocket(protocol + "://" + add + "/ws/socket");
let stunAdds = [
    "stun:iphone-stun.strato-iphone.de:3478",
    // "stun:numb.viagenie.ca:3478",
    // "stun:s1.taraba.net:3478",
    // "stun:s2.taraba.net:3478",
    // "stun:stun.12connect.com:3478"
];
let peerConnection;
let sendDataChannel;
let receiveDataChannel;
let input = document.getElementById("messageInput");

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

function initialize() {
    // let configuration = {"iceServers": [{"urls": stunAdds}]};
    let configuration = null;

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

    peerConnection.onicecandidateerror = function (evt) {
        console.log("ICE Candidate err:" + evt);
    }

    // creating data channel
    // dataChannel = peerConnection.createDataChannel("dataChannel", {
    //     reliable: true
    // });
    // let dataChannelOptions = {
    //     reliable: true,
    //     maxRetransmitTime: "2000"
    // };
    // dataChannel = peerConnection.createDataChannel("dataChannel");
    let handleChannelCallback = function (event) {
        receiveDataChannel = event.channel;
        receiveDataChannel.onopen = dataChannelOpen;
        receiveDataChannel.onclose = dataChannelClose;
        receiveDataChannel.onmessage = dataChannelMessage;
        receiveDataChannel.onerror = dataChannelError;
    };
    peerConnection.ondatachannel = handleChannelCallback;
    sendDataChannel = peerConnection.createDataChannel("dataChannel");
    sendDataChannel.onopen = dataChannelOpen;
    sendDataChannel.onclose = dataChannelClose;
    sendDataChannel.onmessage = dataChannelMessage;
    sendDataChannel.onerror = dataChannelError;
}

function dataChannelOpen(evt) {
    console.log("data channel is opened");
}

function dataChannelError(error) {
    console.log("Error occured on datachannel:", error);
}

// when we receive a message from the other peer, printing it on the console
function dataChannelMessage(event) {
    console.log("message:", event.data);
}

function dataChannelClose() {
    console.log("data channel is closed");
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
    console.log("Candidate:" + JSON.stringify(candidate));
};

function handleAnswer(answer) {
    peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
    console.log("connection established successfully!!");
};

function sendMessage() {
    sendDataChannel.send(input.value);
    input.value = "";
}
