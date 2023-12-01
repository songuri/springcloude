package com.example.apigatewayservice.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Setter @Getter
@Slf4j
@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {


    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //exchange 객체 : Server Request와 Server Response 사용할 수 있도록 도와주는 객체
        //GatewayFilterChain 객체 : 다양한 필터(pre, post 필터 등)들을 연결시켜서 작업해줄 수 있도록 함.
        GatewayFilter filter = new OrderedGatewayFilter(new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();
                ServerHttpResponse response = exchange.getResponse();

                log.info(">>>>>> Logger Filter Base Message :  {}", config.getBaseMessage());

                if (config.isPreLogger()) {
                    log.info(">>>>>> Logger Filter Start:  {}", request.getId());
                }
                return chain.
                        filter(exchange).then(Mono.fromRunnable((Runnable) () -> {
                            if (config.isPostLogger()) {
                                log.info(">>>>>> Logger Filter end:  {}", response.getStatusCode());
                            }
                        }));
            }
        }, Ordered.LOWEST_PRECEDENCE);
        // Ordered.HIGHEST_PRECEDENCE 이걸 쓰면 필터의 우선순위가 가장 높아서 제일 먼저 적용됨.




        return filter;
    }

    @Getter@Setter
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}