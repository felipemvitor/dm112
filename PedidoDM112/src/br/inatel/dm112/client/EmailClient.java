package br.inatel.dm112.client;

import br.inatel.dm112.model.MailRequestData;
import br.inatel.dm112.model.MailStatusResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.FileInputStream;
import java.io.IOException;

public class EmailClient {

    // local
    private String restURL = "http://localhost:8080/UtilityDM112/api/";

    //TODO: modificar este email para enviar para outro endereço
    private static String sendTo = "rrocha.roberto@gmail.com";

    public MailStatusResponse callSendMailService(String from, String password, String to, String message) {
        MailRequestData mrd = new MailRequestData(from, password, to, message);
        return callMailService(mrd);
    }

    private MailStatusResponse callMailService(MailRequestData mrd) {
        String url = restURL + "sendMail";
        return WebClient
                .create(url)
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(mrd), MailRequestData.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(MailStatusResponse.class)
                .log()
                .blockFirst();
    }

    public static void main(String[] args) {
            EmailClient client = new EmailClient();

            MailStatusResponse result = client.callSendMailService(
                    "robertorr9@gmail.com", "robertodm112", sendTo, "E-mail enviado com sucesso!");

            System.out.println("Resposta do email: " + result.getStatus());
    }
}

