package org.dromara.blog.domain.bo;

import org.dromara.blog.domain.TSiteStats;
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
 * 网站统计业务对象 t_site_stats
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TSiteStats.class, reverseConvertGenerate = false)
public class TSiteStatsBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 统计项名称
     */
    @NotBlank(message = "统计项名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 统计值
     */
    private Long value;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
