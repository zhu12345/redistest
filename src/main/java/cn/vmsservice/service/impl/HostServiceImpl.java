package cn.vmsservice.service.impl;

import cn.vmsservice.bo.Host;
import cn.vmsservice.dao.HostMapper;
import cn.vmsservice.service.HostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HostServiceImpl implements HostService {
    @Resource
    private HostMapper hostMapper;
    @Override
    public Host getHostById(String id) {
        return hostMapper.selectByPrimaryKey(id);
    }
}
