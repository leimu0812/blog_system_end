package org.dromara.blog.service;

import org.dromara.blog.domain.vo.TSocialLinksVo;
import org.dromara.blog.domain.bo.TSocialLinksBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 社交链接管理Service接口
 *
 * @author Lion Li
 * @date 2024-12-06
 */
public interface ITSocialLinksService {

    /**
     * 查询社交链接管理
     *
     * @param id 主键
     * @return 社交链接管理
     */
    TSocialLinksVo queryById(Long id);

    /**
     * 分页查询社交链接管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 社交链接管理分页列表
     */
    TableDataInfo<TSocialLinksVo> queryPageList(TSocialLinksBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的社交链接管理列表
     *
     * @param bo 查询条件
     * @return 社交链接管理列表
     */
    List<TSocialLinksVo> queryList(TSocialLinksBo bo);

    /**
     * 新增社交链接管理
     *
     * @param bo 社交链接管理
     * @return 是否新增成功
     */
    Boolean insertByBo(TSocialLinksBo bo);

    /**
     * 修改社交链接管理
     *
     * @param bo 社交链接管理
     * @return 是否修改成功
     */
    Boolean updateByBo(TSocialLinksBo bo);

    /**
     * 校验并批量删除社交链接管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
