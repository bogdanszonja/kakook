$(document).ready(function () {
    webSocket.init("ws://" + window.location.hostname + ":8080/adminwebsocket");
});

$("#start-game").click(function () {
   event.preventDefault();
   webSocket.startGame();
});