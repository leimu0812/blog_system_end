package org.dromara.blog.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 文章标签关联对象 t_article_tags
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_article_tags")
public class TArticleTags {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    // @TableId(value = "article_id")
    private Long articleId;

    /**
     * 标签ID
     */
    // @TableId(value = "tag_id")
    private Long tagId;

    /**
     * 创建时间
     */
    private Date createdAt;


}
