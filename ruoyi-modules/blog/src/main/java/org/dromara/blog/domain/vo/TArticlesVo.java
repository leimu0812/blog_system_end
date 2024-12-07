package org.dromara.blog.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.blog.domain.TArticles;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 文章管理视图对象 t_articles
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TArticles.class)
public class TArticlesVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 文章标题
     */
    @ExcelProperty(value = "文章标题")
    private String title;

    /**
     * 文章摘要
     */
    @ExcelProperty(value = "文章摘要")
    private String summary;

    /**
     * 文章内容
     */
    @ExcelProperty(value = "文章内容")
    private String content;

    /**
     * 封面图片
     */
    @ExcelProperty(value = "封面图片")
    private String coverImg;

    /**
     * 封面图片Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "coverImg")
    private String coverImgUrl;
    /**
     * 分类
     */
    @ExcelProperty(value = "分类")
    private String category;

    /**
     * 浏览量
     */
    @ExcelProperty(value = "浏览量")
    private Long views;

    /**
     * 是否置顶
     */
    @ExcelProperty(value = "是否置顶", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_articles_istop")
    private Long isTop;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_articles_status")
    private String status;

    /**
     * 发布时间
     */
    @ExcelProperty(value = "发布时间")
    private Date publishTime;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updatedAt;


}
