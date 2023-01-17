package com.anakin.example.controller;

import com.anakin.example.model.dto.UserInfoDTO;
import com.anakin.example.model.vo.UserInfoVO;
import com.anakin.example.service.UserInfoService;
import com.anakin.common.model.base.ApiDataResult;
import com.anakin.common.model.base.ApiResult;
import com.anakin.common.enums.CodeMsg;
import com.anakin.common.model.base.VOPage;
import com.anakin.common.util.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description UserInfo Controller
 * @author Generator
 * @date 2021-03-09
 */
@Slf4j
@Api(tags = "UserInfo Controller")
@RestController
@RequestMapping("userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/test")
    public ApiResult test(@RequestParam Long time) throws InterruptedException {
        Thread.sleep(time);
        return ResultUtil.response(CodeMsg.SUCCESS);
    }

    @PostMapping
    @ApiOperation(value = "add UserInfo")
    public ApiResult add(@Validated @RequestBody UserInfoDTO dto) {
        userInfoService.add(dto);
        return ResultUtil.response(CodeMsg.SUCCESS);
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "update UserInfo")
    public ApiResult update(@PathVariable Long userId, @Validated @RequestBody UserInfoDTO dto) {
        userInfoService.update(userId, dto);
        return ResultUtil.response(CodeMsg.SUCCESS);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "delete UserInfo")
    public ApiResult delete(@PathVariable Long userId) {
        userInfoService.delete(userId);
        return ResultUtil.response(CodeMsg.SUCCESS);
    }

    @GetMapping("/list")
    @ApiOperation(value = "list of UserInfo")
    public ApiDataResult<List<UserInfoVO>> list(UserInfoDTO dto) {
        return ResultUtil.response(CodeMsg.SUCCESS,userInfoService.find(dto));
    }

    @GetMapping("/page")
    @ApiOperation(value = "page of UserInfo")
    public ApiDataResult<VOPage<UserInfoVO>> page(UserInfoDTO dto, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return ResultUtil.response(CodeMsg.SUCCESS,userInfoService.find(dto, pageNum, pageSize));
    }
}
