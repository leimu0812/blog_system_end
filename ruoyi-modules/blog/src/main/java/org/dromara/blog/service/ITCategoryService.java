package org.dromara.blog.service;

import org.dromara.blog.domain.vo.TCategoryVo;
import org.dromara.blog.domain.bo.TCategoryBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 文章分类Service接口
 *
 * @author LiuJinYu
 * @date 2024-12-09
 */
public interface ITCategoryService {

    /**
     * 查询文章分类
     *
     * @param categoryId 主键
     * @return 文章分类
     */
    TCategoryVo queryById(Long categoryId);

    /**
     * 分页查询文章分类列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 文章分类分页列表
     */
    TableDataInfo<TCategoryVo> queryPageList(TCategoryBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的文章分类列表
     *
     * @param bo 查询条件
     * @return 文章分类列表
     */
    List<TCategoryVo> queryList(TCategoryBo bo);

    /**
     * 新增文章分类
     *
     * @param bo 文章分类
     * @return 是否新增成功
     */
    Boolean insertByBo(TCategoryBo bo);

    /**
     * 修改文章分类
     *
     * @param bo 文章分类
     * @return 是否修改成功
     */
    Boolean updateByBo(TCategoryBo bo);

    /**
     * 校验并批量删除文章分类信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
