package com.muyuan.goods.face.dto;

import java.util.Date;
import com.muyuan.common.bean.PageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName CategoryQueryRequest
 * Description
 * @author ${author}
 * @date 2022-12-16T11:54:09.147+08:00
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryQueryCommand extends PageDTO {

            /** 主键 */
            private Long id;

            /** 父类ID */
            private Long parentId;

            /** 分类名称 */
            private String name;

            /** 晨级 */
            private Long level;

            /** 创建时间 */
            private Date createTime;

            /** 修改时间 */
            private Date updateTime;

            /** 产品编码 */
            private Long code;

            /** 层级路径 */
            private String ancestors;

            /** $column.columnComment */
            private String logo;

            /** $column.columnComment */
            private Long productCount;

            /** 0-上架 1-下架 2-删除 */
            private Long status;

            /** $column.columnComment */
            private Long orderNum;

            /** 创建人ID */
            private Long createBy;

            /** 创建人 */
            private String creator;

            /** 更新人ID */
            private Long updateBy;

            /** 更新人
 */
            private String updater;

            /** 是否叶子节点 */
            private String leaf;

            /** 子节点数量 */
            private Long subCount;

}