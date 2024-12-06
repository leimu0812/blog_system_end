package org.dromara.blog.mapper;

import org.dromara.blog.domain.TTags;
import org.dromara.blog.domain.vo.TTagsVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 文章标签管理Mapper接口
 *
 * @author LiuJinYu
 * @date 2024-12-06
 */
public interface TTagsMapper extends BaseMapperPlus<TTags, TTagsVo> {

    List<TTagsVo> getTags();

    Long countAll();
}
