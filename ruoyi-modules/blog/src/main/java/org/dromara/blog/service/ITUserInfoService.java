package org.dromara.blog.service;


import org.dromara.blog.domain.bo.TUserInfoBo;
import org.dromara.blog.domain.vo.TUserInfoVo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 信息管理Service接口
 *
 * @author Lion Li
 * @date 2024-12-05
 */
public interface ITUserInfoService {

    /**
     * 查询信息管理
     *
     * @param id 主键
     * @return 信息管理
     */
    TUserInfoVo queryById(Long id);

    /**
     * 分页查询信息管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 信息管理分页列表
     */
    TableDataInfo<TUserInfoVo> queryPageList(TUserInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的信息管理列表
     *
     * @param bo 查询条件
     * @return 信息管理列表
     */
    List<TUserInfoVo> queryList(TUserInfoBo bo);

    /**
     * 新增信息管理
     *
     * @param bo 信息管理
     * @return 是否新增成功
     */
    Boolean insertByBo(TUserInfoBo bo);

    /**
     * 修改信息管理
     *
     * @param bo 信息管理
     * @return 是否修改成功
     */
    Boolean updateByBo(TUserInfoBo bo);

    /**
     * 校验并批量删除信息管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
