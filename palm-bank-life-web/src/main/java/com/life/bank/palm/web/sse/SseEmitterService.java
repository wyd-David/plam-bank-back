package com.life.bank.palm.web.sse;

import com.life.bank.palm.service.user.utils.RequestContextUtil;
import com.life.bank.palm.web.sse.bo.SseEmitterUTF8;
import com.life.bank.palm.web.sse.constant.SseEmitterConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：麻薯哥搞offer
 * @description ：sse链接维护
 * @date ：2024/05/12 17:13
 */

@Service
@Slf4j
public class SseEmitterService {

    private static final Map<String, SseEmitterUTF8> SSE_CACHE = new ConcurrentHashMap<>();


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
            log.info("向客户端{} 第{}次消息重推成功", userId, i + 1);
            return true;
        }
        return false;
    }
}

