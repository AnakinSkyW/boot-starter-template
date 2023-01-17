package com.anakin.example.service;

import com.anakin.example.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.anakin.common.model.base.VOPage;
import com.anakin.example.model.dto.UserInfoDTO;
import com.anakin.example.model.vo.UserInfoVO;
import java.util.List;


/**
 * @description UserInfo 接口类
 * @author Generator
 * @date 2021-03-09
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
    * add UserInfo
    * @param dto data transfer object
    */
    void add(UserInfoDTO dto);

    /**
    * update UserInfo
    * @param userId key
    * @param dto data transfer object
    */
    void update(Long userId, UserInfoDTO dto);

    /**
    * delete UserInfo
    * @param userId key
    */
    void delete(Long userId);

    /**
    * find UserInfo
    * @param userId key
    * @return UserInfo
    */
    UserInfoVO find(Long userId);

    /**
    * find UserInfo list
    * @param dto data transfer object
    * @return list of UserInfo
    */
    List<UserInfoVO> find(UserInfoDTO dto);

     /**
     * find UserInfo page
     * @param dto data transfer object
     * @param pageNum page number
     * @param pageSize page size
     * @return page of UserInfo
     */
     VOPage<UserInfoVO> find(UserInfoDTO dto, Integer pageNum, Integer pageSize);
}
