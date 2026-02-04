package com.example.webflux.service.llmclient;

import com.example.webflux.exception.CommonError;
import com.example.webflux.exception.CustomErrorType;
import com.example.webflux.exception.ErrorTypeException;
import com.example.webflux.model.llmclient.LlmChatRequestDto;
import com.example.webflux.model.llmclient.LlmChatResponseDto;
import com.example.webflux.model.llmclient.LlmType;
import com.example.webflux.model.llmclient.gemini.request.GeminiChatRequestDto;
import com.example.webflux.model.llmclient.gemini.response.GeminiChatResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@Service
@RequiredArgsConstructor
public class GeminiWebClientService implements LlmWebClientService{

    private final WebClient webClient;

    @Value("${llm.gemini.key}")
    private String geminiApiKey;

    @Override
    public Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto requestDto) {
        GeminiChatRequestDto geminiChatRequestDto = new GeminiChatRequestDto(requestDto);
        String geminiUri = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + geminiApiKey;

        return webClient.post()
                .uri(geminiUri)
                .bodyValue(geminiChatRequestDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (clientResponse -> {
                    return clientResponse.bodyToMono(String.class).flatMap(body -> {
                        log.error("Error Response: {}" , body);
                        return Mono.error(new ErrorTypeException("API 요청 실패: " + body, CustomErrorType.GEMINI_RESPONSE_ERROR));
                    });
                }))
                .bodyToMono(GeminiChatResponseDto.class)
                .map(LlmChatResponseDto::new);
    }

    @Override
    public Flux<LlmChatResponseDto> getChatCompletionStream(LlmChatRequestDto requestDto) {
        GeminiChatRequestDto geminiChatRequestDto = new GeminiChatRequestDto(requestDto);
        String geminiUri = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:streamGenerateContent?key=" + geminiApiKey;
        //AtomicInteger count = new AtomicInteger();

        return webClient.post()
                .uri(geminiUri)
                .bodyValue(geminiChatRequestDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (clientResponse -> {
                    return clientResponse.bodyToMono(String.class).flatMap(body -> {
                        log.error("Error Response: {}" , body);
                        return Mono.error(new ErrorTypeException("API 요청 실패: " + body, CustomErrorType.GEMINI_RESPONSE_ERROR));
                    });
                }))
                .bodyToFlux(GeminiChatResponseDto.class)
                .map(LlmChatResponseDto::new);
                /*
                .map((response) -> {
                    try{
                        if(count.incrementAndGet() % 5 == 0){
                            throw new ErrorTypeException("API 요청 실패: " + response.getSingleText(), CustomErrorType.GEMINI_RESPONSE_ERROR);
                        }
                        return new LlmChatResponseDto(response);
                    }catch (Exception e){
                        log.error("테스트를 위한..");
                        return  new LlmChatResponseDto(new CommonError(CustomErrorType.GEMINI_RESPONSE_ERROR.getCode(), e.getMessage()));
                    }
                });
                */
    }

    @Override
    public LlmType getLlmType() {
        return LlmType.GEMINI;
    }
}

