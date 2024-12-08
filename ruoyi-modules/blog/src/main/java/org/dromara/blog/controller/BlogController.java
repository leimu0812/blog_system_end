package org.dromara.blog.controller;

import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.bo.TArticlesBo;
import org.dromara.blog.domain.vo.*;
import org.dromara.blog.service.BlogVueService;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Blog界面信息
 *
 * @author LiuJinYu
 * @date 2024-12-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/home")
public class BlogController extends BaseController {

    private final BlogVueService blogVueService;

    /**
     * 获取个人信息
     *
     * @return
     */
    @GetMapping("/info")
    public R<TUserInfoVo> getInfo() {
        return R.ok(blogVueService.getInfo());
    }

    /**
     * 获取社交连接
     *
     * @return
     */
    @GetMapping("/socialLink")
    public R<List<TSocialLinksVo>> redirect() {
        return R.ok(blogVueService.getSocialLink());
    }

    /**
     * 获取标签列表
     *
     * @return
     */
    @GetMapping("/tags")
    public R<List<TTagsVo>> getTags() {
        return R.ok(blogVueService.getTags());
    }

    /**
     * 获取站点统计数据
     *
     * @return
     */
    @GetMapping("/siteStats")
    public R<List<TSiteStatsVo>> getSiteStats() {
        return R.ok(blogVueService.getSiteStats());
    }

    @GetMapping("/articleList")
    public R<TableDataInfo<TArticlesVo>> getArticles(TArticlesBo bo, PageQuery pageQuery) {
        return R.ok(blogVueService.getArticles(bo, pageQuery));
    }
}
