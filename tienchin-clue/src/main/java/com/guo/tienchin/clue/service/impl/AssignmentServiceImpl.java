package com.guo.tienchin.clue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.guo.tienchin.clue.domain.Assignment;
import com.guo.tienchin.clue.mapper.AssignmentMapper;
import com.guo.tienchin.clue.service.IAssignmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.tienchin.common.constant.TienChinConstants;
import com.guo.tienchin.common.core.domain.AjaxResult;
import com.guo.tienchin.common.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guo
 * @since 2023-02-14
 */
@Service
public class AssignmentServiceImpl extends ServiceImpl<AssignmentMapper, Assignment> implements IAssignmentService {

    @Override
    @Transactional
    public AjaxResult assignClue(Assignment assignment) {
        try {
            // 1. 先将线索的所有分配记录设为非最新
            UpdateWrapper<Assignment> uw = new UpdateWrapper<>();
            uw.lambda().set(Assignment::getLatest, false).eq(Assignment::getAssignId, assignment.getAssignId());
            // 2. 重新分配线索
            assignment.setType(TienChinConstants.CLUE_TYPE);
            assignment.setCreateBy(SecurityUtils.getUsername());
            assignment.setCreateTime(LocalDateTime.now());
            assignment.setLatest(true);
            save(assignment);
            return AjaxResult.success("分配线索成功");
        } catch (Exception e) {
            return AjaxResult.error("分配线索失败" + e.getMessage());
        }
    }
}
