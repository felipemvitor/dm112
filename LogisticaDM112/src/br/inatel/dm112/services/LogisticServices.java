package br.inatel.dm112.services;

import br.inatel.dm112.client.OrderRestClient;
import br.inatel.dm112.model.DeliveryInfo;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LogisticServices {

    private OrderRestClient orderRestClient = new OrderRestClient();

    public List<Order> getFilledOrders() {
        List<Order> listOrders = orderRestClient.getFilledOrders();
        return listOrders;
    }

    public OrderResponse selectedOrderToDelivery(DeliveryInfo deliveryInfo) {
        return orderRestClient.selectedOrderToDelivery(deliveryInfo);
    }

    public OrderResponse confirmDelivery(DeliveryInfo deliveryInfo) {
        return orderRestClient.confirmDelivery(deliveryInfo);
    }

}
