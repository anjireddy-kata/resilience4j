package com.techmonks.resilience.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GenericWebClient {
    private final WebClient.Builder webClientBuilder;


    @Autowired
    public GenericWebClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
}

//    public <T> ResponseEntity<GenericResponse<T>> execute(GenericRequest genericRequest, Object requestEntity) throws HttpServerErrorException, WebClientRequestException, WebClientResponseException {
//        Mono<GenericResponse<T>> response = null;
//        AtomicInteger cnt = new AtomicInteger(1);
//
//        try {
//            response = this.webClientBuilder.build().method(genericRequest.getHttpMethod())
//                    .uri(genericRequest.getServiceUrl())
//                    .bodyValue(requestEntity)
//                    .retrieve()
//                    .bodyToMono(new ParameterizedTypeReference<GenericResponse<T>>() {
//                    })
//                    .doOnNext(apiResponse -> logAPIResponse(genericRequest.getServiceUrl(), apiResponse));
//
//        } catch (HttpServerErrorException var6) {
//            this.handleHttpServerErrorException(var6);
//        } catch (WebClientRequestException var7) {
//            if (var7.getCause() instanceof ConnectException) {
//                log.error("Retry method called - {}", cnt.getAndIncrement());
//                log.error("WebClientRequestException Connection Timed Out Exception occurs in GenericWebClient ", var7);
//                throw var7;
//            }
//        } catch (WebClientResponseException var8) {
//            if (var8.getStatusCode().is5xxServerError()) {
//                log.error("WebClientResponseException Connection Timed Out Exception occurs in GenericWebClient ", var8);
//                throw var8;
//            }
//        }
//}
