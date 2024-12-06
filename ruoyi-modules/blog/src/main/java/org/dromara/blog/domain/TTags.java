package org.dromara.blog.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 文章标签管理对象 t_tags
 *
 * @author LiuJinYu
 * @date 2024-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_tags")
public class TTags{

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签类型
     */
    private String type;

    /**
     * 使用次数
     */
    private Long count;

    /**
     * 状态
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
