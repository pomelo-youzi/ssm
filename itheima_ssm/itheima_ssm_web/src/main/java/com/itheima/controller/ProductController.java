package com.itheima.controller;

import com.itheima.domian.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService produceSevice;

    @RequestMapping("/save.do")
    public String testSave(Product product) throws Exception {
        produceSevice.saveProduct(product);
        return "redirect:findAll.do";
    }

    /**
     * 查询所有的方法
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView testFindAll() throws Exception {
        ModelAndView mv = new ModelAndView();
//        调用方法
        List<Product> list = produceSevice.findAll();
//        存储到域中
        mv.addObject("productList", list);
//        返回的页面
        mv.setViewName("product-list1");
        return mv;
    }
}
