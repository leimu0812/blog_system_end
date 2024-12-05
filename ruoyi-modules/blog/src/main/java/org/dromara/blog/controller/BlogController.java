package org.dromara.blog.controller;

import lombok.RequiredArgsConstructor;
import org.dromara.blog.domain.vo.TUserInfoVo;
import org.dromara.blog.service.BlogVueService;
import org.dromara.blog.service.ITUserInfoService;
import org.dromara.common.core.domain.R;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/info")
    public R<TUserInfoVo> getInfo() {
        return R.ok(blogVueService.getInfo());
    }


}
