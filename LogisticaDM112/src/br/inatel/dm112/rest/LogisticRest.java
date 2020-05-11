package br.inatel.dm112.rest;

import br.inatel.dm112.interfaces.LogisticInterface;
import br.inatel.dm112.model.DeliveryInfo;
import br.inatel.dm112.model.LogisticStatus;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.services.LogisticServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LogisticRest implements LogisticInterface {

    @Autowired
    private LogisticServices logisticService;

    @GetMapping("/orders")
    @Override
    public List<Order> getFilledOrders() {
        return logisticService.getFilledOrders();
    }

    @Override
    @PutMapping("/delivery/start")
    public LogisticStatus setSelectedOrder(@RequestBody DeliveryInfo deliveryInfo) {
        return new LogisticStatus(deliveryInfo.getOrderNumber(), logisticService.selectedOrderToDelivery(deliveryInfo).getStatus());
    }

    @Override
    @PostMapping("/delivery/finish")
    public LogisticStatus confirmDelivery(@RequestBody DeliveryInfo deliveryInfo) {
        deliveryInfo.setDeliveryDate(new Date());
        System.out.println("confirm Delivery: " + deliveryInfo);
        return new LogisticStatus(deliveryInfo.getOrderNumber(), logisticService.confirmDelivery(deliveryInfo).getStatus());
    }
}
