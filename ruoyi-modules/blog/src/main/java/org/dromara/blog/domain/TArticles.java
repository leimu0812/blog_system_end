package org.dromara.blog.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.translation.annotation.Translation;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.common.translation.constant.TransConstant;

import java.io.Serial;

/**
 * 文章管理对象 t_articles
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_articles")
public class TArticles{

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 封面图片
     */
    private String coverImg;

    /**
     * 分类
     */
    private String category;

    /**
     * 浏览量
     */
    private Long views;

    /**
     * 是否置顶
     */
    private Long isTop;

    /**
     * 状态
     */
    private String status;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
