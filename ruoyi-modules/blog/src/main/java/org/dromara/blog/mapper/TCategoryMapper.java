package org.dromara.blog.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.blog.domain.TCategory;
import org.dromara.blog.domain.vo.TCategoryVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 文章分类Mapper接口
 *
 * @author LiuJinYu
 * @date 2024-12-11
 */
public interface TCategoryMapper extends BaseMapperPlus<TCategory, TCategoryVo> {

    List<TCategoryVo> getCategory();

    void updateArticleCount(@Param("category") String category, @Param("aLong") Long aLong);
}
