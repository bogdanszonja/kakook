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
            $(".nicknames").append("<i class=\"nickname\">" + response["nickname"] + "</i>")
    },
    _onError: function (evt) {

    },
    _sendMessage: function (message) {
        this.websocket.send(message);
    }
};