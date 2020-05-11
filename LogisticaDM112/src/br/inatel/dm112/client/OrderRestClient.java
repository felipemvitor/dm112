package br.inatel.dm112.client;

import br.inatel.dm112.model.DeliveryInfo;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

public class OrderRestClient {

    private String restUrl = "http://localhost:8080/PedidoDM112/api/";

    public List<Order> getFilledOrders() {
        String url = restUrl + "orders/delivery";
        return WebClient.create(url).get().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).retrieve().bodyToFlux(Order.class).collectList().log().block();
    }

    public OrderResponse selectedOrderToDelivery(DeliveryInfo deliveryInfo) {
        String url = restUrl + "order/selectDelivery";

        OrderResponse orderResponse = WebClient.create(url)
                .put()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(deliveryInfo), DeliveryInfo.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(OrderResponse.class)
                .log().blockFirst();

        System.out.println("Resultado do selectedOrderToDelivery: " + orderResponse);

        return orderResponse;
    }

    public OrderResponse confirmDelivery(DeliveryInfo deliveryInfo) {
        String url = restUrl + "order/confirmDelivery";

        OrderResponse orderResponse = WebClient.create(url)
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(deliveryInfo), DeliveryInfo.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(OrderResponse.class)
                .log().blockFirst();

        System.out.println("Resultado do confirmDelivery: " + orderResponse);

        return orderResponse;
    }
}
