package org.dromara.blog.service;

import org.dromara.blog.domain.vo.TArticlesVo;
import org.dromara.blog.domain.bo.TArticlesBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 文章管理Service接口
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
public interface ITArticlesService {

    /**
     * 查询文章管理
     *
     * @param id 主键
     * @return 文章管理
     */
    TArticlesVo queryById(Long id);

    /**
     * 分页查询文章管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 文章管理分页列表
     */
    TableDataInfo<TArticlesVo> queryPageList(TArticlesBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的文章管理列表
     *
     * @param bo 查询条件
     * @return 文章管理列表
     */
    List<TArticlesVo> queryList(TArticlesBo bo);

    /**
     * 新增文章管理
     *
     * @param bo 文章管理
     * @return 是否新增成功
     */
    Boolean insertByBo(TArticlesBo bo);

    /**
     * 修改文章管理
     *
     * @param bo 文章管理
     * @return 是否修改成功
     */
    Boolean updateByBo(TArticlesBo bo);

    /**
     * 校验并批量删除文章管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
