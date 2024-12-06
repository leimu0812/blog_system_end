package org.dromara.blog.domain.vo;

import org.dromara.blog.domain.TUserInfo;
import org.dromara.common.translation.annotation.Translation;

import java.util.Date;

import org.dromara.common.translation.constant.TransConstant;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 信息管理视图对象 t_user_info
 *
 * @author LiuJinYu
 * @date 2024-12-05
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TUserInfo.class)
public class TUserInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名")
    private String username;

    /**
     * 昵称
     */
    @ExcelProperty(value = "昵称")
    private String nickname;

    /**
     * 头像
     */
    @ExcelProperty(value = "头像")
    private String avatar;

    /**
     * 头像Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "avatar")
    private String avatarUrl;
    /**
     * 座右铭
     */
    @ExcelProperty(value = "座右铭")
    private String motto;

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
