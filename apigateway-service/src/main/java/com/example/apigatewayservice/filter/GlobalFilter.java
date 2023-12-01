package com.example.apigatewayservice.filter;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Setter
@Getter
@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {


    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info(">>>>>> Gloabl Filter Base Message :  {}", config.getBaseMessage());

            if (config.isPreLogger()) {
                log.info(">>>>>> Gloabl Filter Start:  {}", request.getId());
            }

            return chain.
                    filter(exchange).then(Mono.fromRunnable((Runnable) () -> {
                        if (config.isPostLogger()) {
                            log.info(">>>>>> Gloabl Filter end:  {}", response.getStatusCode());
                        }
                    }));
        };
    }

    @Setter
    @Getter
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;

    }
}
