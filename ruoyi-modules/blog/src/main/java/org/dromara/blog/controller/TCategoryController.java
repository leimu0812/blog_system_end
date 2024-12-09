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
import org.dromara.blog.domain.vo.TCategoryVo;
import org.dromara.blog.domain.bo.TCategoryBo;
import org.dromara.blog.service.ITCategoryService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 文章分类
 *
 * @author LiuJinYu
 * @date 2024-12-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/category")
public class TCategoryController extends BaseController {

    private final ITCategoryService tCategoryService;

    /**
     * 查询文章分类列表
     */
    @SaCheckPermission("blog:category:list")
    @GetMapping("/list")
    public TableDataInfo<TCategoryVo> list(TCategoryBo bo, PageQuery pageQuery) {
        return tCategoryService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出文章分类列表
     */
    @SaCheckPermission("blog:category:export")
    @Log(title = "文章分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TCategoryBo bo, HttpServletResponse response) {
        List<TCategoryVo> list = tCategoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "文章分类", TCategoryVo.class, response);
    }

    /**
     * 获取文章分类详细信息
     *
     * @param categoryId 主键
     */
    @SaCheckPermission("blog:category:query")
    @GetMapping("/{categoryId}")
    public R<TCategoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long categoryId) {
        return R.ok(tCategoryService.queryById(categoryId));
    }

    /**
     * 新增文章分类
     */
    @SaCheckPermission("blog:category:add")
    @Log(title = "文章分类", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TCategoryBo bo) {
        return toAjax(tCategoryService.insertByBo(bo));
    }

    /**
     * 修改文章分类
     */
    @SaCheckPermission("blog:category:edit")
    @Log(title = "文章分类", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TCategoryBo bo) {
        return toAjax(tCategoryService.updateByBo(bo));
    }

    /**
     * 删除文章分类
     *
     * @param categoryIds 主键串
     */
    @SaCheckPermission("blog:category:remove")
    @Log(title = "文章分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] categoryIds) {
        return toAjax(tCategoryService.deleteWithValidByIds(List.of(categoryIds), true));
    }
}
