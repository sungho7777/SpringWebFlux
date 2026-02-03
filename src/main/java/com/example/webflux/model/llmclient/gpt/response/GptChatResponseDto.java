package com.example.webflux.model.llmclient.gpt.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GptChatResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -4527356382244117674L;

    private List<GptChoice> choices;

    public GptChoice getSingleChoice(){
        return choices.stream().findFirst().orElseThrow();
    }
}
