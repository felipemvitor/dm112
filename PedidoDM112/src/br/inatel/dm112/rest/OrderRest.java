package br.inatel.dm112.rest;

import br.inatel.dm112.interfaces.OrderInterface;
import br.inatel.dm112.model.DeliveryInfo;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;
import br.inatel.dm112.model.ResponseStatus;
import br.inatel.dm112.rest.support.OrderDuplicateException;
import br.inatel.dm112.rest.support.OrderNotFoundException;
import br.inatel.dm112.rest.support.OrderResponseError;
import br.inatel.dm112.rest.support.OrderStatusException;
import br.inatel.dm112.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRest implements OrderInterface {

    @Autowired
    private OrderService orderService;

    @Override
    @GetMapping("/order/{orderNumber}")
    public Order getOrder(@PathVariable("orderNumber") Integer orderNumber) {

        System.out.println("OrderRest - getOrder");
        return orderService.getOrder(orderNumber);
    }

    @Override
    @GetMapping("/orders/{cpf:.+}")
    public List<Order> searchOrders(@PathVariable("cpf") String cpf) {

        System.out.println("OrderRest - searchOrders");
        return orderService.searchOrders(cpf);
    }

    @Override
    @PutMapping("/order")
    public OrderResponse updateOrder(@RequestBody Order order) {

        System.out.println("OrderRest - updateOrder");
        orderService.updateOrder(order);
        return new OrderResponse(ResponseStatus.OK.ordinal());
    }

    //usado para teste
    @Override
    @GetMapping("/orders")
    public List<Order> getAllOrders() {

        System.out.println("OrderRest - getAllOrders");
        return orderService.getAllOrders();
    }

    @PostMapping("order")
    public OrderResponse saveNewOrder(@RequestBody Order newOrder) {

        System.out.println("OrderRest - saveNewOrder");
        orderService.createOrder(newOrder);
        return new OrderResponse(ResponseStatus.OK.ordinal());
    }

    @GetMapping("orders/delivery")
    public List<Order> getOrdersToDelivery() {

        System.out.println("OrderRest - getOrderDelivery");
        return orderService.getOrderByStatus(Order.STATUS.FILLED.ordinal());
    }

    @PutMapping("order/selectDelivery")
    public OrderResponse selectedOrderToDelivery(@RequestBody DeliveryInfo deliveryInfo) {
        System.out.println("OrderRest - selectedOrderToDelivery");
        orderService.updateOrderStatus(deliveryInfo, Order.STATUS.PENDING.ordinal());
        return new OrderResponse(ResponseStatus.OK.ordinal());
    }

    @PostMapping("order/confirmDelivery")
    public OrderResponse confirmDelivery(@RequestBody DeliveryInfo deliveryInfo) {
        System.out.println("OrderRest - confirmDelivery");
        orderService.updateOrderStatus(deliveryInfo, Order.STATUS.CONFIRMED.ordinal());
        return new OrderResponse(ResponseStatus.OK.ordinal());
    }

    @ExceptionHandler
    public ResponseEntity<OrderResponseError> handlerException(OrderNotFoundException ex) {

        ex.printStackTrace();

        OrderResponseError error = new OrderResponseError(); // create OrderResponseError
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<OrderResponseError> handlerException(OrderDuplicateException ex) {

        ex.printStackTrace();

        OrderResponseError error = new OrderResponseError(); // create OrderResponseError
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<OrderResponseError> handlerException(OrderStatusException exception) {
        exception.printStackTrace();

        OrderResponseError error = new OrderResponseError(); // create OrderResponseError
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }
}