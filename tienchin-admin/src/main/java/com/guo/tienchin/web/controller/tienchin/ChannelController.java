package com.guo.tienchin.web.controller.tienchin;

import com.guo.tienchin.channel.domain.Channel;
import com.guo.tienchin.channel.domain.vo.ChannelVO;
import com.guo.tienchin.channel.service.IChannelService;
import com.guo.tienchin.common.annotation.Log;
import com.guo.tienchin.common.constant.UserConstants;
import com.guo.tienchin.common.core.controller.BaseController;
import com.guo.tienchin.common.core.domain.AjaxResult;
import com.guo.tienchin.common.core.domain.entity.SysRole;
import com.guo.tienchin.common.core.domain.entity.SysUser;
import com.guo.tienchin.common.core.page.TableDataInfo;
import com.guo.tienchin.common.enums.BusinessType;
import com.guo.tienchin.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author guo
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/tienchin/channel")
public class ChannelController extends BaseController {

    @Autowired
    IChannelService channelService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("hasPermission('tienchin:channel:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChannelVO channelVO) {
        startPage();
        List<Channel> list = channelService.selectChannelList(channelVO);
        return getDataTable(list);
    }

    /**
     * 新增渠道
     */
    @PreAuthorize("hasPermission('system:role:add')")
    @Log(title = "渠道管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ChannelVO channelVO) {
        return channelService.addChannel(channelVO);
    }

    @PreAuthorize("hasPermission('tienchin:channel:edit')")
    @Log(title = "渠道管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ChannelVO channelVO) {
        return channelService.updateChannel(channelVO);
    }

    /**
     * 根据渠道id查询一个渠道
     * @param channelId
     * @return
     */
    @PreAuthorize("hasPermission('tienchin:channel:list')")
    @GetMapping("/{channelId}")
    public AjaxResult getChannel(@PathVariable Long channelId) {
        return AjaxResult.success(channelService.getById(channelId));
    }

    /**
     * 删除渠道
     */
    @PreAuthorize("hasPermission('tienchin:channel:remove')")
    @Log(title = "渠道管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{channelIds}")
    public AjaxResult remove(@PathVariable Long[] channelIds) {
        return toAjax(channelService.deleteChannelByIds(channelIds));
    }

    @Log(title = "渠道管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("hasPermission('tienchin:channel:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChannelVO channelVO) {
        List<Channel> list = channelService.selectChannelList(channelVO);
        ExcelUtil<Channel> util = new ExcelUtil<Channel>(Channel.class);
        util.exportExcel(response, list, "渠道数据");
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<Channel> util = new ExcelUtil<Channel>(Channel.class);
        util.importTemplateExcel(response, "渠道数据");
    }

    @Log(title = "渠道管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("hasPermission('tienchin:channel:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Channel> util = new ExcelUtil<Channel>(Channel.class);
        List<Channel> channelList = util.importExcel(file.getInputStream());
        return AjaxResult.success(channelService.importChannel(channelList, updateSupport));
    }
}
