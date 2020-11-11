package com.qly.mall.crontab;

import com.qly.mall.service.BannerService;
import com.qly.mall.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;


@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    @Autowired
    OrdersService ordersService;

    static final Logger logger = LoggerFactory.getLogger(RedisKeyExpirationListener.class);

    public RedisKeyExpirationListener(RedisMessageListenerContainer container) {
        super(container);
    }

    /**
     * 超时失效订单，更新状态
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = message.toString();
        if (key != null && key.startsWith("order")) {
            String orderId = key.substring(5);
            ordersService.CloseOrder(Integer.parseInt(orderId));
            logger.info("订单号为：" + orderId + "的订单超时未支付，关闭订单");
        }
    }
}
