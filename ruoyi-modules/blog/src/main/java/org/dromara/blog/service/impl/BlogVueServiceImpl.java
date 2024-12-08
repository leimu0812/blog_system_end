package org.dromara.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.bo.TArticlesBo;
import org.dromara.blog.domain.vo.*;
import org.dromara.blog.mapper.TSiteStatsMapper;
import org.dromara.blog.mapper.TSocialLinksMapper;
import org.dromara.blog.mapper.TTagsMapper;
import org.dromara.blog.mapper.TUserInfoMapper;
import org.dromara.blog.service.BlogVueService;
import org.dromara.blog.service.ITArticlesService;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogVueServiceImpl implements BlogVueService {

    private final TUserInfoMapper tUserInfoMapper;
    private final TSocialLinksMapper tSocialLinksMapper;
    private final TTagsMapper tTagsMapper;
    private final TSiteStatsMapper tSiteStatsMapper;
    private final ITArticlesService tArticlesService;

    /**
     * 获取信息
     *
     * @return
     */
    @Override
    public TUserInfoVo getInfo() {
        return tUserInfoMapper.getInfo();
    }


    /**
     * 获取社交链接
     *
     * @return
     */
    @Override
    public List<TSocialLinksVo> getSocialLink() {
        return tSocialLinksMapper.getSocialLink();
    }

    /**
     * 获取标签列表
     *
     * @return
     */
    @Override
    public List<TTagsVo> getTags() {
        return tTagsMapper.getTags();
    }

    /**
     * 获取站点统计
     *
     * @return
     */
    @Override
    public List<TSiteStatsVo> getSiteStats() {
        return tSiteStatsMapper.getSiteStats();
    }

    /**
     * 获取文章列表
     *
     * @return
     */
    @Override
    public TableDataInfo<TArticlesVo> getArticles(TArticlesBo bo, PageQuery pageQuery) {
        return tArticlesService.queryPageList(bo, pageQuery);
    }
}
