jQuery(document).ready(function($) {
    console.log("DOM is loaded");

    // assigning an exercise to a student
    $(".add-exercise-to-selection").click(function () {
        console.log('Adding exercise...');
        let exerciseId = $(this).attr('data-exercise-id');
        let userId = $(this).attr('data-user-id');
        $.post("/select-exercise", { userId: userId, exerciseId: exerciseId})
            .done(function() {
                console.log("Request is finished!")
            });
    });
});