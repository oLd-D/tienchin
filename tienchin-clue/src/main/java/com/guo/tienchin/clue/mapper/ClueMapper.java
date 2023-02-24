package com.guo.tienchin.clue.mapper;

import com.guo.tienchin.clue.domain.Clue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.tienchin.clue.domain.vo.ClueDetails;
import com.guo.tienchin.clue.domain.vo.ClueSummary;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guo
 * @since 2023-02-14
 */
public interface ClueMapper extends BaseMapper<Clue> {

    List<ClueSummary> selectClueList();

    ClueDetails getClueDetailsByClueId(Long clueId);
}
