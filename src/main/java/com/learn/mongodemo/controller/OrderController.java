package com.learn.mongodemo.controller;

import com.learn.mongodemo.pojo.logistics;
import com.learn.mongodemo.pojo.order;
import com.learn.mongodemo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 一些声明信息
 * Description:
 * date: 2020/11/12 20:31
 *
 * @author 李佳乐
 * @version JDK 1.8
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("api/logistics")
public class OrderController {

    public static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/addOrder")
    public String addOrder(order order) {
        order.setStatus("订单已创建");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String timeString = dateFormat.format(System.currentTimeMillis());
        order.setOrderTime(timeString);
        order.setShipTime(timeString);
        orderService.addOrder(order);
        return "添加成功";
    }

    /**
     * 更新订单信息
     */
    @PostMapping("/updateOrder")
    public String updateOrder(logistics logistics) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String timeString = dateFormat.format(System.currentTimeMillis());
        logistics.setOperationTime(timeString);
        orderService.addLogisticsAndUpdateStatus(logistics);
        return "添加成功";
    }

    /**
     * 根据订单ID获取订单信息
     */
    @GetMapping("/getOrderById")
    public order getOrderById(int id) {
        order order = orderService.getOrderById(id);
        return order;
    }

    /**
     * 根据订单ID删除订单
     */
    @GetMapping("/deleteById")
    public String deleteById(int id) {
        orderService.deleteOrderById(id);
        return "成功";
    }

    /**
     * 查询所有订单
     */
    @GetMapping("/getAllOrders")
    public Map<String, Object> getAllOrder() {
        Map<String, Object> resultMap = new HashMap<>();
        List<order> data = orderService.getAllOrder();
        resultMap.put("code", "0");
        resultMap.put("count", data.size());
        resultMap.put("data", data);
        return resultMap;
    }
}

