package org.dromara.blog.mapper;

import org.dromara.blog.domain.TSocialLinks;
import org.dromara.blog.domain.vo.TSocialLinksVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 社交链接管理Mapper接口
 *
 * @author Lion Li
 * @date 2024-12-06
 */
public interface TSocialLinksMapper extends BaseMapperPlus<TSocialLinks, TSocialLinksVo> {

    List<TSocialLinksVo> getSocialLink();
}
