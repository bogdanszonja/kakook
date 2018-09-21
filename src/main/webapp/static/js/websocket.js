let webSocket = {
    websocket: null,
    init: function (wsURI) {
        this.websocket = new WebSocket(wsURI);
        this.websocket.onclose = function(evt) { webSocket._onClose(evt); };
        this.websocket.onopen = function(evt) { webSocket._onOpen(evt); };
        this.websocket.onmessage = function(evt) { webSocket._onMessage(evt); };
        this.websocket.onerror = function(evt) { webSocket._onError(evt); };
        console.log("WebSocket initalized");
    },
    _onOpen: function (evt) {
        console.log("opened")
    },
    _onClose: function (evt) {
        console.log("closed")
    },
    _onMessage: function (evt) {
        console.log(evt.data);
        let response = JSON.parse(evt.data);
        if (response.hasOwnProperty("action") && response["action"] === "setup_nickname" && response["success"] === true )
            narrative.setup_nickname_success(response["nickname"]);
        else if (response.hasOwnProperty("action") && response["action"] === "setup_nickname" && response["success"] === false && response["reason"] === "game_already_started")
            alert("Game started already!");
        else if (response.hasOwnProperty("action") && response["action"] === "send_answer" && response["success"] === true )
            narrative.on_answer_sent(response["answer"]);
        else if (response.hasOwnProperty("server_action") && response["server_action"] === "start_game")
            narrative.start_game();
        else if (response.hasOwnProperty("server_action") && response["server_action"] === "new_question_shown")
            narrative.on_new_question();
        else if (response.hasOwnProperty("server_action") && response["server_action"] === "answer_shown")
            narrative.on_answer(response["is_answer_good"], response["rank"], response["points"])
    },
    _onError: function (evt) {

    },
    _sendMessage: function (message) {
        this.websocket.send(message);
    },
    sendAnswer: function (answer) {
        this._sendMessage(JSON.stringify({'action': 'send_answer', 'answer': answer}));
    },
    sendNickname: function (nickname) {
        this._sendMessage(JSON.stringify({'action': 'setup_nickname', 'nickname': nickname}))
    }
};