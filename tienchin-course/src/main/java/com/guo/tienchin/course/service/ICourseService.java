package com.guo.tienchin.course.service;

import com.guo.tienchin.common.core.domain.AjaxResult;
import com.guo.tienchin.course.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.tienchin.course.domain.vo.CourseVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guo
 * @since 2023-02-11
 */
public interface ICourseService extends IService<Course> {

    List<Course> selectCourseList(CourseVO courseVO);

    AjaxResult addCourse(Course course);

    AjaxResult updateCourse(Course course);

    boolean deleteCourseByIds(Long[] courseIds);
}
