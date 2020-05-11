package br.inatel.dm112.services;

import br.inatel.dm112.client.EmailClient;
import br.inatel.dm112.model.*;
import br.inatel.dm112.model.dao.OrderDAO;
import br.inatel.dm112.model.entities.OrderEntity;
import br.inatel.dm112.rest.support.OrderDuplicateException;
import br.inatel.dm112.rest.support.OrderNotFoundException;
import br.inatel.dm112.rest.support.OrderStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private String sendToAddress = "rrocha.roberto@gmail.com";
    private String sendFromAddress = "robertorr9@gmail.com";
    private String sendPassAddress = "robertodm112";

    @Autowired
    private OrderDAO orderDAO;

    private EmailClient clientEmail = new EmailClient();

    public Order getOrder(Integer orderNumber) {

        OrderEntity entity = orderDAO.getOrderById(orderNumber);
        Order order = null;
        if (entity != null) {
            order = convertToOrder(entity);
        }
        return order;
    }

    public List<Order> searchOrders(String cpf) {

        List<OrderEntity> entities = orderDAO.getOrdersByCPF(cpf);
        List<Order> orders = new ArrayList<Order>();
        for (OrderEntity entity : entities) {
            Order order = convertToOrder(entity);
            orders.add(order);
        }
        return orders;
    }

    public void updateOrder(Order order) {

        OrderEntity entity = orderDAO.getOrderById(order.getNumber());
        if (entity != null) {
            convertOrderToEntityWithoutPK(order, entity); //don't change PK
            orderDAO.updateOrder(entity);
            System.out.println("OrderImpl updateOrder - atualizou o pedido com número: " + order.getNumber());
        } else {
            throw new OrderNotFoundException(
                    "Pedido não encontrado para fazer update: cpf: " + order.getCpf() + "valor: " + order.getValue());
        }
    }

    public void createOrder(Order order) {

        OrderEntity entity = orderDAO.getOrderById(order.getNumber());
        if (entity == null) {
            entity = new OrderEntity();
            entity.setNumber(order.getNumber());
            convertOrderToEntityWithoutPK(order, entity);

            System.out.println("OrderImpl updateOrder - pedido não encontrado com número: " + order.getNumber());
            orderDAO.insert(entity);
        } else {
            throw new OrderDuplicateException("Pedido já existe: " + order.getNumber());//TODO: melhorar a semântica
        }
    }

    public List<Order> getAllOrders() {
        List<OrderEntity> entities = orderDAO.getAllOrders();
        List<Order> orders = new ArrayList<>();

        for (OrderEntity entity : entities) {
            Order order = convertToOrder(entity);
            orders.add(order);
        }
        return orders;
    }

    private Order convertToOrder(OrderEntity entity) {
        Order order = new Order(
                entity.getNumber(),
                entity.getCpf(),
                entity.getValue(),
                entity.getStatus(),
                entity.getOrderDate(),
                entity.getIssueDate(),
                entity.getPaymentDate());
        return order;
    }

    private void convertOrderToEntityWithoutPK(Order order, OrderEntity entity) {
        entity.setCpf(order.getCpf());
        entity.setValue(order.getValue());
        entity.setStatus(order.getStatus());
        entity.setOrderDate(order.getOrderDate());
        entity.setIssueDate(order.getIssueDate());
        entity.setPaymentDate(order.getPaymentDate());
    }

    public List<Order> getOrderByStatus(int orderStatus) {
        List<OrderEntity> entities = orderDAO.getOrderByStatus(orderStatus);
        List<Order> orders = new ArrayList<Order>();
        for (OrderEntity entity : entities) {
            Order order = convertToOrder(entity);
            orders.add(order);
        }
        return orders;
    }

    public void updateOrderStatus(DeliveryInfo deliveryInfo, int status) {
        OrderEntity entity = orderDAO.getOrderById(deliveryInfo.getOrderNumber());
        if (entity != null) {
            if (status == entity.getStatus()) {
                throw new OrderStatusException("Pedido já possui este status.");
            } else if (entity.getStatus() > status) {
                throw new OrderStatusException("Pedido já entregue.");
            } else {
                entity.setStatus(status);
                orderDAO.updateOrder(entity);
                if (status == Order.STATUS.CONFIRMED.ordinal())
                    clientEmail.callSendMailService(
                            sendFromAddress, sendPassAddress, sendToAddress, getMessage(deliveryInfo
                            .getCpfClient()));

                System.out.println("OrderService updateOrder - Pedido saiu para a entrega: " + deliveryInfo.getOrderNumber());
            }
        } else {
            throw new OrderNotFoundException("Pedido não encontrado para fazer update: number: " + deliveryInfo.getOrderNumber());
        }
    }

    private String getMessage(String cpf) {
        return "Prezado cliente,\n\n"
                + "Seu pedido foi entregue com sucesso ao portador do cpf:" + cpf;
    }
}