package com.guo.tienchin.channel.service;

import com.guo.tienchin.channel.domain.Channel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.tienchin.channel.domain.vo.ChannelVO;
import com.guo.tienchin.common.core.domain.AjaxResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guo
 * @since 2023-02-01
 */
public interface IChannelService extends IService<Channel> {

    List<Channel> selectChannelList(ChannelVO channelVO);

    AjaxResult addChannel(ChannelVO channelVO);

    AjaxResult updateChannel(ChannelVO channelVO);

    boolean deleteChannelByIds(Long[] channelIds);

    Boolean importChannel(List<Channel> channelList, boolean updateSupport);

}
