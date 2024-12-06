package org.dromara.blog.domain.bo;

import org.dromara.blog.domain.TSocialLinks;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 社交链接管理业务对象 t_social_links
 *
 * @author Lion Li
 * @date 2024-12-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TSocialLinks.class, reverseConvertGenerate = false)
public class TSocialLinksBo extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 图标
     */
    @NotBlank(message = "图标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String icon;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * 链接地址
     */
    @NotBlank(message = "链接地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String url;

    /**
     * 排序顺序
     */
    @NotNull(message = "排序顺序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sortOrder;

    /**
     * 状态：0-禁用 1-启用
     */
    @NotBlank(message = "状态：0-禁用 1-启用不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
