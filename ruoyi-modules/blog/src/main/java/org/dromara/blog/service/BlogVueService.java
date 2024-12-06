package org.dromara.blog.service;

import org.dromara.blog.domain.vo.TSiteStatsVo;
import org.dromara.blog.domain.vo.TSocialLinksVo;
import org.dromara.blog.domain.vo.TTagsVo;
import org.dromara.blog.domain.vo.TUserInfoVo;

import java.util.List;

public interface BlogVueService {

    TUserInfoVo getInfo();

    List<TSocialLinksVo> getSocialLink();

    List<TTagsVo> getTags();

    List<TSiteStatsVo> getSiteStats();
}
