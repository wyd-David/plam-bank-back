package com.life.bank.palm.web.controller.comunity;

import com.life.bank.palm.common.result.CommonResponse;
import com.life.bank.palm.common.result.PageResult;
import com.life.bank.palm.common.utils.CheckUtil;
import com.life.bank.palm.dao.community.pojo.BankCommentPO;
import com.life.bank.palm.dao.user.pojo.UserPO;
import com.life.bank.palm.service.community.BankCommentService;
import com.life.bank.palm.service.community.CommunityPostService;
import com.life.bank.palm.service.user.utils.RequestContextUtil;
import com.life.bank.palm.web.controller.comunity.convertor.BankCommentVoConvertor;
import com.life.bank.palm.web.controller.comunity.convertor.CommunityPostVoConvertor;
import com.life.bank.palm.web.controller.comunity.vo.BankCommentVo;
import com.life.bank.palm.web.controller.comunity.vo.CommunityPostVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private BankCommentService bankCommentService;


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

    @PostMapping("comment/insert")
    @ApiOperation("评论帖子或根评论")
    public CommonResponse<Void> comment(@RequestBody @ApiParam("评论实体") BankCommentVo commentVo){
        CheckUtil.Biz.INSTANCE
                .noNull(commentVo, "入参不合法")
                .strNotBlank(commentVo.getCommentContent(), "内容不能为空");
        bankCommentService.insert(BankCommentVoConvertor.INSTANCE.toPO(commentVo));
        return CommonResponse.buildSuccess();
    }

    @GetMapping("comment/getList")
    @ResponseBody
    @ApiOperation("显示评论")
    public CommonResponse<List<BankCommentVo>> getComment(@RequestParam(defaultValue = "3") @ApiParam("显示子评论数") Integer size){
        List<BankCommentPO> bankCommentPos = bankCommentService.getComment();
        List<BankCommentVo> bankCommentVos = new ArrayList<BankCommentVo>();
        for(BankCommentPO commentPO : bankCommentPos){
            BankCommentVo bankCommentVo = BankCommentVoConvertor.INSTANCE.toVO(commentPO);
            List<BankCommentPO> subList = commentPO.getSubList();
            List<BankCommentVo> subListVos = BankCommentVoConvertor.INSTANCE.toRecords(subList);
            bankCommentVo.setSubList(subListVos);
            bankCommentVos.add(bankCommentVo);
        }

        return CommonResponse.buildSuccess(bankCommentVos);
    }


}
