package org.dromara.blog.domain.bo;

import org.dromara.blog.domain.TCategory;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 文章分类业务对象 t_category
 *
 * @author LiuJinYu
 * @date 2024-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TCategory.class, reverseConvertGenerate = false)
public class TCategoryBo extends BaseEntity {

    /**
     * 分类ID
     */
    @NotNull(message = "分类ID不能为空", groups = { EditGroup.class })
    private Long categoryId;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String categoryName;

    /**
     * 分类描述
     */
    @NotBlank(message = "分类描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 图标
     */
    @NotBlank(message = "图标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String icon;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sortOrder;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
