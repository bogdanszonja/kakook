$(document).ready(function () {
    webSocket.init("ws://192.168.160.24:8080/websocket");
});

$("button").click(function () {
    event.preventDefault();
    console.log($(this).attr("id"));
    webSocket.sendAnswer($(this).attr("id"));
});