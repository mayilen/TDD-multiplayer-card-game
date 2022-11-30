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
 id = Math.floor(Math.random()*10000);
var socket = new SockJS('/room');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            setPlayerNum(JSON.parse(greeting.body))
            //showGreeting(true);
           // console.log("JSON.parse(greeting.body): "+JSON.parse(greeting.body))

        });
stompClient.subscribe('/topic/scores', function (greeting) {
            console.log("llllkkkkokok"+JSON.parse(greeting.body))
            loadScores(JSON.parse(greeting.body))
            //showGreeting(true);
           // console.log("JSON.parse(greeting.body): "+JSON.parse(greeting.body))

        });
 stompClient.subscribe('/user/queue/specific-user/'
              + id, function (msgOut) {
              console.log("msg: "+JSON.parse(msgOut.body))
        showHand(JSON.parse(msgOut.body));
            $("#greetings").hide
            })
    });
 setTimeout(function () {
  console.log("after")

     stompClient.send("/app/hello", {}, JSON.stringify({'id':id}));

     }, 1000);

}
function loadScores(scores){
    for(let i=0;i<scores.length;i++){
        $("#score"+(i+1)).html(scores[i]);
        console.log(scores[i])
    }
}
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}
function showGreeting(message) {

    $("#greetings").hide;


}
function setPlayerNum(num){
console.log(num);
    $("#playerID").html(num);
}
function play(){

    if($( "#suite").is(':visible')){
    console.log( $( "#card").val());
    console.log($( "#suite").val())
    }else{
     console.log( $( "#card").val());
    }

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
    $( "#card" ).on('input',function(){
    if($( "#card" ).val().includes("8")){
    console.log("dfgdf")
        $( "#suiteLabel").prop("hidden",false)
        $( "#suite").prop("hidden",false)
    }else{
     $( "#suiteLabel").prop("hidden",true)
            $( "#suite").prop("hidden",true)
    }
    })
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { play(); });
});