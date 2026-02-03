package com.example.webflux.controller.user.chat;

import com.example.webflux.model.user.chat.UserChatRequestDto;
import com.example.webflux.model.user.chat.UserChatResponseDto;
import com.example.webflux.service.user.chat.UserChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class UserChatController {

    private final UserChatService userChatService;

    @PostMapping("/oneshot")
    public Mono<UserChatResponseDto> onShotChat(@RequestBody UserChatRequestDto userChatRequestDto){

        return userChatService.getOneShotChat(userChatRequestDto);
    }
}
