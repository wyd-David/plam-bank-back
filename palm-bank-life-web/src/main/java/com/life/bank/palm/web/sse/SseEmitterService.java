package com.life.bank.palm.web.sse;

import com.alibaba.fastjson.JSON;
import com.life.bank.palm.service.user.utils.RequestContextUtil;
import com.life.bank.palm.web.sse.bo.DeepSeekRequest;
import com.life.bank.palm.web.sse.bo.SseEmitterUTF8;
import com.life.bank.palm.web.sse.constant.SseEmitterConstant;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author ：麻薯哥搞offer
 * @description ：sse链接维护
 * @date ：2024/05/12 17:13
 */

@Service
@Slf4j
public class SseEmitterService {

    private static final Map<String, SseEmitterUTF8> SSE_CACHE = new ConcurrentHashMap<>();
    private static final Map<Integer, EventSource> GPT_SSE_CLIENT_CACHE = new ConcurrentHashMap<>();


    /**
     * 创建连接sse
     *
     */
    public SseEmitter connect() {

        String clientId = String.valueOf(RequestContextUtil.getCurrentUserId());

        SseEmitterUTF8 sseEmitter = new SseEmitterUTF8(0L);
        try {
            sseEmitter.send(SseEmitter.event().comment("创建连接成功 !!!"));
        } catch (IOException e) {
            log.error("创建连接失败 , {} ", e.getMessage());
        }
        sseEmitter.onCompletion(() -> {
            log.info("connect onCompletion , {} 结束连接 ...", clientId);
            removeClient(clientId);
        });
        sseEmitter.onTimeout(() -> {
            log.info("connect onTimeout , {} 连接超时 ...", clientId);
            removeClient(clientId);
        });
        sseEmitter.onError((throwable) -> {
            log.error("connect onError , {} 连接异常 ...", clientId);
            removeClient(clientId);
        });
        SSE_CACHE.put(clientId, sseEmitter);

        log.info("当前用户总连接数 : {} ", SSE_CACHE.size());
        return sseEmitter;
    }

    public void removeClient(String clientId) {
        if (StringUtils.isBlank(clientId)) {
            clientId = String.valueOf(RequestContextUtil.getCurrentUserId());
        }
        SSE_CACHE.remove(clientId);
        GPT_SSE_CLIENT_CACHE.remove(RequestContextUtil.getCurrentUserId());
    }

    public void sendAndReceiveMessage(List<DeepSeekRequest.Message> messages) {

        Integer userId = RequestContextUtil.getCurrentUserId();

        DeepSeekRequest deepSeekRequest = new DeepSeekRequest();
        deepSeekRequest.setMessages(messages);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json"), JSON.toJSONString(deepSeekRequest));
        Request request = new Request.Builder()
                .url("https://api.deepseek.com/chat/completions")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer sk-e408e4cdf05d4ac495f333582e14c8b1")
                .build();

        EventSourceListener listener = new EventSourceListener() {
            @Override
            public void onOpen(@NotNull EventSource eventSource, @NotNull Response response) {
                super.onOpen(eventSource, response);
                log.info("后端发起大模型请求。。。userId : {}, question: {}", userId, JSON.toJSONString(messages));
            }

            @Override
            public void onEvent(@NotNull EventSource eventSource, @Nullable String id, @Nullable String type, @NotNull String data) {
                super.onEvent(eventSource, id, type, data);
                sendMessage(data, userId);
            }

            @Override
            public void onClosed(@NotNull EventSource eventSource) {
                super.onClosed(eventSource);
                log.info("消息接收完成，大模型主动断开链接。。。userId : {}, question: {}", userId, JSON.toJSONString(messages));
                GPT_SSE_CLIENT_CACHE.remove(userId);
            }

            @Override
            public void onFailure(@NotNull EventSource eventSource, @Nullable Throwable t, @Nullable Response response) {
                super.onFailure(eventSource, t, response);
                log.info("链接建立失败 : {}, question: {}", userId, JSON.toJSONString(messages));
                GPT_SSE_CLIENT_CACHE.remove(userId);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();

        EventSource.Factory factory = EventSources.createFactory(client);

        EventSource eventSource = factory.newEventSource(request, listener);
        GPT_SSE_CLIENT_CACHE.put(userId, eventSource);
    }

    /**
     * 实际发送消息
     */
    public void sendMessage(Object data, Integer userId) {
        if (userId == null || data == null || !SSE_CACHE.containsKey(String.valueOf(userId))) {
            return;
        }

        SseEmitterUTF8 sseEmitter = SSE_CACHE.get(String.valueOf(userId));

        SseEmitter.SseEventBuilder message = SseEmitter.event().name(SseEmitterConstant.VOLUME_OVERVIEW).data(data, MediaType.APPLICATION_JSON);
        boolean sendResult = sendMessageWithRetry(sseEmitter, userId, message);
        if (!sendResult) {
            SSE_CACHE.remove(String.valueOf(userId));
        }
    }


    private boolean sendMessageWithRetry(SseEmitterUTF8 sseEmitter, Integer userId, SseEmitter.SseEventBuilder message) {
        for (int i = 0; i < 3; i++) {
            try {
                sseEmitter.send(message);
            } catch (Exception ex) {
                log.error("向客户端{} 第{}次消息重推失败", userId, i + 1, ex);
                continue;
            }
            log.info("向客户端{} 第{}次消息推送成功", userId, i + 1);
            return true;
        }
        return false;
    }
}

