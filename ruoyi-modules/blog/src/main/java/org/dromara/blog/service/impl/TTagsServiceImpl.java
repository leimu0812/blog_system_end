package org.dromara.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dromara.blog.mapper.TSiteStatsMapper;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.blog.domain.bo.TTagsBo;
import org.dromara.blog.domain.vo.TTagsVo;
import org.dromara.blog.domain.TTags;
import org.dromara.blog.mapper.TTagsMapper;
import org.dromara.blog.service.ITTagsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 文章标签管理Service业务层处理
 *
 * @author LiuJinYu
 * @date 2024-12-06
 */
@RequiredArgsConstructor
@Service
public class TTagsServiceImpl implements ITTagsService {

    private final TTagsMapper baseMapper;
    private final TSiteStatsMapper siteStatsMapper;

    /**
     * 查询文章标签管理
     *
     * @param id 主键
     * @return 文章标签管理
     */
    @Override
    public TTagsVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询文章标签管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 文章标签管理分页列表
     */
    @Override
    public TableDataInfo<TTagsVo> queryPageList(TTagsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TTags> lqw = buildQueryWrapper(bo);
        Page<TTagsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的文章标签管理列表
     *
     * @param bo 查询条件
     * @return 文章标签管理列表
     */
    @Override
    public List<TTagsVo> queryList(TTagsBo bo) {
        LambdaQueryWrapper<TTags> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TTags> buildQueryWrapper(TTagsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TTags> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), TTags::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), TTags::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增文章标签管理
     *
     * @param bo 文章标签管理
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TTagsBo bo) {
        TTags add = MapstructUtils.convert(bo, TTags.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
            // 标签新增成功，计数，统计数量表对应的内容添加
            QueryWrapper<TTags> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("status", 1);
            siteStatsMapper.updateTagNumber(baseMapper.selectCount(queryWrapper),"标签");
        }
        return flag;
    }

    /**
     * 修改文章标签管理
     *
     * @param bo 文章标签管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TTagsBo bo) {
        TTags update = MapstructUtils.convert(bo, TTags.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TTags entity) {
        // TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除文章标签管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        Boolean flag = baseMapper.deleteByIds(ids) > 0;
        if (flag) {
            QueryWrapper<TTags> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("status", 1);
            siteStatsMapper.updateTagNumber(baseMapper.selectCount(queryWrapper),"标签");
        }
        return flag;
    }

    /**
     * 查询状态为1的标签
     *
     * @return
     */
    @Override
    public List<TTagsVo> selectStatusTags() {
        return baseMapper.getTags();
    }
}
