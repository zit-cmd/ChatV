package com.vit.utils;

import com.google.gson.Gson;
import com.vit.model.MessageModel;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.logging.Logger;

public class MessageDecoder implements Decoder.Text<MessageModel> {
    private final Logger log = Logger.getLogger(getClass().getName());

    @Override
    public MessageModel decode(String s) throws DecodeException {
        log.info("incoming message : " + s);

        Gson gson = new Gson();
        MessageModel message = gson.fromJson(s, MessageModel.class);
        return message;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // do nothing
    }

    @Override
    public void destroy() {
        // do nothing
    }
}