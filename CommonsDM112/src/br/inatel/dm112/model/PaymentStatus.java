package br.inatel.dm112.model;

public class PaymentStatus {

	private String cpf;
	private Integer orderNumber;
	private int status;

	public PaymentStatus() {

	}

	public PaymentStatus(int status, String cpf, Integer orderNumber) {
		super();
		this.status = status;
		this.cpf = cpf;
		this.orderNumber = orderNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "PaymentStatus [cpf=" + cpf + ", orderNumber=" + orderNumber + ", status=" + status + "]";
	}

}
