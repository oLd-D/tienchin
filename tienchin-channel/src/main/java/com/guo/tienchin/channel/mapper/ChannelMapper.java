package com.guo.tienchin.channel.mapper;

import com.guo.tienchin.channel.domain.Channel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.tienchin.channel.domain.vo.ChannelVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guo
 * @since 2023-02-01
 */
public interface ChannelMapper extends BaseMapper<Channel> {

    List<Channel> selectChannelList(ChannelVO channelVO);
}
