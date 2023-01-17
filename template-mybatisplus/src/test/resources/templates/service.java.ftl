package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.anakin.common.model.base.VOPage;
import ${package.Xml}model.dto.${entity}DTO;
import ${package.Xml}model.vo.${entity}VO;
import java.util.List;

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
 * @description ${entity} 接口类
 * @author ${author}
 * @date ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${entity}Service extends ${superServiceClass}<${entity}> {

    /**
    * add ${entity}
    * @param dto data transfer object
    */
    void add(${entity}DTO dto);

    /**
    * update ${entity}
    * @param ${keyName} key
    * @param dto data transfer object
    */
    void update(${keyNameType} ${keyName}, ${entity}DTO dto);

    /**
    * delete ${entity}
    * @param ${keyName} key
    */
    void delete(${keyNameType} ${keyName});

    /**
    * find ${entity}
    * @param ${keyName} key
    * @return ${entity}
    */
    ${entity}VO find(${keyNameType} ${keyName});

    /**
    * find ${entity} list
    * @param dto data transfer object
    * @return list of ${entity}
    */
    List<${entity}VO> find(${entity}DTO dto);

     /**
     * find ${entity} page
     * @param dto data transfer object
     * @param pageNum page number
     * @param pageSize page size
     * @return page of ${entity}
     */
     VOPage<${entity}VO> find(${entity}DTO dto, Integer pageNum, Integer pageSize);
}
</#if>
