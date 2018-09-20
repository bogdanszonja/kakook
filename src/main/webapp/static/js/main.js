$(document).ready(function () {
    console.log(window.location.hostname);
    webSocket.init("ws://" + window.location.hostname + ":8080/websocket");
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

$("#answer-selector button").click(function () {
    event.preventDefault();
    webSocket.sendAnswer($(this).attr("id"));
});