/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nntt.repositories.impl;

import com.nntt.pojo.Product;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class ProductRepositoryImpl {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    
    public List<Product> getProducts(Map<String, String> params){
        Session s = this.factoryBean.getObject().getCurrentSession();
        
        return null;
    }
    
    public void addOrUpdate(Product p){
    
    }
}
