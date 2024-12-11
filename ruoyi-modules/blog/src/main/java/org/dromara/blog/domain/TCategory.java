package org.dromara.blog.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 文章分类对象 t_category
 *
 * @author LiuJinYu
 * @date 2024-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_category")
public class TCategory {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId(value = "category_id")
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 图标
     */
    private String icon;

    /**
     * 文章数量
     */
    private Long articleCount;

    /**
     * 显示顺序
     */
    private Long sortOrder;

    /**
     * 状态（0停用 1正常）
     */
    private String status;


}
