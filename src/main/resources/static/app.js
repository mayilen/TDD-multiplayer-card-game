var stompClient = null;
let id=null;
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
     $("#connection").hide();
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    id = Math.floor(Math.random()*10000000);
    var socket = new SockJS('/room');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
           // setPlayerNum(JSON.parse(greeting.body))

    });
    stompClient.subscribe('/topic/scores', function (greeting) {
        console.log("llllkkkkokok"+JSON.parse(greeting.body))
        loadScores(JSON.parse(greeting.body))

    });

    stompClient.subscribe('/topic/deck', function (greeting) {

     console.log("topcarrd"+JSON.parse(greeting.body))
            deck(JSON.parse(greeting.body))

        });
    stompClient.subscribe('/topic/topcard', function (greeting) {
    console.log("topcard is "+greeting)
 console.log("topcarrd"+JSON.parse(greeting.body))
        topCard(JSON.parse(greeting.body))

    });
 stompClient.subscribe('/user/queue/hand/'
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
function deck({message}){
$("#deck").html(message)
console.log(message)
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
function topCard({message}){

 $("#topCard").html(message);
}
function setPlayerNum(num){
console.log(num);
    $("#playerID").html(num);
}
function play(){
let cards=$( "#card").val();
    if($( "#suite").is(':visible')){
    let suite=$( "#suite").val()
    stompClient.send("/app/play", {}, JSON.stringify({'card':cards,'suite':suite}));
    console.log( $( "#card").val());
    console.log($( "#suite").val())
    }else{
     stompClient.send("/app/play", {}, JSON.stringify({'card':cards}));
     console.log( $( "#card").val());
    }

}
function draw(){
stompClient.send("/app/draw", {})
}
function showHand({player,card,playerTurn,error}) {
console.log("hand is "+card);
    if(player!=0)
    setPlayerNum(player)
    if(card!=null)
    $("#hand").html(card);
if(playerTurn!=null){
$("#turn").html(playerTurn);
if(playerTurn=="Your Turn"){
    $("#play").show()
}else{
  $("#play").hide()
}
}
if(error!=null){
    alert(error)
    }

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
    $("#draw").click(function(){draw();})
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { play(); });
});