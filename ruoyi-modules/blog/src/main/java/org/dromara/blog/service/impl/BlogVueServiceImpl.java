package org.dromara.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.vo.TUserInfoVo;
import org.dromara.blog.mapper.TUserInfoMapper;
import org.dromara.blog.service.BlogVueService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogVueServiceImpl implements BlogVueService {

    private final TUserInfoMapper tUserInfoMapper;

    @Override
    public TUserInfoVo getInfo() {
        return tUserInfoMapper.selectVoOne(null);
    }
}
