package com.vit.controller.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.vit.dao.impl.MessageDAO;
import com.vit.model.MessageModel;
import com.vit.utils.MessageDecoder;
import com.vit.utils.MessageEncoder;

// id của người dùng
@ServerEndpoint(
        value="/chat/{id}",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class
)
public class ChatEndpoint {
    private final Logger log = Logger.getLogger(getClass().getName());

    private Session session;
    private static final Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String,Long> users = new HashMap<>();
    private MessageDAO messDao;
    
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) throws IOException, EncodeException {
        log.info(session.getId() + " connected!");
        
        messDao = new MessageDAO();
        this.session = session;
        Long idUser = Long.valueOf(id);
        chatEndpoints.add(this);
        users.put(session.getId(), idUser);
    }

    @OnMessage
    public void onMessage(Session session, MessageModel message) throws IOException, EncodeException {
        log.info(message.toString());
        Long idM = messDao.saveTableMessage(message);
    	if (idM != null) {
    		messDao.saveTableReceiver(idM, message);
    		sendMessageToOneUser(message);
    	}
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        log.info(session.getId() + " disconnected!");

        chatEndpoints.remove(this);
        users.remove(session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.warning(throwable.toString());
    }
    
//	gửi tin cho nhóm
//    private static void broadcast(MessageModel message) throws IOException, EncodeException {
//        for (ChatEndpoint endpoint : chatEndpoints) {
//            synchronized(endpoint) {
//                endpoint.session.getBasicRemote().sendObject(message);
//            }
//        }
//    }

//    gửi tin 1 vs 1
    private void sendMessageToOneUser(MessageModel message) throws IOException, EncodeException {
        for (ChatEndpoint endpoint : chatEndpoints) {
            synchronized(endpoint) {
                if (endpoint.session.getId().equals(getSessionId(message.getReceiverId()))) {
                	
                	endpoint.session.getBasicRemote().sendObject(message);
                }
            }
        }
    }

    private String getSessionId(Long to) {
        if (users.containsValue(to)) {
            for (String sessionId: users.keySet()) {
                if (users.get(sessionId).equals(to)) {
                    return sessionId;
                }
            }
        }
        return null;
    }
}


