package com.life.bank.palm.web.controller.sse;

import com.life.bank.palm.common.result.CommonResponse;
import com.life.bank.palm.service.sse.SseEmitterService;
import com.life.bank.palm.web.anotations.LoginRequired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@Api(tags = "ai智能问答接口")
@RequestMapping("/ai/conversation")
public class SseEmitterController {

    @Autowired
    private SseEmitterService sseEmitterService;

    /**
     * 创建SSE连接
     *
     */
    @LoginRequired
    @PostMapping("connect")
    @ApiOperation("建立链接")
    public CommonResponse<SseEmitter> connect() {
        SseEmitter connect = sseEmitterService.connect();
        return CommonResponse.buildSuccess(connect);
    }

    @LoginRequired
    @PostMapping("un-connect")
    @ApiOperation("断开链接")
    public CommonResponse<Boolean> unConnect() {
        sseEmitterService.removeClient(null);
        return CommonResponse.buildSuccess(Boolean.TRUE);
    }

    @PostMapping("send-question")
    @ApiOperation("发送问题")
    @LoginRequired
    public CommonResponse<Boolean> sendMessage(@RequestParam(value = "question") @ApiParam("问题") String question) {
        // todo 调用ai api。发送消息，异步，然后慢慢接受消息推送到端上

        return CommonResponse.buildSuccess(Boolean.TRUE);
    }



}

