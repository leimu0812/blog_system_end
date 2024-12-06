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
import org.dromara.blog.domain.bo.TSocialLinksBo;
import org.dromara.blog.domain.vo.TSocialLinksVo;
import org.dromara.blog.domain.TSocialLinks;
import org.dromara.blog.mapper.TSocialLinksMapper;
import org.dromara.blog.service.ITSocialLinksService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 社交链接管理Service业务层处理
 *
 * @author Lion Li
 * @date 2024-12-06
 */
@RequiredArgsConstructor
@Service
public class TSocialLinksServiceImpl implements ITSocialLinksService {

    private final TSocialLinksMapper baseMapper;

    /**
     * 查询社交链接管理
     *
     * @param id 主键
     * @return 社交链接管理
     */
    @Override
    public TSocialLinksVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询社交链接管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 社交链接管理分页列表
     */
    @Override
    public TableDataInfo<TSocialLinksVo> queryPageList(TSocialLinksBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TSocialLinks> lqw = buildQueryWrapper(bo);
        Page<TSocialLinksVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的社交链接管理列表
     *
     * @param bo 查询条件
     * @return 社交链接管理列表
     */
    @Override
    public List<TSocialLinksVo> queryList(TSocialLinksBo bo) {
        LambdaQueryWrapper<TSocialLinks> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TSocialLinks> buildQueryWrapper(TSocialLinksBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TSocialLinks> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getTitle()), TSocialLinks::getTitle, bo.getTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), TSocialLinks::getUrl, bo.getUrl());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), TSocialLinks::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增社交链接管理
     *
     * @param bo 社交链接管理
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TSocialLinksBo bo) {
        TSocialLinks add = MapstructUtils.convert(bo, TSocialLinks.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改社交链接管理
     *
     * @param bo 社交链接管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TSocialLinksBo bo) {
        TSocialLinks update = MapstructUtils.convert(bo, TSocialLinks.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TSocialLinks entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除社交链接管理信息
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
