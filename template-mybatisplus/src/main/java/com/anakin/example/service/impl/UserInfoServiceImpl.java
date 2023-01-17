package com.anakin.example.service.impl;

import com.anakin.example.entity.UserInfo;
import com.anakin.example.model.dto.UserInfoDTO;
import com.anakin.example.service.UserInfoService;
import com.anakin.common.enums.CodeMsg;
import com.anakin.common.exception.CommonException;
import com.anakin.example.repository.UserInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.anakin.common.model.base.VOPage;
import com.anakin.example.model.cvt.UserInfoConvert;
import com.anakin.example.model.vo.UserInfoVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description UserInfo 接口实现类
 * @author Generator
 * @date 2021-03-09
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public void add(UserInfoDTO dto) {
        save(UserInfoConvert.INSTANCE.toEntity(dto));
    }

    @Override
    public void update(Long userId, UserInfoDTO dto) {
        update(UserInfoConvert.INSTANCE.toEntity(dto),new UpdateWrapper<UserInfo>().lambda().eq(UserInfo::getUserId,userId));
    }

    @Override
    public void delete(Long userId) {
        removeById(userId);
    }

    @Override
    public UserInfoVO find(Long userId) {
        return UserInfoConvert.INSTANCE.toVO(getById(userId));
    }

    @Override
    public List<UserInfoVO> find(UserInfoDTO dto) {
        return list(new QueryWrapper<>(UserInfoConvert.INSTANCE.toEntity(dto))).stream().map(UserInfoConvert.INSTANCE::toVO)
                 .collect(Collectors.toList());
    }

     @Override
     public VOPage<UserInfoVO> find(UserInfoDTO dto, Integer pageNum, Integer pageSize) {
         IPage<UserInfo> pageUserInfo = page(new Page<>(pageNum, pageSize), new QueryWrapper<>(UserInfoConvert.INSTANCE.toEntity(dto)));
         return new VOPage<>(pageNum,pageSize,pageUserInfo.getTotal(), pageUserInfo.getRecords().stream()
                 .map(UserInfoConvert.INSTANCE::toVO).collect(Collectors.toList()));
    }
}
