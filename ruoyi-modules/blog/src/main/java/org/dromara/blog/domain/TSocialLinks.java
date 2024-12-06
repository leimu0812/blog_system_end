package org.dromara.blog.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 社交链接管理对象 t_social_links
 *
 * @author LiuJinYu
 * @date 2024-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_social_links")
public class TSocialLinks{

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 图标
     */
    private String icon;

    /**
     * 标题
     */
    private String title;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 排序顺序
     */
    private Long sortOrder;

    /**
     * 状态：0-禁用 1-启用
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
