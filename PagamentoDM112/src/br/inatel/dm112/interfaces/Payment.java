package br.inatel.dm112.interfaces;

import br.inatel.dm112.model.PaymentStatus;

public interface Payment {

	PaymentStatus startPaymentOfOrder(String cpf, Integer orderNumber);

	PaymentStatus confirmPaymentOfOrder(String cpf, Integer orderNumber);

}