package org.dromara.blog.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.blog.domain.TArticles;
import org.dromara.blog.domain.vo.TArticlesVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 文章管理Mapper接口
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
public interface TArticlesMapper extends BaseMapperPlus<TArticles, TArticlesVo> {

    Long selectCategoryCount(@Param("category") String category);
}
