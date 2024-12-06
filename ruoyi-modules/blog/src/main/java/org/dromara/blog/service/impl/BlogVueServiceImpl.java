package org.dromara.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.vo.TSocialLinksVo;
import org.dromara.blog.domain.vo.TTagsVo;
import org.dromara.blog.domain.vo.TUserInfoVo;
import org.dromara.blog.mapper.TSocialLinksMapper;
import org.dromara.blog.mapper.TTagsMapper;
import org.dromara.blog.mapper.TUserInfoMapper;
import org.dromara.blog.service.BlogVueService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogVueServiceImpl implements BlogVueService {

    private final TUserInfoMapper tUserInfoMapper;
    private final TSocialLinksMapper tSocialLinksMapper;
    private final TTagsMapper tTagsMapper;

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
}
