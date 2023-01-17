package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.anakin.common.model.base.VOPage;
import ${package.Xml}model.dto.${entity}DTO;
import ${package.Xml}model.cvt.${entity}Convert;
import ${package.Xml}model.vo.${entity}VO;

import java.util.List;
import java.util.stream.Collectors;

<#--查找主键ID-->
<#assign keyName="id">
<#assign keyNameUpFirst="Id">
<#assign keyNameType="Long">
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyName="${field.propertyName}">
        <#assign keyNameUpFirst="${keyName?cap_first}">
        <#assign keyNameType="${field.propertyType}">
    </#if>
</#list>
/**
 * @description ${entity} 接口实现类
 * @author ${author}
 * @date ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public void add(${entity}DTO dto) {
        save(${entity}Convert.INSTANCE.toEntity(dto));
    }

    @Override
    public void update(${keyNameType} ${keyName}, ${entity}DTO dto) {
        update(${entity}Convert.INSTANCE.toEntity(dto),new UpdateWrapper<${entity}>().lambda().eq(${entity}::get${keyNameUpFirst},${keyName}));
    }

    @Override
    public void delete(${keyNameType} ${keyName}) {
        removeById(${keyName});
    }

    @Override
    public ${entity}VO find(${keyNameType} ${keyName}) {
        return ${entity}Convert.INSTANCE.toVO(getById(${keyName}));
    }

    @Override
    public List<${entity}VO> find(${entity}DTO dto) {
        return list(new QueryWrapper<>(${entity}Convert.INSTANCE.toEntity(dto))).stream().map(${entity}Convert.INSTANCE::toVO)
                 .collect(Collectors.toList());
    }

     @Override
     public VOPage<${entity}VO> find(${entity}DTO dto, Integer pageNum, Integer pageSize) {
         IPage<${entity}> page${entity} = page(new Page<>(pageNum, pageSize), new QueryWrapper<>(${entity}Convert.INSTANCE.toEntity(dto)));
         return new VOPage<>(pageNum,pageSize,page${entity}.getTotal(), page${entity}.getRecords().stream()
                 .map(${entity}Convert.INSTANCE::toVO).collect(Collectors.toList()));
    }
}
</#if>
