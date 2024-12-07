package org.dromara.blog.domain.bo;

import org.dromara.blog.domain.TTags;
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
 * 文章标签管理业务对象 t_tags
 *
 * @author LiuJinYu
 * @date 2024-12-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TTags.class, reverseConvertGenerate = false)
public class TTagsBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String name;

    /**
     * 标签类型
     */
    @NotBlank(message = "标签类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String type;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private String status;


}
