package com.guo.tienchin.activity.service;

import com.guo.tienchin.activity.domain.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.tienchin.activity.domain.vo.ActivityVO;
import com.guo.tienchin.common.core.domain.AjaxResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guo
 * @since 2023-02-06
 */
public interface IActivityService extends IService<Activity> {

    List<ActivityVO> selectActivityList(ActivityVO activityVO);

    AjaxResult addActivity(ActivityVO activityVO);

    AjaxResult updateActivity(ActivityVO activityVO);

    ActivityVO getActivityById(Long activityId);

    boolean deleteActivityByIds(Long[] activityIds);

    AjaxResult getActivityByChannelId(Integer channelId);
}
