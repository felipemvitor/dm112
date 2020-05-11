package br.inatel.dm112.interfaces;

import br.inatel.dm112.model.DeliveryInfo;
import br.inatel.dm112.model.LogisticStatus;
import br.inatel.dm112.model.Order;

import java.util.List;

public interface LogisticInterface {
    public List<Order> getFilledOrders();

    public LogisticStatus setSelectedOrder(DeliveryInfo deliveryInfo);

    public LogisticStatus confirmDelivery(DeliveryInfo deliveryInfo);
}
