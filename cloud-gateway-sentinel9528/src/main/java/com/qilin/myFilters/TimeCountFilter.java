package com.qilin.myFilters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TimeCountFilter implements GlobalFilter, Ordered {

    private static final String BEGIN_TIME = "beginTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        exchange.getAttributes().put(BEGIN_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startVisitTime = exchange.getAttribute(BEGIN_TIME);
            if (startVisitTime != null) {
                long endVisitTime = System.currentTimeMillis();
                log.info("=================================================");
                log.info("访问接口主机: {}", exchange.getRequest().getURI().getHost());
                log.info("访问接口端口: {}", exchange.getRequest().getURI().getPort());
                log.info("访问接口URL: {}", exchange.getRequest().getURI().getPath());
                log.info("访问接口参数: {}", exchange.getRequest().getURI().getRawQuery());
                log.info("访问接口耗时: {}ms", (endVisitTime - startVisitTime));
                log.info("=================================================");
            }
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
