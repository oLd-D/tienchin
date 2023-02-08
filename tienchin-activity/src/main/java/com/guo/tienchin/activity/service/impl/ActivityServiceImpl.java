package com.guo.tienchin.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.guo.tienchin.activity.domain.Activity;
import com.guo.tienchin.activity.domain.vo.ActivityVO;
import com.guo.tienchin.activity.mapper.ActivityMapper;
import com.guo.tienchin.activity.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.tienchin.common.core.domain.AjaxResult;
import com.guo.tienchin.common.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guo
 * @since 2023-02-06
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Autowired
    ActivityMapper activityMapper;

    @Override
    public List<ActivityVO> selectActivityList(ActivityVO activityVO) {
        expireActivity();
        return activityMapper.selectActivityList(activityVO);
    }

    @Override
    public AjaxResult addActivity(ActivityVO activityVO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityVO, activity);
        activity.setCreateBy(SecurityUtils.getUsername());
        activity.setCreateTime(LocalDateTime.now());
        return save(activity) ? AjaxResult.success("添加活动成功"): AjaxResult.error("添加活动失败");
    }

    @Override
    public AjaxResult updateActivity(ActivityVO activityVO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityVO, activity);
        activity.setUpdateBy(SecurityUtils.getUsername());
        activity.setUpdateTime(LocalDateTime.now());
        // 为null的值不会被修改
        activity.setCreateTime(null);
        activity.setCreateBy(null);
        activity.setDelFlag(null);
        return updateById(activity) ? AjaxResult.success("修改活动成功"): AjaxResult.error("修改活动失败");
    }

    @Override
    public ActivityVO getActivityById(Long activityId) {
        Activity activity = getById(activityId);
        ActivityVO activityVO = new ActivityVO();
        BeanUtils.copyProperties(activity, activityVO);
        return activityVO;
    }

    @Override
    public boolean deleteActivityByIds(Long[] activityIds) {
        UpdateWrapper<Activity> uw = new UpdateWrapper<>();
        uw.lambda().set(Activity::getDelFlag, 1).in(Activity::getActivityId, activityIds);
        return update(uw);
    }


    /**
     * 查询活动列表前先将过期活动设置一下
     * @return
     */
    private boolean expireActivity() {
        UpdateWrapper<Activity> uw = new UpdateWrapper<>();
        uw.lambda().set(Activity::getStatus, 0).eq(Activity::getStatus, 1).lt(Activity::getEndTime, LocalDateTime.now());
        return update(uw);
    }
}
