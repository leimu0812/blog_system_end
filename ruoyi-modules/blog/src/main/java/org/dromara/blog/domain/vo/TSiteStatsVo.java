package org.dromara.blog.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.blog.domain.TSiteStats;
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
 * 网站统计视图对象 t_site_stats
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TSiteStats.class)
public class TSiteStatsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 统计项名称
     */
    @ExcelProperty(value = "统计项名称", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_count_number")
    private String name;

    /**
     * 统计值
     */
    @ExcelProperty(value = "统计值")
    private Long value;

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
