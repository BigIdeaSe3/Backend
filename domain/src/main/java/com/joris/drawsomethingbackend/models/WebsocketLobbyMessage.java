package com.joris.drawsomethingbackend.models;


import com.joris.drawsomethingbackend.enums.LobbyMessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebsocketLobbyMessage {
    private LobbyMessageType type;
    private Object message;
}