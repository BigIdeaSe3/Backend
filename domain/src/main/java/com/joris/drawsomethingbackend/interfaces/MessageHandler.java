package com.joris.drawsomethingbackend.interfaces;

import com.joris.drawsomethingbackend.enums.GameMessageType;
import com.joris.drawsomethingbackend.models.WebsocketGameMessage;

public interface MessageHandler {
    void register(GameMessageType commandName, Command command);
    WebsocketGameMessage execute(WebsocketGameMessage message, Integer id);
}