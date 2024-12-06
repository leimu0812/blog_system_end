package org.dromara.blog.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.blog.domain.bo.TArticlesBo;
import org.dromara.blog.domain.vo.TArticlesVo;
import org.dromara.blog.domain.TArticles;
import org.dromara.blog.mapper.TArticlesMapper;
import org.dromara.blog.service.ITArticlesService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 文章管理Service业务层处理
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
@RequiredArgsConstructor
@Service
public class TArticlesServiceImpl implements ITArticlesService {

    private final TArticlesMapper baseMapper;

    /**
     * 查询文章管理
     *
     * @param id 主键
     * @return 文章管理
     */
    @Override
    public TArticlesVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询文章管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 文章管理分页列表
     */
    @Override
    public TableDataInfo<TArticlesVo> queryPageList(TArticlesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TArticles> lqw = buildQueryWrapper(bo);
        Page<TArticlesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的文章管理列表
     *
     * @param bo 查询条件
     * @return 文章管理列表
     */
    @Override
    public List<TArticlesVo> queryList(TArticlesBo bo) {
        LambdaQueryWrapper<TArticles> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TArticles> buildQueryWrapper(TArticlesBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TArticles> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getTitle()), TArticles::getTitle, bo.getTitle());
        lqw.like(StringUtils.isNotBlank(bo.getSummary()), TArticles::getSummary, bo.getSummary());
        lqw.eq(StringUtils.isNotBlank(bo.getCategory()), TArticles::getCategory, bo.getCategory());
        lqw.eq(bo.getIsTop() != null, TArticles::getIsTop, bo.getIsTop());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), TArticles::getStatus, bo.getStatus());
        lqw.eq(bo.getPublishTime() != null, TArticles::getPublishTime, bo.getPublishTime());
        lqw.eq(bo.getCreatedAt() != null, TArticles::getCreatedAt, bo.getCreatedAt());
        return lqw;
    }

    /**
     * 新增文章管理
     *
     * @param bo 文章管理
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TArticlesBo bo) {
        TArticles add = MapstructUtils.convert(bo, TArticles.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改文章管理
     *
     * @param bo 文章管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TArticlesBo bo) {
        TArticles update = MapstructUtils.convert(bo, TArticles.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TArticles entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除文章管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
