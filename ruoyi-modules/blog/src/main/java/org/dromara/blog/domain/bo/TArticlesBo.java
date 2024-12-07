package org.dromara.blog.domain.bo;

import org.dromara.blog.domain.TArticles;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import org.dromara.common.translation.annotation.Translation;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.common.translation.constant.TransConstant;

/**
 * 文章管理业务对象 t_articles
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TArticles.class, reverseConvertGenerate = false)
public class TArticlesBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空", groups = { AddGroup.class, EditGroup.class })
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

    private String[] tags;


}
