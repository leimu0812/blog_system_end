package org.dromara.blog.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.blog.domain.TSocialLinks;
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
 * 社交链接管理视图对象 t_social_links
 *
 * @author Lion Li
 * @date 2024-12-06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TSocialLinks.class)
public class TSocialLinksVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 图标
     */
    @ExcelProperty(value = "图标")
    private String icon;

    /**
     * 标题
     */
    @ExcelProperty(value = "标题")
    private String title;

    /**
     * 链接地址
     */
    @ExcelProperty(value = "链接地址")
    private String url;

    /**
     * 排序顺序
     */
    @ExcelProperty(value = "排序顺序")
    private Long sortOrder;

    /**
     * 状态：0-禁用 1-启用
     */
    @ExcelProperty(value = "状态：0-禁用 1-启用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_show_status")
    private String status;

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
