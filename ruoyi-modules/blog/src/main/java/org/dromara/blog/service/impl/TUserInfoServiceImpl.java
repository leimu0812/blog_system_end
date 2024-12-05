package org.dromara.blog.service.impl;

import org.dromara.blog.domain.TUserInfo;
import org.dromara.blog.domain.bo.TUserInfoBo;
import org.dromara.blog.domain.vo.TUserInfoVo;
import org.dromara.blog.mapper.TUserInfoMapper;
import org.dromara.blog.service.ITUserInfoService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 信息管理Service业务层处理
 *
 * @author Lion Li
 * @date 2024-12-05
 */
@RequiredArgsConstructor
@Service
public class TUserInfoServiceImpl implements ITUserInfoService {

    private final TUserInfoMapper baseMapper;

    /**
     * 查询信息管理
     *
     * @param id 主键
     * @return 信息管理
     */
    @Override
    public TUserInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询信息管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 信息管理分页列表
     */
    @Override
    public TableDataInfo<TUserInfoVo> queryPageList(TUserInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TUserInfo> lqw = buildQueryWrapper(bo);
        Page<TUserInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的信息管理列表
     *
     * @param bo 查询条件
     * @return 信息管理列表
     */
    @Override
    public List<TUserInfoVo> queryList(TUserInfoBo bo) {
        LambdaQueryWrapper<TUserInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TUserInfo> buildQueryWrapper(TUserInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TUserInfo> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getUsername()), TUserInfo::getUsername, bo.getUsername());
        lqw.like(StringUtils.isNotBlank(bo.getNickname()), TUserInfo::getNickname, bo.getNickname());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), TUserInfo::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增信息管理
     *
     * @param bo 信息管理
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TUserInfoBo bo) {
        TUserInfo add = MapstructUtils.convert(bo, TUserInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改信息管理
     *
     * @param bo 信息管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TUserInfoBo bo) {
        TUserInfo update = MapstructUtils.convert(bo, TUserInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TUserInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除信息管理信息
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
