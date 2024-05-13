package com.life.bank.palm.web.controller.comunity;

import com.life.bank.palm.common.result.CommonResponse;
import com.life.bank.palm.common.result.PageResult;
import com.life.bank.palm.common.utils.CheckUtil;
import com.life.bank.palm.service.community.CommunityPostService;
import com.life.bank.palm.web.controller.comunity.convertor.CommunityPostVoConvertor;
import com.life.bank.palm.web.controller.comunity.vo.CommunityPostVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：麻薯哥搞offer
 * @description ：转账部分核心接口
 * @date ：2024/04/14 00:09
 */
@RestController
@RequestMapping("community")
@Api(tags = "社区核心接口汇总")
public class CommunityController {

    @Autowired
    private CommunityPostService communityPostService;

    @PostMapping("post/create-one")
    @ApiOperation("新创建一个篇帖子")
    public CommonResponse<Void> createOne(@RequestBody @ApiParam("输入社区实体") CommunityPostVo communityPostVo) {
        CheckUtil.Biz.INSTANCE
                .noNull(communityPostVo, "入参不合法")
                .strNotBlank(communityPostVo.getTitle(), "标题不能为空")
                .strNotBlank(communityPostVo.getRichContent(), "内容不能为空");
        communityPostService.createOne(CommunityPostVoConvertor.INSTANCE.toVO(communityPostVo));
        return CommonResponse.buildSuccess();
    }


    @GetMapping("post/index-list")
    @ApiOperation("查询帖子的list列表")
    public CommonResponse<PageResult> indexList(@RequestParam(defaultValue = "1") @ApiParam("页码") Integer pageNo,
                                                @RequestParam(defaultValue = "10") @ApiParam("每页数") Integer pageSize) {
        PageResult index = communityPostService.index(pageNo, pageSize);
        index.setResult(CommunityPostVoConvertor.INSTANCE.toRecords(index.getResult()));
        return CommonResponse.buildSuccess(index);
    }

    @GetMapping("post/hot-list")
    @ApiOperation("社区热门帖子")
    public CommonResponse<PageResult> hotList() {
        PageResult index = communityPostService.index(1, 10);
        index.setResult(CommunityPostVoConvertor.INSTANCE.toRecords(index.getResult()));
        return CommonResponse.buildSuccess(index);
    }

}
