let narrative = {
    setup_nickname_success: function (nickname) {
        $(".nickname-input").hide();
        $(".loading-screen .nickname").html("Hi " + nickname + "!");
        $(".loading-screen").show();
    },
    start_game: function () {
        $(".loading-screen").hide();
        $(".game-starting").show();
    },
    on_new_question: function () {
        $(".good-answer").hide();
        $(".wrong-answer").hide();
        $(".game-starting").hide();
        $(".answer").hide();
        $("#answer-selector").show();
    },
    on_answer_sent: function(answer){
        $("#answer-selector").hide();
        $(".waiting-for-answer .answer-number").html(answer)
        if (answer === "answer1")
            $(".waiting-for-answer .answer-color").css("color", "red");
        else if (answer === "answer2")
            $(".waiting-for-answer .answer-color").css("color", "blue");
        else if (answer === "answer3")
            $(".waiting-for-answer .answer-color").css("color", "green");
        else if (answer === "answer4")
            $(".waiting-for-answer .answer-color").css("color", "#ffa700");
        $(".waiting-for-answer").show();

    },
    on_answer: function (is_answer_good, rank, points) {
        $(".waiting-for-answer").hide();
        $(".answer .rank").html(rank);
        $(".answer .points").html(points);
        if (is_answer_good)
            $(".good-answer").show();
        else
            $(".wrong-answer").show()
    }
};