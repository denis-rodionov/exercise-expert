jQuery(document).ready(function($) {
    console.log("DOM is loaded");


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