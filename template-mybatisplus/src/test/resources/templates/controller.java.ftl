package ${package.Controller};

import com.anakin.common.model.base.ApiDataResult;
import com.anakin.common.model.base.ApiResult;
import com.anakin.common.enums.CodeMsg;
import com.anakin.common.model.base.VOPage;
import com.anakin.common.util.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import ${package.Xml}model.dto.${entity}DTO;
import ${package.Xml}model.vo.${entity}VO;
import ${package.Service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
<#if entityLombokModel>
import lombok.extern.slf4j.Slf4j;
</#if>
<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

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
 * @description ${entity} Controller
 * @author ${author}
 * @date ${date}
 */
<#if entityLombokModel>
@Slf4j
</#if>
<#if swagger2>
@Api(tags = "${entity} Controller")
</#if>
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    @Autowired
    private ${table.serviceName} ${cfg.modelNameUpperCamel}Service;

    @PostMapping
<#if swagger2>
    @ApiOperation(value = "add ${entity}")
</#if>
    public ApiResult add(@Validated @RequestBody ${entity}DTO dto) {
        ${cfg.modelNameUpperCamel}Service.add(dto);
        return ResultUtil.response(CodeMsg.SUCCESS);
    }

    @PutMapping("/{${keyName}}")
<#if swagger2>
    @ApiOperation(value = "update ${entity}")
</#if>
    public ApiResult update(@PathVariable ${keyNameType} ${keyName}, @Validated @RequestBody ${entity}DTO dto) {
        ${cfg.modelNameUpperCamel}Service.update(${keyName}, dto);
        return ResultUtil.response(CodeMsg.SUCCESS);
    }

    @GetMapping("/{${keyName}}")
<#if swagger2>
    @ApiOperation(value = "find ${entity}")
</#if>
    public ApiDataResult<${entity}VO> find(@PathVariable ${keyNameType} ${keyName}) {
        return ResultUtil.response(CodeMsg.SUCCESS,${cfg.modelNameUpperCamel}Service.find(${keyName}));
    }

    @DeleteMapping("/{${keyName}}")
<#if swagger2>
    @ApiOperation(value = "delete ${entity}")
</#if>
    public ApiResult delete(@PathVariable ${keyNameType} ${keyName}) {
        ${cfg.modelNameUpperCamel}Service.delete(${keyName});
        return ResultUtil.response(CodeMsg.SUCCESS);
    }

    @GetMapping("/list")
<#if swagger2>
    @ApiOperation(value = "list of ${entity}")
</#if>
    public ApiDataResult<List<${entity}VO>> list(${entity}DTO dto) {
        return ResultUtil.response(CodeMsg.SUCCESS,${cfg.modelNameUpperCamel}Service.find(dto));
    }

    @GetMapping("/page")
<#if swagger2>
    @ApiOperation(value = "page of ${entity}")
</#if>
    public ApiDataResult<VOPage<${entity}VO>> page(${entity}DTO dto, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return ResultUtil.response(CodeMsg.SUCCESS,${cfg.modelNameUpperCamel}Service.find(dto, pageNum, pageSize));
    }
}
</#if>
