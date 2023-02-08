package com.guo.tienchin.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.guo.tienchin.channel.domain.Channel;
import com.guo.tienchin.channel.domain.vo.ChannelVO;
import com.guo.tienchin.channel.mapper.ChannelMapper;
import com.guo.tienchin.channel.service.IChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.tienchin.common.core.domain.AjaxResult;
import com.guo.tienchin.common.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guo
 * @since 2023-02-01
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements IChannelService {

    @Autowired
    ChannelMapper channelMapper;

    /**
     * mybatis-plus 有相关方法, 但不宜使用, 因为可能导致分页失效, 因此此处还是选择 mybatis 自己写 SQL语句
     * @param
     * @param channelVO
     * @return
     */
    @Override
    public List<Channel> selectChannelList(ChannelVO channelVO) {
        return channelMapper.selectChannelList(channelVO);
    }

    @Override
    public AjaxResult addChannel(ChannelVO channelVO) {
        QueryWrapper<Channel> qw = new QueryWrapper<>();
        qw.lambda().eq(Channel::getChannelName, channelVO.getChannelName()).eq(Channel::getDelFlag, 0);
        Channel c = getOne(qw);
        if (c != null) {
            return AjaxResult.error("渠道名已存在");
        }
        Channel channel = new Channel();
        BeanUtils.copyProperties(channelVO, channel);
        channel.setCreateBy(SecurityUtils.getUsername());
        channel.setCreateTime(LocalDateTime.now());
        channel.setDelFlag(0);
        return save(channel) ? AjaxResult.success("添加成功"): AjaxResult.error("添加失败");
    }

    @Override
    public AjaxResult updateChannel(ChannelVO channelVO) {
        Channel channel = new Channel();
        BeanUtils.copyProperties(channelVO, channel);
        channel.setUpdateBy(SecurityUtils.getUsername());
        channel.setUpdateTime(LocalDateTime.now());
        // 防止前端修改
        channel.setCreateTime(null);
        channel.setCreateBy(null);
        channel.setDelFlag(null);
        return updateById(channel) ? AjaxResult.success("更新成功") : AjaxResult.error("更新失败");
    }

    @Override
    public boolean deleteChannelByIds(Long[] channelIds) {
        UpdateWrapper<Channel> uw = new UpdateWrapper<>();
        uw.lambda().set(Channel::getDelFlag, 1).in(Channel::getChannelId, channelIds);
        return update(uw);
    }

    /**
     * 导入渠道数据, 既可以用来更新旧数据, 也可以用来插入新数据
     * @param channelList
     * @param updateSupport
     * @return
     */
    @Override
    public Boolean importChannel(List<Channel> channelList, boolean updateSupport) {
        if (updateSupport) {
            // 更新
            List<Channel> channels = channelList.stream().map(c -> {
                c.setUpdateTime(LocalDateTime.now());
                c.setUpdateBy(SecurityUtils.getUsername());
                return c;
            }).collect(Collectors.toList());
            return updateBatchById(channels);
        }else {
            // 插入
            List<Channel> channels = channelList.stream().map(c -> {
                c.setCreateTime(LocalDateTime.now());
                c.setCreateBy(SecurityUtils.getUsername());
                c.setChannelId(null);
                return c;
            }).collect(Collectors.toList());
            return saveBatch(channels);
        }
    }



}
