package br.inatel.dm112.model;

public class LogisticStatus {
    private Integer orderNumber;
    private int status;

    public LogisticStatus(Integer orderNumber, int status) {
        this.orderNumber = orderNumber;
        this.status = status;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
