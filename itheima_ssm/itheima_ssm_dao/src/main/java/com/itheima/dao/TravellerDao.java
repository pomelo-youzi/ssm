package com.itheima.dao;

import com.itheima.domian.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {
    /**
     * 根据id查询所有游客
     *
     * @param id
     * @return
     */
    @Select("select * from TRAVELLER where ID in (select travellerID from ORDER_TRAVELLER where orderId = #{id}) ")
    public List<Traveller> findByOrdersId(String id);
}
