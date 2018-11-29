package com.itheima.service;

import com.itheima.domian.Orders;

import java.util.List;

public interface OrdersService {
    public List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(String id) throws  Exception;
}
