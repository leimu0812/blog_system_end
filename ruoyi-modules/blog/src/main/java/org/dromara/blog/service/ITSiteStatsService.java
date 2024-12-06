package org.dromara.blog.service;

import org.dromara.blog.domain.vo.TSiteStatsVo;
import org.dromara.blog.domain.bo.TSiteStatsBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 网站统计Service接口
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
public interface ITSiteStatsService {

    /**
     * 查询网站统计
     *
     * @param id 主键
     * @return 网站统计
     */
    TSiteStatsVo queryById(Long id);

    /**
     * 分页查询网站统计列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 网站统计分页列表
     */
    TableDataInfo<TSiteStatsVo> queryPageList(TSiteStatsBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的网站统计列表
     *
     * @param bo 查询条件
     * @return 网站统计列表
     */
    List<TSiteStatsVo> queryList(TSiteStatsBo bo);

    /**
     * 新增网站统计
     *
     * @param bo 网站统计
     * @return 是否新增成功
     */
    Boolean insertByBo(TSiteStatsBo bo);

    /**
     * 修改网站统计
     *
     * @param bo 网站统计
     * @return 是否修改成功
     */
    Boolean updateByBo(TSiteStatsBo bo);

    /**
     * 校验并批量删除网站统计信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
