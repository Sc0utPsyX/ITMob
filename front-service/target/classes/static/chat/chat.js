const CHAT_SERVICE = "http://localhost:9090";

function onError() {
    console.log("NotConnected")
}

const connect = () => {
    const Stomp = require("stompjs");
    var SockJS = require("sockjs-client");
    SockJS = new SockJS("http://localhost:9090/ItMobChat");
    stompClient = Stomp.over(SockJS);
    stompClient.connect({}, onConnected, onError);
};

const onConnected = () => {
    stompClient.subscribe(
        "/user/" + currentUser.id + "/queue/messages",
        onMessageReceived
    );
};

function findChatMessage(id) {
    if (!localStorage.getItem("accessToken")) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: CHAT_SERVICE + "/messages/" + senderId + "/" + recipientId,
        method: "GET",
    });
}

function setMessages(newMessages) {
    
}

const onMessageReceived = (msg) => {

};

function findChatMessages(senderId, recipientId) {
    if (!localStorage.getItem("accessToken")) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: CHAT_SERVICE + "/messages/" + senderId + "/" + recipientId,
        method: "GET",
    });


const sendMessage = (msg) => {
    if (msg.trim() !== "") {
        const message = {
            senderId: currentUser.id,
            recipientId: activeContact.id,
            senderName: currentUser.name,
            recipientName: activeContact.name,
            content: msg,
            timestamp: new Date(),
        };
        stompClient.send("/ItMobChat/chat", {}, JSON.stringify(message));
    }
};}

