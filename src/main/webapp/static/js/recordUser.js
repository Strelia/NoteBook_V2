$(document).ready(function() {
    $("#accept").click(function() {
        if ($(this).prop("checked")) {
            $("#file_answer").css({visibility: "hidden"});

            var input = $("#file_answ");
            input.replaceWith(input.val('').clone(true));
        } else {
            $("#file_answer").css({visibility: "visible"});
        }
    });
});