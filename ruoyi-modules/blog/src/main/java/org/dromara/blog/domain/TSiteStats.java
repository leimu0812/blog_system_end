package org.dromara.blog.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 网站统计对象 t_site_stats
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_site_stats")
public class TSiteStats {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 统计项名称
     */
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
