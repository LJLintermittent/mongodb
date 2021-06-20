package com.learn.mongodemo.controller;

import com.learn.mongodemo.pojo.logistics;
import com.learn.mongodemo.pojo.order;
import com.learn.mongodemo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/11/12 20:31<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
@RestController
public class OrderController {

    public static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @PostMapping("/addOrder")
    public String addOrder(order order) {
        order.setStatus("发货中");
        order.setOrderTime(new Date());
        order.setShipTime(new Date());
        orderService.addOrder(order);
        return "添加成功";
    }

    @PostMapping("/updateOrder")
    public String updateOrder(logistics logistics) {
        logistics.setOperationTime(new Date());
        orderService.addLogisticsAndUpdateStatus(logistics);
        return "添加成功";
    }

    @GetMapping("getOrderById")
    public order getOrderById(int id) {
        order order = orderService.getOrderById(id);
        return order;
    }

    @GetMapping("deleteById")
    public String deleteById(int id) {
        orderService.deleteOrderById(id);
        return "成功";
    }

    @GetMapping("getAllOrders")
    public Map<String, Object> getAllOrder() {
        Map<String, Object> map = new HashMap<>();
        List<order> list = orderService.getAllOrder();
        map.put("code", "0");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }
}
