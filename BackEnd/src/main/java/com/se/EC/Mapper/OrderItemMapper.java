package com.se.EC.Mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.se.EC.Entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemMapper extends MppBaseMapper<OrderItem> {
//        select * from sedb.orderItem , sedb.order  where ( sedb.order.state=0 or sedb.order.state=1 ) and orderItem.commodityId = 1 ;
        @Select("select * from orderItem , order  where order.id = orderItem.orderId  and( order.state=0 or order.state=1 ) and orderItem.commodityId = #{commodityId}")
        List<OrderItem> haveCommodityInOrder(@Param("commodityId") Integer commodityId);


}
