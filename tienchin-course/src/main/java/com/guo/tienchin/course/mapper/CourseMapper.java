package com.guo.tienchin.course.mapper;

import com.guo.tienchin.course.domain.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.tienchin.course.domain.vo.CourseVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guo
 * @since 2023-02-11
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> selectCourseList(CourseVO courseVO);
}
