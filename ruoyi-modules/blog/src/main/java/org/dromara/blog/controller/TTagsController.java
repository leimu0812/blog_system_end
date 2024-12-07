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
import org.dromara.blog.domain.vo.TTagsVo;
import org.dromara.blog.domain.bo.TTagsBo;
import org.dromara.blog.service.ITTagsService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 文章标签管理
 *
 * @author LiuJinYu
 * @date 2024-12-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/tags")
public class TTagsController extends BaseController {

    private final ITTagsService tTagsService;

    @GetMapping("/articlesSelect")
    public R<List<TTagsVo>> articlesSelect() {
        return R.ok(tTagsService.selectStatusTags());
    }

    /**
     * 查询文章标签管理列表
     */
    @SaCheckPermission("blog:tags:list")
    @GetMapping("/list")
    public TableDataInfo<TTagsVo> list(TTagsBo bo, PageQuery pageQuery) {
        return tTagsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出文章标签管理列表
     */
    @SaCheckPermission("blog:tags:export")
    @Log(title = "文章标签管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TTagsBo bo, HttpServletResponse response) {
        List<TTagsVo> list = tTagsService.queryList(bo);
        ExcelUtil.exportExcel(list, "文章标签管理", TTagsVo.class, response);
    }

    /**
     * 获取文章标签管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:tags:query")
    @GetMapping("/{id}")
    public R<TTagsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tTagsService.queryById(id));
    }

    /**
     * 新增文章标签管理
     */
    @SaCheckPermission("blog:tags:add")
    @Log(title = "文章标签管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TTagsBo bo) {
        return toAjax(tTagsService.insertByBo(bo));
    }

    /**
     * 修改文章标签管理
     */
    @SaCheckPermission("blog:tags:edit")
    @Log(title = "文章标签管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TTagsBo bo) {
        return toAjax(tTagsService.updateByBo(bo));
    }

    /**
     * 删除文章标签管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:tags:remove")
    @Log(title = "文章标签管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tTagsService.deleteWithValidByIds(List.of(ids), true));
    }
}
