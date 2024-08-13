package com.qilin.myFilters;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AddRequestHeaderGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.style.ToStringCreator;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


@Component
public class UserInfoGatewayFilterFactory extends AbstractGatewayFilterFactory<UserInfoGatewayFilterFactory.NameValueConfig> {

    public UserInfoGatewayFilterFactory() {
        super(UserInfoGatewayFilterFactory.NameValueConfig.class);
    }

    @Override
    public GatewayFilter apply(UserInfoGatewayFilterFactory.NameValueConfig config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String name = config.getName();
                String value = config.getValue();

                exchange.getAttributes().put(config.getName(), config.getValue());
                return chain.filter(exchange);
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(GatewayFilter.NAME_KEY, GatewayFilter.VALUE_KEY);
    }

    @Validated
    public static class NameValueConfig {

        @NotEmpty
        protected String name;

        @NotEmpty
        protected String value;

        public @NotEmpty String getName() {
            return name;
        }

        public void setName(@NotEmpty String name) {
            this.name = name;
        }

        public @NotEmpty String getValue() {
            return value;
        }

        public void setValue(@NotEmpty String value) {
            this.value = value;
        }
    }
}
