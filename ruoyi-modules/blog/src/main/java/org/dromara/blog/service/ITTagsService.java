package org.dromara.blog.service;

import org.dromara.blog.domain.vo.TTagsVo;
import org.dromara.blog.domain.bo.TTagsBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 文章标签管理Service接口
 *
 * @author LiuJinYu
 * @date 2024-12-06
 */
public interface ITTagsService {

    /**
     * 查询文章标签管理
     *
     * @param id 主键
     * @return 文章标签管理
     */
    TTagsVo queryById(Long id);

    /**
     * 分页查询文章标签管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 文章标签管理分页列表
     */
    TableDataInfo<TTagsVo> queryPageList(TTagsBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的文章标签管理列表
     *
     * @param bo 查询条件
     * @return 文章标签管理列表
     */
    List<TTagsVo> queryList(TTagsBo bo);

    /**
     * 新增文章标签管理
     *
     * @param bo 文章标签管理
     * @return 是否新增成功
     */
    Boolean insertByBo(TTagsBo bo);

    /**
     * 修改文章标签管理
     *
     * @param bo 文章标签管理
     * @return 是否修改成功
     */
    Boolean updateByBo(TTagsBo bo);

    /**
     * 校验并批量删除文章标签管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 查询状态标签
     *
     * @return
     */
    List<TTagsVo> selectStatusTags();
}
