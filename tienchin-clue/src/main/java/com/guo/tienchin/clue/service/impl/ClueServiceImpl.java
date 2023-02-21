package com.guo.tienchin.clue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guo.tienchin.clue.domain.Assignment;
import com.guo.tienchin.clue.domain.Clue;
import com.guo.tienchin.clue.domain.vo.ClueSummary;
import com.guo.tienchin.clue.mapper.ClueMapper;
import com.guo.tienchin.clue.service.IAssignmentService;
import com.guo.tienchin.clue.service.IClueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.tienchin.common.constant.TienChinConstants;
import com.guo.tienchin.common.core.domain.AjaxResult;
import com.guo.tienchin.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guo
 * @since 2023-02-14
 */
@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements IClueService {

    @Autowired
    IAssignmentService assignmentService;

    @Autowired
    ClueMapper clueMapper;

    @Override
    @Transactional
    public AjaxResult addClue(Clue clue) {
        QueryWrapper<Clue> qw = new QueryWrapper<>();
        qw.lambda().eq(Clue::getPhone, clue.getPhone());
        Clue one = getOne(qw);
        if (one != null) {
            return AjaxResult.error("电话号码重复, 录入失败");
        }
        clue.setNextTime(LocalDateTime.now().plusHours(TienChinConstants.NEXT_FOLLOW_TIME));
        clue.setCreateBy(SecurityUtils.getUsername());
        clue.setCreateTime(LocalDateTime.now());
        try {
            //添加线索
            save(clue);
            //添加线索默认的分配人
            Assignment assignment = new Assignment();
            assignment.setAssignId(clue.getClueId());
            assignment.setLatest(true);
            assignment.setType(TienChinConstants.CLUE_TYPE);
            assignment.setUserName(SecurityUtils.getUsername());
            assignment.setUserId(SecurityUtils.getUserId());
            assignment.setDeptId(SecurityUtils.getDeptId());
            assignment.setCreateBy(SecurityUtils.getUsername());
            assignment.setCreateTime(LocalDateTime.now());
            assignmentService.save(assignment);
            return AjaxResult.success("线索录入成功");
        } catch (Exception e) {
            return AjaxResult.error("线索录入失败");
        }
    }

    @Override
    public List<ClueSummary> selectClueList() {
        return clueMapper.selectClueList();
    }
}
