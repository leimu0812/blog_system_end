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
import org.dromara.blog.domain.vo.TSiteStatsVo;
import org.dromara.blog.domain.bo.TSiteStatsBo;
import org.dromara.blog.service.ITSiteStatsService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 网站统计
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/siteStats")
public class TSiteStatsController extends BaseController {

    private final ITSiteStatsService tSiteStatsService;

    /**
     * 查询网站统计列表
     */
    @SaCheckPermission("blog:siteStats:list")
    @GetMapping("/list")
    public TableDataInfo<TSiteStatsVo> list(TSiteStatsBo bo, PageQuery pageQuery) {
        return tSiteStatsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出网站统计列表
     */
    @SaCheckPermission("blog:siteStats:export")
    @Log(title = "网站统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TSiteStatsBo bo, HttpServletResponse response) {
        List<TSiteStatsVo> list = tSiteStatsService.queryList(bo);
        ExcelUtil.exportExcel(list, "网站统计", TSiteStatsVo.class, response);
    }

    /**
     * 获取网站统计详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:siteStats:query")
    @GetMapping("/{id}")
    public R<TSiteStatsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tSiteStatsService.queryById(id));
    }

    /**
     * 新增网站统计
     */
    @SaCheckPermission("blog:siteStats:add")
    @Log(title = "网站统计", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TSiteStatsBo bo) {
        return toAjax(tSiteStatsService.insertByBo(bo));
    }

    /**
     * 修改网站统计
     */
    @SaCheckPermission("blog:siteStats:edit")
    @Log(title = "网站统计", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TSiteStatsBo bo) {
        return toAjax(tSiteStatsService.updateByBo(bo));
    }

    /**
     * 删除网站统计
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:siteStats:remove")
    @Log(title = "网站统计", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tSiteStatsService.deleteWithValidByIds(List.of(ids), true));
    }
}
