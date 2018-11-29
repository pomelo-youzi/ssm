package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domian.Orders;
import com.itheima.service.OrdersService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /* */

    /**
     * 查询所有（未分页）
     *
     * @return
     * @throws Exception
     *//*
    @RequestMapping("/findAll.do")
    public ModelAndView testFindAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> list = ordersService.findAll();
        mv.addObject("ordersList", list);
        mv.setViewName("orders-list");
        return mv;
    }*/

    /**
     * 查询所有分页
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView testFindAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "3") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> list = ordersService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    /**
     * 根据id查询orders
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView testFindById(@RequestParam(name = "id", required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
