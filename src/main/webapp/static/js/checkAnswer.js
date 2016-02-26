$(document).ready(function() {
    $("#check_answer").click(function() {
        if ($(this).is(":checked")) {
            $("#file_answer").hide();
        } else {
            $("#file_answer").show();
        }
    });
});