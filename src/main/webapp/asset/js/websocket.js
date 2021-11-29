import loadListFriend from "./loading.js";

var ws;
var id = document.getElementById("myid").value;

ws = new WebSocket("ws://" + document.location.host + "/chat_servlet/chat/" + id);

ws.onmessage = function(event) {
    // nhận tin từ người khác gửi đến
	var message = JSON.parse(event.data);
    console.log("hi ",message);
    log = document.querySelector(".chat-body");

    let index =  document.querySelector(".index");
    let src = index.querySelector("img").src;

    loadListFriend(id);

    log.innerHTML += '<div class="flex flex-row justify-start"><div class="w-8 h-8 relative flex flex-shrink-0 mr-4">'+
        '<img class="shadow-md rounded-full w-full h-full object-cover" src="'+src+'" alt=""/>'+
    '</div><div class="messages text-sm text-gray-700 grid grid-flow-row gap-2"><div class="flex items-center group">'+
    '<p class="px-6 py-3 rounded-t-full rounded-r-full bg-gray-800 max-w-xs lg:max-w-md text-gray-200">'+message.content+'</p></div></div></div>';

    // loadListFriend(id);
};

// // gửi tin
function send(e) {
    if(event.key === 'Enter' && e.value !== "") {
        // console.log("chat" + e.value);
        receiverId = document.querySelector(".index").dataset.id;
        var json = JSON.stringify({
            "senderId": id,
            "receiverId": receiverId,
            "content": e.value,
            "typeReceiver": 0,
            "remove": false,
            "seen": false
        });
        ws.send(json);

        log = document.querySelector(".chat-body");

        log.innerHTML += '<div class="flex flex-row justify-end"><div class="messages text-sm text-white grid grid-flow-row gap-2">'+
        '<div class="flex items-center flex-row-reverse group">'+
            '<p class="px-6 py-3 rounded-t-full rounded-l-full bg-blue-700 max-w-xs lg:max-w-md">'+e.value+'</p></div></div></div>';
    // // set input empty
       e.value = "";     
    }
}