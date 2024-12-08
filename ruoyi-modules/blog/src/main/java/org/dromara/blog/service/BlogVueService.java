package org.dromara.blog.service;

import org.dromara.blog.domain.bo.TArticlesBo;
import org.dromara.blog.domain.vo.*;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.List;

public interface BlogVueService {

    TUserInfoVo getInfo();

    List<TSocialLinksVo> getSocialLink();

    List<TTagsVo> getTags();

    List<TSiteStatsVo> getSiteStats();

    TableDataInfo<TArticlesVo> getArticles(TArticlesBo bo, PageQuery pageQuery);
}
