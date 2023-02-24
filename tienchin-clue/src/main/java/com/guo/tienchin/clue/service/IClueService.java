package com.guo.tienchin.clue.service;

import com.guo.tienchin.clue.domain.Clue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.tienchin.clue.domain.vo.ClueSummary;
import com.guo.tienchin.common.core.domain.AjaxResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guo
 * @since 2023-02-14
 */
public interface IClueService extends IService<Clue> {

    AjaxResult addClue(Clue clue);

    List<ClueSummary> selectClueList();

    AjaxResult getClueDetailsByClueId(Long clueId);
}
