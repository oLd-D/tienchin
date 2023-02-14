package com.guo.tienchin.web.controller.tienchin;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.guo.tienchin.common.annotation.Log;
import com.guo.tienchin.common.core.controller.BaseController;
import com.guo.tienchin.common.core.domain.AjaxResult;
import com.guo.tienchin.common.core.page.TableDataInfo;
import com.guo.tienchin.common.enums.BusinessType;
import com.guo.tienchin.common.utils.poi.ExcelUtil;
import com.guo.tienchin.course.domain.Course;
import com.guo.tienchin.course.domain.vo.CourseVO;
import com.guo.tienchin.course.service.ICourseService;
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
 * @since 2023-02-11
 */
@RestController
@RequestMapping("/tienchin/course")
public class CourseController extends BaseController {
    @Autowired
    ICourseService courseService;

    /**
     * 查询课程列表
     * @return
     */
    @PreAuthorize("hasPermission('tienchin:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(CourseVO courseVO) {
        startPage();
        List<Course> list = courseService.selectCourseList(courseVO);
        return getDataTable(list);
    }

    /**
     * 添加课程
     * @param course
     * @return
     */
    @PreAuthorize("hasPermission('tienchin:course:add')")
    @Log(title = "课程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Course course) {
        return courseService.addCourse(course);
    }

    /**
     * 修改课程
     * @param course
     * @return
     */
    @PreAuthorize("hasPermission('tienchin:course:edit')")
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@Validated @RequestBody Course course) {
        return courseService.updateCourse(course);
    }

    /**
     * 根据活动id查询一个课程
     * @param courseId
     * @return
     */
    @PreAuthorize("hasPermission('tienchin:course:edit')")
    @GetMapping("/{courseId}")
    public AjaxResult getCourse(@PathVariable Long courseId) {
        return AjaxResult.success(courseService.getById(courseId));
    }

    @PreAuthorize("hasPermission('tienchin:course:remove')")
    @Log(title = "课程管理", businessType = BusinessType.DELETE)
    @DeleteMapping
    public AjaxResult remove(Long[] courseIds) {
        return toAjax(courseService.deleteCourseByIds(courseIds));
    }

    @Log(title = "课程管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("hasPermission('tienchin:course:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourseVO courseVO) {
        List<Course> list = courseService.selectCourseList(courseVO);
        ExcelUtil<Course> util = new ExcelUtil<>(Course.class);
        util.exportExcel(response, list, "课程数据");
    }
}
