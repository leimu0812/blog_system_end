package org.dromara.blog.domain.bo;


import org.dromara.blog.domain.TUserInfo;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 信息管理业务对象 t_user_info
 *
 * @author Lion Li
 * @date 2024-12-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TUserInfo.class, reverseConvertGenerate = false)
public class TUserInfoBo extends BaseEntity {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, EditGroup.class})
    private String username;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String nickname;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空", groups = {AddGroup.class, EditGroup.class})
    private String avatar;

    /**
     * 座右铭
     */
    private String motto;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private String status;


}
