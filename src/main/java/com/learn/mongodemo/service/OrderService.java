package com.learn.mongodemo.service;

import com.learn.mongodemo.pojo.logistics;
import com.learn.mongodemo.pojo.order;

import java.util.List;

/**
 * Description:
 * date: 2022/4/22 13:41
 * Package: com.learn.mongodemo.service
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public interface OrderService {

    /**
     * 添加订单
     */
    void addOrder(order order);

    /**
     * 更新物流
     */
    void addLogisticsAndUpdateStatus(logistics logistics);

    /**
     * 通过id查询物流
     */
    order getOrderById(int id);

    /**
     * 根据id删除记录
     */
    boolean deleteOrderById(int id);

    /**
     * 查询所有订单
     */
    List<order> getAllOrder();

}
