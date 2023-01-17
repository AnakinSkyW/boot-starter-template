package com.anakin.example.model.cvt;

import com.anakin.example.entity.UserInfo;
import com.anakin.example.model.dto.UserInfoDTO;
import com.anakin.example.model.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
/**
 * @description UserInfo 对象转换类
 * @author Generator
 * @date 2021-03-09
 */
@Mapper
public interface UserInfoConvert {

    UserInfoConvert INSTANCE = Mappers.getMapper(UserInfoConvert.class);

    /**
    * convert to vo
    * @param entity
    * @return vo
    */
    UserInfoVO toVO(UserInfo entity);

    /**
    * convert to entity
    * @param dto
    * @return entity
    */
    UserInfo toEntity(UserInfoDTO dto);
}
