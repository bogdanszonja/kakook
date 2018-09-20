let narrative = {
    on_new_nickname: function (nickname) {
        $(".nicknames").append("<i class=\"nickname\">" + nickname + "</i>")
    },
    start_game: function () {
        $(".waiting-players").hide();
        $(".game-starting").show();
    },
    on_new_question: function (question, answers) {
        $("#next-question").hide();
        $(".game-starting").hide();
        $(".question-wrapper .question").html(question);
        $(".check").html("");
        for (var index in answers) {
            let answer = answers[index];
            console.log(answer);
            let i = parseInt(index) + 1;
            console.log(i);
            $("#answer" + i + " .answer-text").html(answer);
            $("#answer" + i).css("opacity", 1);
        }
        $(".question-wrapper").show();
    },
    on_answer: function (good_answer_number, answer_statistic) {
        $("#next-question").show();
        for (let i = 1; i <= 4; i++){
            if (i !== good_answer_number)
                $("#answer" + i).css("opacity", 0.4);
            else
                $("#answer" + i + " .check").html("<i class=\"fas fa-check fa-1x\"></i>");
        }
    }
};