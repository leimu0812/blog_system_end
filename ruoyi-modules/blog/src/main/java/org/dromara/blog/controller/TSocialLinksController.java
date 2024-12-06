package org.dromara.blog.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.blog.domain.vo.TSocialLinksVo;
import org.dromara.blog.domain.bo.TSocialLinksBo;
import org.dromara.blog.service.ITSocialLinksService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 社交链接管理
 *
 * @author Lion Li
 * @date 2024-12-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/socialLinks")
public class TSocialLinksController extends BaseController {

    private final ITSocialLinksService tSocialLinksService;

    /**
     * 查询社交链接管理列表
     */
    @SaCheckPermission("blog:socialLinks:list")
    @GetMapping("/list")
    public TableDataInfo<TSocialLinksVo> list(TSocialLinksBo bo, PageQuery pageQuery) {
        return tSocialLinksService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出社交链接管理列表
     */
    @SaCheckPermission("blog:socialLinks:export")
    @Log(title = "社交链接管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TSocialLinksBo bo, HttpServletResponse response) {
        List<TSocialLinksVo> list = tSocialLinksService.queryList(bo);
        ExcelUtil.exportExcel(list, "社交链接管理", TSocialLinksVo.class, response);
    }

    /**
     * 获取社交链接管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:socialLinks:query")
    @GetMapping("/{id}")
    public R<TSocialLinksVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tSocialLinksService.queryById(id));
    }

    /**
     * 新增社交链接管理
     */
    @SaCheckPermission("blog:socialLinks:add")
    @Log(title = "社交链接管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TSocialLinksBo bo) {
        return toAjax(tSocialLinksService.insertByBo(bo));
    }

    /**
     * 修改社交链接管理
     */
    @SaCheckPermission("blog:socialLinks:edit")
    @Log(title = "社交链接管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TSocialLinksBo bo) {
        return toAjax(tSocialLinksService.updateByBo(bo));
    }

    /**
     * 删除社交链接管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:socialLinks:remove")
    @Log(title = "社交链接管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tSocialLinksService.deleteWithValidByIds(List.of(ids), true));
    }
}
