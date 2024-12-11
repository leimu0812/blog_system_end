package org.dromara.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dromara.blog.domain.TArticleTags;
import org.dromara.blog.domain.TTags;
import org.dromara.blog.mapper.*;
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
import org.dromara.blog.service.ITArticlesService;

import java.util.*;

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
    private final TArticleTagsMapper articleTagsMapper;
    private final TTagsMapper tagsMapper;
    private final TSiteStatsMapper siteStatsMapper;
    private final TCategoryMapper categoryMapper;

    @Override
    public TArticlesVo getArticleDetail(Long id) {
        TArticlesVo articleVo = baseMapper.selectVoById(id);
        if (articleVo != null) {
            // 查询文章标签
            List<TArticleTags> articleTags = articleTagsMapper.selectList(new QueryWrapper<TArticleTags>().eq("article_id", id));
            List<TTags> tags = new ArrayList<>();
            for (TArticleTags articleTag : articleTags) {
                TTags tag = tagsMapper.selectById(articleTag.getTagId());
                if (tag != null) {
                    tags.add(tag);
                }
            }
            articleVo.setTags(tags);
        }
        return articleVo;
    }

    /**
     * 查询文章列表
     *
     * @param bo
     * @param pageQuery
     * @return
     */
    @Override
    public TableDataInfo<TArticlesVo> getArticlesList(TArticlesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TArticles> lqw = new LambdaQueryWrapper<>();
        // 模糊查询文章标题，并且查询文章状态为1
        lqw.like(StringUtils.isNotBlank(bo.getTitle()), TArticles::getTitle, bo.getTitle())
            .eq(TArticles::getStatus, 1);
        // 分页查询
        Page<TArticlesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        // 查询每篇文章的标签信息
        for (TArticlesVo articleVo : result.getRecords()) {
            List<TArticleTags> articleTags = articleTagsMapper.selectList(new QueryWrapper<TArticleTags>().eq("article_id", articleVo.getId()));
            List<TTags> tags = new ArrayList<>();

            for (TArticleTags articleTag : articleTags) {
                TTags tag = tagsMapper.selectById(articleTag.getTagId());
                if (tag != null) {
                    tags.add(tag);
                }
            }
            articleVo.setTags(tags); // 设置标签信息
        }
        return TableDataInfo.build(result);
    }

    /**
     * 查询文章信息
     *
     * @param id 主键
     * @return 文章管理
     */
    @Override
    public TArticlesVo queryById(Long id) {
        TArticlesVo articleVo = baseMapper.selectVoById(id);

        // 查询与文章关联的标签
        List<TArticleTags> articleTags = articleTagsMapper.selectList(new QueryWrapper<TArticleTags>().eq("article_id", id));
        List<TTags> tags = new ArrayList<>();

        for (TArticleTags articleTag : articleTags) {
            TTags tag = tagsMapper.selectById(articleTag.getTagId());
            if (tag != null) {
                tags.add(tag); // 添加标签对象到列表
            }
        }
        articleVo.setTags(tags); // 设置标签信息
        return articleVo;
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

        // 查询每篇文章的标签信息
        for (TArticlesVo articleVo : result.getRecords()) {
            List<TArticleTags> articleTags = articleTagsMapper.selectList(new QueryWrapper<TArticleTags>().eq("article_id", articleVo.getId()));
            List<TTags> tags = new ArrayList<>();

            for (TArticleTags articleTag : articleTags) {
                TTags tag = tagsMapper.selectById(articleTag.getTagId());
                if (tag != null) {
                    tags.add(tag);
                }
            }
            articleVo.setTags(tags); // 设置标签信息
        }
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
            // 将新增的文章id添加到标签文章关联表中
            for (String tag : bo.getTags()) {
                TArticleTags articleTags = new TArticleTags();
                articleTags.setArticleId(add.getId());
                articleTags.setTagId(Long.parseLong(tag));
                articleTagsMapper.insert(articleTags);
                // 查询文章标签表的标签id数量，统计数量表对应的内容添加
                QueryWrapper<TArticleTags> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("tag_id", Long.parseLong(tag));
                Long l = articleTagsMapper.selectCount(queryWrapper);
                tagsMapper.updateCount(Long.parseLong(tag), l);
            }
            // 查询文章总数量，统计到统计表中
            QueryWrapper<TArticles> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("status", 1);
            siteStatsMapper.updateTagNumber(baseMapper.selectCount(queryWrapper), "文章");
            // 获取分类名称，统计该分类名称数量
            String category = bo.getCategory();
            Long i = baseMapper.selectCategoryCount(category);
            categoryMapper.updateArticleCount(category, i);
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
        // 将文章id删除标签文章关联表中
        articleTagsMapper.delete(Wrappers.<TArticleTags>lambdaQuery().eq(TArticleTags::getArticleId, update.getId()));
        // 将新增的文章id添加到标签文章关联表中
        for (String tag : bo.getTags()) {
            TArticleTags articleTags = new TArticleTags();
            articleTags.setArticleId(update.getId());
            articleTags.setTagId(Long.parseLong(tag));
            articleTagsMapper.insert(articleTags);
            // 查询文章标签表的标签id数量，统计数量表对应的内容添加
            QueryWrapper<TArticleTags> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tag_id", Long.parseLong(tag));
            Long l = articleTagsMapper.selectCount(queryWrapper);
            tagsMapper.updateCount(Long.parseLong(tag), l);
        }
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TArticles entity) {
        // TODO 做一些数据校验,如唯一约束
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
        if (isValid) {

        }
        // 遍历ids，在文章标签关联表中删除对应的数据
        for (Long id : ids) {
            articleTagsMapper.delete(Wrappers.<TArticleTags>lambdaQuery().eq(TArticleTags::getArticleId, id));
        }
        Boolean flag = baseMapper.deleteByIds(ids) > 0;
        if (flag) {
            QueryWrapper<TArticles> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("status", 1);
            siteStatsMapper.updateTagNumber(baseMapper.selectCount(queryWrapper), "文章");
        }
        return flag;
    }
}
