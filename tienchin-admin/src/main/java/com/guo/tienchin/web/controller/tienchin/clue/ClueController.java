package com.guo.tienchin.web.controller.tienchin.clue;

import com.guo.tienchin.activity.domain.vo.ActivityVO;
import com.guo.tienchin.activity.service.IActivityService;
import com.guo.tienchin.channel.service.IChannelService;
import com.guo.tienchin.clue.domain.Clue;
import com.guo.tienchin.clue.domain.vo.ClueSummary;
import com.guo.tienchin.clue.service.IClueService;
import com.guo.tienchin.common.annotation.Log;
import com.guo.tienchin.common.core.controller.BaseController;
import com.guo.tienchin.common.core.domain.AjaxResult;
import com.guo.tienchin.common.core.page.TableDataInfo;
import com.guo.tienchin.common.enums.BusinessType;
import com.guo.tienchin.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author guo
 * @since 2023-02-14
 */
@RestController
@RequestMapping("/tienchin/clue")
public class ClueController extends BaseController {

    @Autowired
    IClueService clueService;
    @Autowired
    IChannelService channelService;
    @Autowired
    ISysUserService sysUserService;
    @Autowired
    IActivityService activityService;

    @PreAuthorize("hasPermission('tienchin:clue:add')")
    @Log(title = "线索管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addClue(@Validated @RequestBody Clue clue) {
        return clueService.addClue(clue);
    }

    @PreAuthorize("hasPermission('tienchin:clue:add')")
    @GetMapping("/channels")
    public AjaxResult getAllChannels() {
        return AjaxResult.success(channelService.list());
    }

    @PreAuthorize("hasPermission('tienchin:clue:add')")
    @GetMapping("/activity/{channelId}")
    public AjaxResult getActivityByChannelId(@PathVariable Integer channelId) {
        return activityService.getActivityByChannelId(channelId);
    }

    /**
     * 获取线索列表
     */
    @PreAuthorize("hasPermission('tienchin:clue:list')")
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<ClueSummary> list = clueService.selectClueList();
        return getDataTable(list);
    }

    @PreAuthorize("hasPermission('tienchin:clue:assign')")
    @GetMapping("/users/{deptId}")
    public AjaxResult getUsersByDeptId(@PathVariable Long deptId) {
        return sysUserService.getUsersByDeptId(deptId);
    }

    @PreAuthorize("hasAnyPermission('tienchin:clue:view', 'tienchin:clue:follow')")
    @GetMapping("/{clueId}")
    public AjaxResult getClueDetailsByClueId(@PathVariable Long clueId) {
        return clueService.getClueDetailsByClueId(clueId);
    }

}
