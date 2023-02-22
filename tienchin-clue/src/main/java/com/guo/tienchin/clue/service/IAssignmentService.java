package com.guo.tienchin.clue.service;

import com.guo.tienchin.clue.domain.Assignment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.tienchin.common.core.domain.AjaxResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guo
 * @since 2023-02-14
 */
public interface IAssignmentService extends IService<Assignment> {

    AjaxResult assignClue(Assignment assignment);
}
