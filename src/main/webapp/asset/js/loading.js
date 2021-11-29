var myId = document.getElementById("myid").value;
function loadListFriend(myId) {
    // Create a request variable and assign a new XMLHttpRequest object to it.
    var request = new XMLHttpRequest();
    // Open a new connection, using the GET request on the URL endpoint
    request.open('GET', 'http://localhost:8080/chat_servlet/api-load?sender='+myId, true);
    request.onload = function () {
        var data = JSON.parse(this.response);
        console.log(data);
        showList(data);
    };
    // Send request
    request.send();
}

function showList(result) {
    let html = ""
    const listElement = document.getElementById('list-friends')
    // console.log(result);
    result.map( (item, index) => {
        // console.log("Item: "+item.friendName);
        var pathImg = "resources/"+item.friendAvatar
        const dateObject = new Date(item.message[0].createdDate);

        if (index === 0) {
            html += '<div class="item-friend flex justify-between items-center p-3 hover:bg-gray-800 bg-gray-800 rounded-lg relative cs-p index" onclick="choooseFriend(this)" data-id="'+item.friendId+'">' +
                    '<div class="w-16 h-16 relative flex flex-shrink-0">' +
                        '<img class="shadow-md rounded-full w-full h-full object-cover" src="'+pathImg+'" alt=""/></div>'+
                    '<div class="flex-auto min-w-0 ml-4 mr-6 hidden md:block group-hover:block">'+
                        '<p>'+item.friendName+'</p>'+
                        '<div class="flex items-center text-sm text-gray-600">'+
                            '<div class="min-w-0"><p class="truncate">'+item.message[0].content+'</p></div>'+
                            '<p class="ml-2 whitespace-no-wrap">'+dateObject.toLocaleString()+'</p>'+
                '</div></div></div>'
        } else {
            html += '<div class="item-friend flex justify-between items-center p-3 hover:bg-gray-800 rounded-lg relative cs-p" onclick="choooseFriend(this)" data-id="'+item.friendId+'">' +
                    '<div class="w-16 h-16 relative flex flex-shrink-0">' +
                        '<img class="shadow-md rounded-full w-full h-full object-cover" src="'+pathImg+'" alt=""/></div>'+
                    '<div class="flex-auto min-w-0 ml-4 mr-6 hidden md:block group-hover:block">'+
                        '<p>'+item.friendName+'</p>'+
                        '<div class="flex items-center text-sm text-gray-600">'+
                            '<div class="min-w-0"><p class="truncate">'+item.message[0].content+'</p></div>'+
                            '<p class="ml-2 whitespace-no-wrap">'+dateObject.toLocaleString()+'</p>'+
                '</div></div></div>'
        }
         
        return html;
    })
    listElement.innerHTML = html;
    choooseFriend(document.querySelector(".index"))
}

loadListFriend(myId); // đã render

function choooseFriend(event) {
    const list = document.querySelectorAll(".item-friend");
    list.forEach( item => {
        item.classList.remove("bg-gray-800");
        item.classList.remove("index")
    });
    event.classList.add("bg-gray-800");
    event.classList.add("index");
    const id = event.dataset.id;
    // document.querySelector(".my-friend-name").textContent = name;
    // document.querySelector(".chat-avatar").src = pathImg;
    loadMessage(id);
}

function loadMessage(myFriend) {
    // Create a request variable and assign a new XMLHttpRequest object to it.
    var request = new XMLHttpRequest();
    // Open a new connection, using the GET request on the URL endpoint
    request.open('GET', 'http://localhost:8080/chat_servlet/api-load?sender='+myId+'&receiver='+myFriend, true);
    request.onload = function () {
        var data = JSON.parse(this.response);
        console.log(data);
        document.querySelector(".my-friend-name").textContent = data.friendName;
        document.querySelector(".chat-avatar").src = "resources/"+data.friendAvatar;
        setMessage(data, myFriend);
    };
    // Send request
    request.send();
}


function setMessage(arr, myFriend) {
    const chatBody = document.querySelector(".chat-body");
    var html = '';
    arr.message.map( item => {
        // let temp = 
        if (item.receiverId.toString() === myFriend && item.senderId.toString() === myId) {
            html = '<div class="flex flex-row justify-end"><div class="messages text-sm text-white grid grid-flow-row gap-2">'+
                '<div class="flex items-center flex-row-reverse group">'+
                    '<p class="px-6 py-3 rounded-t-full rounded-l-full bg-blue-700 max-w-xs lg:max-w-md">'+item.content+'</p></div></div></div>' + html;
        } else {
            html = '<div class="flex flex-row justify-start"><div class="w-8 h-8 relative flex flex-shrink-0 mr-4">'+
                '<img class="shadow-md rounded-full w-full h-full object-cover" src="resources/'+arr.friendAvatar+'" alt=""/>'+
            '</div><div class="messages text-sm text-gray-700 grid grid-flow-row gap-2"><div class="flex items-center group">'+
            '<p class="px-6 py-3 rounded-t-full rounded-r-full bg-gray-800 max-w-xs lg:max-w-md text-gray-200">'+item.content+'</p></div></div></div>' + html;
        }
        return html;
    });
    chatBody.innerHTML = html;
}

//  --------------WEBSOCKET-------------

var ws;

ws = new WebSocket("ws://" + document.location.host + "/chat_servlet/chat/" + myId);

ws.onmessage = function(event) {
    // nhận tin từ người khác gửi đến
	var message = JSON.parse(event.data);
    console.log("hi ",message);
    log = document.querySelector(".chat-body");

    let index =  document.querySelector(".index");
    let src = index.querySelector("img").src;

    log.innerHTML += '<div class="flex flex-row justify-start"><div class="w-8 h-8 relative flex flex-shrink-0 mr-4">'+
        '<img class="shadow-md rounded-full w-full h-full object-cover" src="'+src+'" alt=""/>'+
    '</div><div class="messages text-sm text-gray-700 grid grid-flow-row gap-2"><div class="flex items-center group">'+
    '<p class="px-6 py-3 rounded-t-full rounded-r-full bg-gray-800 max-w-xs lg:max-w-md text-gray-200">'+message.content+'</p></div></div></div>';

    loadListFriend(myId);
};

// // gửi tin
function send(e) {
    if(event.key === 'Enter' && e.value !== "") {
        // console.log("chat" + e.value);
        receiverId = document.querySelector(".index").dataset.id;
        var json = JSON.stringify({
            "senderId": myId,
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

