/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nntt.repositories.impl;

import com.nntt.pojo.Product;
import com.nntt.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    
    @Override
    public List<Product> getProducts(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Product> q = b.createQuery(Product.class);
        Root root = q.from(Product.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        String kw = params.get("kw");
        if (kw != null) {
            predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
        }

        String fromPrice = params.get("fromPrice");
        if (fromPrice != null) {
            predicates.add(b.greaterThanOrEqualTo(root.get("price"), Double.parseDouble(fromPrice)));
        }
        
        String toPrice = params.get("toPrice");
        if (toPrice != null) {
            predicates.add(b.lessThanOrEqualTo(root.get("price"), Double.parseDouble(fromPrice)));
        }
        
        String cateId = params.get("cateId");
        if (cateId != null){
            predicates.add(b.equal(root.get("categoryId"), Integer.parseInt(cateId)));
        }
        
        q.where(predicates.toArray(Predicate[]::new));
        
        Query query = s.createQuery(q);
        List<Product> products = query.getResultList();

        return products;
    }

    @Override
    public void addOrUpdate(Product p) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        s.saveOrUpdate(p);
    }
}
