$(document).ready(function () {
    webSocket.init("ws://localhost:8080/websocket");
});

$(".nickname-input button").click(function () {
    event.preventDefault();
    let inputVal = $(".nickname-input input").val();
    if (inputVal === "")
        alert("Please choose a nickname!");
    else if (inputVal.length > 18)
        alert("Nickname too long! Max length is 17!");
    if (inputVal !== "")
        webSocket.sendNickname(inputVal);
});

$(".answer-selector button").click(function () {
    event.preventDefault();
    webSocket.sendAnswer($(this).attr("id"));
});