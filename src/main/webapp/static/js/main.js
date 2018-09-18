$(document).ready(function () {
    webSocket.init("ws://localhost:8080/websocket");
});

$(".nickname-input button").click(function () {
    event.preventDefault();
    console.log($(this).attr("id"));
    webSocket.sendNickname($(".nickname-input input").val());
});