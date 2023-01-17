package ${package.Xml}model.cvt;

import ${package.Entity}.${entity};
import ${package.Xml}model.dto.${entity}DTO;
import ${package.Xml}model.vo.${entity}VO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
/**
 * @description ${entity} 对象转换类
 * @author ${author}
 * @date ${date}
 */
@Mapper
public interface ${entity}Convert {

    ${entity}Convert INSTANCE = Mappers.getMapper(${entity}Convert.class);

    /**
    * convert to vo
    * @param entity
    * @return vo
    */
    ${entity}VO toVO(${entity} entity);

    /**
    * convert to entity
    * @param dto
    * @return entity
    */
    ${entity} toEntity(${entity}DTO dto);
}
