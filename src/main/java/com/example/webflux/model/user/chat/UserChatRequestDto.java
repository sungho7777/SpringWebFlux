package com.example.webflux.model.user.chat;

import com.example.webflux.model.llmclient.LlmModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserChatRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 2669293150219020249L;

    private String request;
    private LlmModel llmModel;
}
