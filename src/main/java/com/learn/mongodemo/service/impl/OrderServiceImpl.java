package com.learn.mongodemo.service.impl;

import com.learn.mongodemo.pojo.logistics;
import com.learn.mongodemo.pojo.order;
import com.learn.mongodemo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 一些声明信息
 * Description:
 * date: 2020/11/12 20:33
 *
 * @author 李佳乐
 * @version  JDK 1.8
 */
@Service
@SuppressWarnings("all")
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加订单
     */
    public void addOrder(order order) {
        mongoTemplate.insert(order, "order");
    }

    /**
     * 更新物流
     */
    public void addLogisticsAndUpdateStatus(logistics logistics) {
        String status = logistics.getOperation();
        Query query = new Query(Criteria.where("_id").is(logistics.getOrderId()));
        Update update = new Update();
        update.set("status", status);
        update.push("logistics", logistics);
        mongoTemplate.upsert(query, update, order.class);
    }

    /**
     * 通过id查询物流
     */
    public order getOrderById(int id) {
        Query query = new Query(Criteria.where("_id").is(id));
        order order = mongoTemplate.findOne(query, order.class);
        return order;
    }

    /**
     * 根据id删除记录
     */
    public boolean deleteOrderById(int id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, order.class, "order");
        return true;
    }

    /**
     * 查询所有订单
     */
    public List<order> getAllOrder() {
        List<order> list = mongoTemplate.findAll(order.class, "order");
        return list;
    }
}
