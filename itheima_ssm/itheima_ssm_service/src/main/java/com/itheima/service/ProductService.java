package com.itheima.service;

import com.itheima.domian.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll() throws Exception;

    void saveProduct(Product product) throws  Exception;
}
