let narrative = {
    on_new_nickname: function (nickname) {
        $(".nicknames").append("<i class=\"nickname\">" + nickname + "</i>")
    },
    start_game: function () {
        $(".waiting-players").hide();
        $(".game-starting").show();
    },
    on_new_question: function (question, answers) {
        $(".game-starting").hide();
        $(".question-wrapper .question").html(question);
        var i = 0;
        for (var answer in answers) {
            $("#answer" + i).html(answer).css("opacity", 1);
            i++;
        }
        $(".question-wrapper").show();
    },
    on_answer: function (good_answer_number, answer_statistic) {
        for (let i = 1; i <= 4; i++){
            if (i !== good_answer_number)
                $("#answer" + i).css("opacity", 0.5);
        }

    }
};