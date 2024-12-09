package org.dromara.blog.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.blog.domain.TSiteStats;
import org.dromara.blog.domain.vo.TSiteStatsVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 网站统计Mapper接口
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
public interface TSiteStatsMapper extends BaseMapperPlus<TSiteStats, TSiteStatsVo> {
    void updateTagNumber(@Param("aLong") Long aLong, @Param("name") String name);

    List<TSiteStatsVo> getSiteStats();
}
