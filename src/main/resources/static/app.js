var stompClient = null;
let id=null;
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
//    var socket = new SockJS('/room');
//    stompClient = Stomp.over(socket);
//    stompClient.connect({}, function (frame) {
//        setConnected(true);
//        console.log('Connected: ' + frame);
//        stompClient.subscribe('/topic/greetings', function (greeting) {
//
//            showGreeting(JSON.parse(greeting.body));
//
//        });
// stompClient.subscribe('user/queue/specific-user'
//              + '-user' + this.id, function (msgOut) {
//
//            })
//    });
 id = Math.floor(Math.random()*10000);
var socket = new SockJS('/room');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body))
           // console.log("JSON.parse(greeting.body): "+JSON.parse(greeting.body))

        });
 stompClient.subscribe('/user/queue/specific-user/'
              + id, function (msgOut) {
              console.log("msg: "+JSON.parse(msgOut.body))
        showHand(JSON.parse(msgOut.body));
            })
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'id':id}));
     $( "#send" ).prop("disabled",true);

}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message.message + "</td></tr>");
}
function showHand(message) {
console.log("hand is "+message);
    $("#hand").html(message.message);
}
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});