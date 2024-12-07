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
import org.dromara.blog.domain.vo.TArticlesVo;
import org.dromara.blog.domain.bo.TArticlesBo;
import org.dromara.blog.service.ITArticlesService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 文章管理
 *
 * @author LiuJinYu
 * @date 2024-12-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/articles")
public class TArticlesController extends BaseController {

    private final ITArticlesService tArticlesService;

    /**
     * 查询文章管理列表
     */
    @SaCheckPermission("blog:articles:list")
    @GetMapping("/list")
    public TableDataInfo<TArticlesVo> list(TArticlesBo bo, PageQuery pageQuery) {
        return tArticlesService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出文章管理列表
     */
    @SaCheckPermission("blog:articles:export")
    @Log(title = "文章管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TArticlesBo bo, HttpServletResponse response) {
        List<TArticlesVo> list = tArticlesService.queryList(bo);
        ExcelUtil.exportExcel(list, "文章管理", TArticlesVo.class, response);
    }

    /**
     * 获取文章管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:articles:query")
    @GetMapping("/{id}")
    public R<TArticlesVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tArticlesService.queryById(id));
    }

    /**
     * 新增文章管理
     */
    @SaCheckPermission("blog:articles:add")
    @Log(title = "文章管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TArticlesBo bo) {
        return toAjax(tArticlesService.insertByBo(bo));
    }

    /**
     * 修改文章管理
     */
    @SaCheckPermission("blog:articles:edit")
    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TArticlesBo bo) {
        return toAjax(tArticlesService.updateByBo(bo));
    }

    /**
     * 删除文章管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:articles:remove")
    @Log(title = "文章管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tArticlesService.deleteWithValidByIds(List.of(ids), true));
    }
}
