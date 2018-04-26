package cn.vmsservice.dao;

import cn.vmsservice.bo.Host;

public interface HostMapper {
    int deleteByPrimaryKey(String id);

    int insert(Host record);

    int insertSelective(Host record);

    Host selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Host record);

    int updateByPrimaryKey(Host record);
}