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
        if (response.hasOwnProperty("server_action") && response["server_action"] === "new_nickname")
            narrative.on_new_nickname(response["nickname"]);
        else if (response.hasOwnProperty("server_action") && response["server_action"] === "start_game")
            narrative.start_game();
        else if (response.hasOwnProperty("server_action") && response["server_action"] === "new_question")
            narrative.on_new_question(response["description"], response["answers"]);
        else if (response.hasOwnProperty("server_action") && response["server_action"] === "show_answer")
            narrative.on_answer(response["good_answer_number"], response["answer_statistic"]);
    },
    _onError: function (evt) {

    },
    _sendMessage: function (message) {
        this.websocket.send(message);
    },
    startGame: function () {
        this._sendMessage(JSON.stringify({"action": "start_game"}))
    },
    nextQuestion: function () {
        this._sendMessage(JSON.stringify({"action": "next_question"}))
    }
};