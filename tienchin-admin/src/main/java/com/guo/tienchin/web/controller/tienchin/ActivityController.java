package com.guo.tienchin.web.controller.tienchin;

import com.guo.tienchin.activity.domain.vo.ActivityVO;
import com.guo.tienchin.activity.service.IActivityService;
import com.guo.tienchin.common.validator.AddGroup;
import com.guo.tienchin.channel.domain.vo.ChannelVO;
import com.guo.tienchin.channel.service.IChannelService;
import com.guo.tienchin.common.annotation.Log;
import com.guo.tienchin.common.core.controller.BaseController;
import com.guo.tienchin.common.core.domain.AjaxResult;
import com.guo.tienchin.common.core.page.TableDataInfo;
import com.guo.tienchin.common.enums.BusinessType;
import com.guo.tienchin.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author guo
 * @since 2023-02-06
 */
@RestController
@RequestMapping("/tienchin/activity")
public class ActivityController extends BaseController {
    @Autowired
    IActivityService activityService;

    @Autowired
    IChannelService channelService;

    /**
     * 获取渠道列表
     */
    @PreAuthorize("hasPermission('tienchin:activity:add')")
    @GetMapping("/channel/list")
    public AjaxResult channelList() {
        return AjaxResult.success(channelService.selectChannelList(new ChannelVO()));
    }

    /**
     * 获取活动列表
     */
    @PreAuthorize("hasPermission('tienchin:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActivityVO activityVO) {
        startPage();
        List<ActivityVO> list = activityService.selectActivityList(activityVO);
        return getDataTable(list);
    }

    /**
     * 新增活动
     * @param activityVO
     * @return
     */
    @PreAuthorize("hasPermission('tienchin:activity:add')")
    @Log(title = "活动管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody ActivityVO activityVO) {
        return activityService.addActivity(activityVO);
    }

    /**
     * 修改活动
     * @param activityVO
     * @return
     */
    @PreAuthorize("hasPermission('tienchin:activity:edit')")
    @Log(title = "活动管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ActivityVO activityVO) {
        return activityService.updateActivity(activityVO);
    }

    /**
     * 根据活动id查询一个活动
     * @param activityId
     * @return
     */
    @PreAuthorize("hasPermission('tienchin:activity:edit')")
    @GetMapping("/{activityId}")
    public AjaxResult getActivity(@PathVariable Long activityId) {
        return AjaxResult.success(activityService.getActivityById(activityId));
    }

    /**
     * 删除活动
     */
    @PreAuthorize("hasPermission('tienchin:activity:remove')")
    @Log(title = "活动管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    public AjaxResult remove(@PathVariable Long[] activityIds) {
        return toAjax(activityService.deleteActivityByIds(activityIds));
    }

    @Log(title = "活动管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("hasPermission('tienchin:activity:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActivityVO activityVO) {
        List<ActivityVO> list = activityService.selectActivityList(activityVO);
        ExcelUtil<ActivityVO> util = new ExcelUtil<ActivityVO>(ActivityVO.class);
        util.exportExcel(response, list, "活动数据");
    }

}
