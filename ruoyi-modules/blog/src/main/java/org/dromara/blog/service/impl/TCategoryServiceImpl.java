package org.dromara.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dromara.blog.domain.TTags;
import org.dromara.blog.mapper.TSiteStatsMapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.blog.domain.bo.TCategoryBo;
import org.dromara.blog.domain.vo.TCategoryVo;
import org.dromara.blog.domain.TCategory;
import org.dromara.blog.mapper.TCategoryMapper;
import org.dromara.blog.service.ITCategoryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 文章分类Service业务层处理
 *
 * @author LiuJinYu
 * @date 2024-12-11
 */
@RequiredArgsConstructor
@Service
public class TCategoryServiceImpl implements ITCategoryService {

    private final TCategoryMapper baseMapper;

    private final TSiteStatsMapper siteStatsMapper;

    @Override
    public List<TCategoryVo> articlesSelect() {
        return baseMapper.getCategory();
    }

    /**
     * 查询文章分类
     *
     * @param categoryId 主键
     * @return 文章分类
     */
    @Override
    public TCategoryVo queryById(Long categoryId) {
        return baseMapper.selectVoById(categoryId);
    }

    /**
     * 分页查询文章分类列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 文章分类分页列表
     */
    @Override
    public TableDataInfo<TCategoryVo> queryPageList(TCategoryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TCategory> lqw = buildQueryWrapper(bo);
        Page<TCategoryVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的文章分类列表
     *
     * @param bo 查询条件
     * @return 文章分类列表
     */
    @Override
    public List<TCategoryVo> queryList(TCategoryBo bo) {
        LambdaQueryWrapper<TCategory> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TCategory> buildQueryWrapper(TCategoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TCategory> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getCategoryName()), TCategory::getCategoryName, bo.getCategoryName());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), TCategory::getDescription, bo.getDescription());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), TCategory::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增文章分类
     *
     * @param bo 文章分类
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TCategoryBo bo) {
        TCategory add = MapstructUtils.convert(bo, TCategory.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCategoryId(add.getCategoryId());
            // 分类新增成功，计数，统计数量表对应的内容添加
            QueryWrapper<TCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("status", 1);
            siteStatsMapper.updateTagNumber(baseMapper.selectCount(queryWrapper), "分类");

        }
        return flag;
    }

    /**
     * 修改文章分类
     *
     * @param bo 文章分类
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TCategoryBo bo) {
        TCategory update = MapstructUtils.convert(bo, TCategory.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TCategory entity) {
        // TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除文章分类信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        boolean flag = baseMapper.deleteByIds(ids) > 0;
        if (flag) {
            QueryWrapper<TCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("status", 1);
            siteStatsMapper.updateTagNumber(baseMapper.selectCount(queryWrapper), "分类");
        }
        return flag;
    }
}
