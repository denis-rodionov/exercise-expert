jQuery(document).ready(function($) {
    console.log("DOM is loaded");

    $.get("/notification", function(data) {
        $(".userNotification").text(data);
    });
});

function showSuccessMessage(msg) {
    $.uiAlert({
        textHead: 'Success', // header
        text: msg, // Text
        bgcolor: '#19c3aa', // background-color
        textcolor: '#fff', // color
        position: 'bottom-left',// position . top And bottom ||  left / center / right
        icon: 'checkmark box', // icon in semantic-UI
        time: 2, // time
    })
}

function showErrorMessage(msg) {
    $.uiAlert({
        textHead: "Something went wrong", // header
        text: 'msg', // Text
        bgcolor: '#DB2828', // background-color
        textcolor: '#fff', // color
        position: 'bottom-left',// position . top And bottom ||  left / center / right
        icon: 'remove circle', // icon in semantic-UI
        time: 3, // time
    })
}