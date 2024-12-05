package org.dromara.blog.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.blog.domain.bo.TUserInfoBo;
import org.dromara.blog.domain.vo.TUserInfoVo;
import org.dromara.blog.service.ITUserInfoService;
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
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 信息管理
 *
 * @author Lion Li
 * @date 2024-12-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/userInfo")
public class TUserInfoController extends BaseController {

    private final ITUserInfoService tUserInfoService;

    /**
     * 查询信息管理列表
     */
    @SaCheckPermission("blog:userInfo:list")
    @GetMapping("/list")
    public TableDataInfo<TUserInfoVo> list(TUserInfoBo bo, PageQuery pageQuery) {
        return tUserInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出信息管理列表
     */
    @SaCheckPermission("blog:userInfo:export")
    @Log(title = "信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TUserInfoBo bo, HttpServletResponse response) {
        List<TUserInfoVo> list = tUserInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "信息管理", TUserInfoVo.class, response);
    }

    /**
     * 获取信息管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("blog:userInfo:query")
    @GetMapping("/{id}")
    public R<TUserInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                  @PathVariable Long id) {
        return R.ok(tUserInfoService.queryById(id));
    }

    /**
     * 新增信息管理
     */
    @SaCheckPermission("blog:userInfo:add")
    @Log(title = "信息管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TUserInfoBo bo) {
        return toAjax(tUserInfoService.insertByBo(bo));
    }

    /**
     * 修改信息管理
     */
    @SaCheckPermission("blog:userInfo:edit")
    @Log(title = "信息管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TUserInfoBo bo) {
        return toAjax(tUserInfoService.updateByBo(bo));
    }

    /**
     * 删除信息管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("blog:userInfo:remove")
    @Log(title = "信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tUserInfoService.deleteWithValidByIds(List.of(ids), true));
    }
}
