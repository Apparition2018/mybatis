package com.ljh.dao;

import com.ljh.entity.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderMapper
 *
 * @author ljh
 * created on 2021/8/16 2:22
 */
@Repository
public interface OrdersMapper {

    List<Orders> list();
}
