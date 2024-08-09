package com.qilin.myRoutes;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class LevelRoutePredicateFactory extends AbstractRoutePredicateFactory<LevelRoutePredicateFactory.Config> {


    public LevelRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("levelname");
    }


    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                List<String> levelname = serverWebExchange.getRequest().getQueryParams().get("levelname");
                if (levelname == null || levelname.isEmpty()) {
                    return false;
                }
                if (levelname.get(0).equals("gold")) {
                    return true;
                }
                return false;
            }


        };
    }


    public static class Config {

        @NotNull
        private String levelname;

        public @NotNull String getLevelname() {
            return levelname;
        }

        public void setLevelname(@NotNull String levelname) {
            this.levelname = levelname;
        }
    }


}
