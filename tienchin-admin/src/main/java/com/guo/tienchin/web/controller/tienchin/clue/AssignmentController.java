package com.guo.tienchin.web.controller.tienchin.clue;

import com.guo.tienchin.clue.domain.Assignment;
import com.guo.tienchin.clue.service.IAssignmentService;
import com.guo.tienchin.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author guo
 * @since 2023-02-14
 */
@RestController
@RequestMapping("/tienchin/assignment")
public class AssignmentController {

    @Autowired
    IAssignmentService assignmentService;

    @PostMapping
    @PreAuthorize("hasPermission('tienchin:clue:assignment')")
    public AjaxResult assignClue(@RequestBody Assignment assignment) {
        return assignmentService.assignClue(assignment);
    }
}
