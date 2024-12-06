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
import org.dromara.blog.domain.bo.TSiteStatsBo;
import org.dromara.blog.domain.vo.TSiteStatsVo;
import org.dromara.blog.domain.TSiteStats;
import org.dromara.blog.mapper.TSiteStatsMapper;
import org.dromara.blog.service.ITSiteStatsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 网站统计Service业务层处理
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
@RequiredArgsConstructor
@Service
public class TSiteStatsServiceImpl implements ITSiteStatsService {

    private final TSiteStatsMapper baseMapper;

    /**
     * 查询网站统计
     *
     * @param id 主键
     * @return 网站统计
     */
    @Override
    public TSiteStatsVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询网站统计列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 网站统计分页列表
     */
    @Override
    public TableDataInfo<TSiteStatsVo> queryPageList(TSiteStatsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TSiteStats> lqw = buildQueryWrapper(bo);
        Page<TSiteStatsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的网站统计列表
     *
     * @param bo 查询条件
     * @return 网站统计列表
     */
    @Override
    public List<TSiteStatsVo> queryList(TSiteStatsBo bo) {
        LambdaQueryWrapper<TSiteStats> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TSiteStats> buildQueryWrapper(TSiteStatsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TSiteStats> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), TSiteStats::getName, bo.getName());
        lqw.eq(bo.getValue() != null, TSiteStats::getValue, bo.getValue());
        lqw.eq(bo.getCreatedAt() != null, TSiteStats::getCreatedAt, bo.getCreatedAt());
        lqw.eq(bo.getUpdatedAt() != null, TSiteStats::getUpdatedAt, bo.getUpdatedAt());
        return lqw;
    }

    /**
     * 新增网站统计
     *
     * @param bo 网站统计
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TSiteStatsBo bo) {
        TSiteStats add = MapstructUtils.convert(bo, TSiteStats.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改网站统计
     *
     * @param bo 网站统计
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TSiteStatsBo bo) {
        TSiteStats update = MapstructUtils.convert(bo, TSiteStats.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TSiteStats entity) {
        // TODO 做一些数据校验,如唯一约束
        // 查找是否创建过
        if (StringUtils.isNotBlank(entity.getName())) {
            TSiteStats siteStats = baseMapper.selectOne(Wrappers.lambdaQuery(TSiteStats.class)
                .eq(TSiteStats::getName, entity.getName()));
            if (siteStats != null && !siteStats.getId().equals(entity.getId())) {
                throw new RuntimeException("网站统计名称已存在");
            }
        }
    }

    /**
     * 校验并批量删除网站统计信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
