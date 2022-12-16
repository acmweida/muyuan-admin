package com.muyuan.manager.goods.facade.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.manager.goods.dto.AttributeDTO;
import com.muyuan.manager.goods.model.Attribute;
import com.muyuan.manager.goods.service.AttributeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品分类属性Controller
 *
 * @author ${author}
 * @date 2022-06-23T15:09:05.397+08:00
 */
@RestController
@RequestMapping("/category/attribute")
@AllArgsConstructor
public class AttributeController {

    private AttributeService attributeService;


    /**
     * 新增商品分类属性
     */
    @RequirePermissions("goods:category:attribute:add")
    @Log(title = "商品分类属性", businessType = BusinessType.INSERT)
    @ApiOperation(value = "商品分类属性新增")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name", value = "属性名称", dataTypeClass = String.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "categoryCode", value = "商品分类ID", dataTypeClass = Long.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "type", value = "类型 : 多类型编时传入编码之和 1-公共 2-销售 4-关键 8-其他", dataTypeClass = Integer.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "inputType", value = "输入类型 0-手动输入 1-选择输入 ", dataTypeClass = Integer.class, paramType = "body",required = true)
            }
    )
    @PostMapping
    public Result add(@RequestBody @Validated AttributeDTO categoryAttribute)
    {
        if (GlobalConst.NOT_UNIQUE.equals(
                attributeService.checkUnique(Attribute.builder()
                        .name(categoryAttribute.getName())
                        .categoryCode(categoryAttribute.getCategoryCode())
                        .build())
        )) {
            return ResultUtil.fail(ResponseCode.ADD_EXIST.getCode(),"属性名称已存在");
        }

        attributeService.add(categoryAttribute);
        return ResultUtil.success();
    }

    /**
     * 修改商品分类属性
     */
    @RequirePermissions("goods:category:attribute:edit")
    @Log(title = "商品分类属性", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "商品分类属性更新")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "主键", dataTypeClass = Long.class, paramType = "body",required = true),
                @ApiImplicitParam(name = "name", value = "属性名称", dataTypeClass = String.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "categoryCode", value = "商品分类ID", dataTypeClass = Long.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "type", value = "类型 : 多类型编时传入编码之和 1-公共 2-销售 4-关键 8-其他", dataTypeClass = Integer.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "inputType", value = "输入类型 0-手动输入 1-选择输入 ", dataTypeClass = Integer.class, paramType = "body",required = true)
            }
    )
    public Result update(@RequestBody @Validated AttributeDTO categoryAttribute)
    {
        if (ObjectUtils.isEmpty(categoryAttribute.getId()))  {
            return ResultUtil.fail(ResponseCode.ARGUMENT_ERROR.getCode(),"Id不能为NULl");
        }
            attributeService.update(categoryAttribute);
        return ResultUtil.success();
    }

    /**
     * 删除商品分类属性
     */
    @ApiOperation(value = "商品分类属性删除")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "主键", dataTypeClass = Long[].class, paramType = "path",required = true)}
    )
    @RequirePermissions("goods:category:attribute:remove")
    @Log(title = "商品分类属性", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable String[] ids)
    {
        attributeService.delete(ids);
        return ResultUtil.success();
    }
}