package org.dromara.blog.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.blog.domain.TTags;
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
 * 文章标签管理视图对象 t_tags
 *
 * @author LiuJinYu
 * @date 2024-12-06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TTags.class)
public class TTagsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 标签名称
     */
    @ExcelProperty(value = "标签名称")
    private String name;

    /**
     * 标签类型
     */
    @ExcelProperty(value = "标签类型")
    private String type;

    /**
     * 使用次数
     */
    @ExcelProperty(value = "使用次数")
    private Long count;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
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
