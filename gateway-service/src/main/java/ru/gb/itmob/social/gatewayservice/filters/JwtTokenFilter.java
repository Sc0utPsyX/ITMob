package ru.gb.itmob.social.gatewayservice.filters;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.gb.itmob.social.gatewayservice.services.TokenService;

import java.nio.charset.StandardCharsets;

@Component
public class JwtTokenFilter extends AbstractGatewayFilterFactory<JwtTokenFilter.Config> {

    private final TokenService tokenService;

    @Override
    public GatewayFilter apply(Config config) {

        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

                ServerHttpRequest request = exchange.getRequest();

                if (request.getHeaders().containsKey("authorization")) {

                    if (request.getHeaders().getOrEmpty("authorization").isEmpty()) {
                        return monoError(exchange, "Authorization header of request is empty", HttpStatus.BAD_REQUEST);
                    }

                    String token = request.getHeaders().getOrEmpty("authorization").get(0);

                    if (tokenService.isExpired(token)) {
                        return monoError(exchange, "Token is expired", HttpStatus.BAD_REQUEST);
                    }
                }

                return chain.filter(exchange);
            }
        };
    }

    private Mono<Void> monoError(ServerWebExchange exchange, String msg, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        DataBuffer buffer = response.bufferFactory().wrap(msg.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }

    public static class Config {}

    public JwtTokenFilter(TokenService tokenService) {
        super(Config.class);
        this.tokenService = tokenService;
    }
}
