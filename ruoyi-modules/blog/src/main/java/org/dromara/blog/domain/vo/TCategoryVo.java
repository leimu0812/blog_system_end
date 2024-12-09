package org.dromara.blog.domain.vo;

import org.dromara.blog.domain.TCategory;
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
 * 文章分类视图对象 t_category
 *
 * @author LiuJinYu
 * @date 2024-12-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TCategory.class)
public class TCategoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @ExcelProperty(value = "分类ID")
    private Long categoryId;

    /**
     * 分类名称
     */
    @ExcelProperty(value = "分类名称")
    private String categoryName;

    /**
     * 分类描述
     */
    @ExcelProperty(value = "分类描述")
    private String description;

    /**
     * 图标
     */
    @ExcelProperty(value = "图标")
    private String icon;

    /**
     * 文章数量
     */
    @ExcelProperty(value = "文章数量")
    private Long articleCount;

    /**
     * 显示顺序
     */
    @ExcelProperty(value = "显示顺序")
    private Long sortOrder;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_show_status")
    private String status;


}
