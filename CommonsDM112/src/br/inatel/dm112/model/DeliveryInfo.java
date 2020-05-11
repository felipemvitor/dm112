package br.inatel.dm112.model;

import java.util.Date;

public class DeliveryInfo {
    private Integer orderNumber;
    private Date deliveryDate;
    private String cpfClient;

    public DeliveryInfo() {

    }

    public DeliveryInfo(Integer orderNumber, Date deliveryDate, String cpfClient) {
        this.orderNumber = orderNumber;
        this.deliveryDate = deliveryDate;
        this.cpfClient = cpfClient;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getCpfClient() {
        return cpfClient;
    }

    public void setCpfClient(String cpfClient) {
        this.cpfClient = cpfClient;
    }
}
