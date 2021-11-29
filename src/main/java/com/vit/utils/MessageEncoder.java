package com.vit.utils;

import com.google.gson.Gson;
import com.vit.model.MessageModel;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.logging.Logger;

public class MessageEncoder implements Encoder.Text<MessageModel> {
    private final Logger log = Logger.getLogger(getClass().getName());
//    chuyển obj sang json
    @Override
    public String encode(MessageModel message) throws EncodeException {
        log.info("converting message obj to json format");

        Gson gson = new Gson();
        String json = gson.toJson(message);
        return json;
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