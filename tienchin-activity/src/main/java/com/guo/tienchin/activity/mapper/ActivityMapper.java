package com.guo.tienchin.activity.mapper;

import com.guo.tienchin.activity.domain.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.tienchin.activity.domain.vo.ActivityVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guo
 * @since 2023-02-06
 */
public interface ActivityMapper extends BaseMapper<Activity> {

    List<ActivityVO> selectActivityList(ActivityVO activityVO);
}
