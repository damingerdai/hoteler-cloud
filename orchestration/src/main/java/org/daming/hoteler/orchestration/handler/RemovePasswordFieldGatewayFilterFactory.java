package org.daming.hoteler.orchestration.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Component
public class RemovePasswordFieldGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            // 装饰响应对象
            ServerHttpResponse originalResponse = exchange.getResponse();
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();

            ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                @Override
                public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                    if (body instanceof Flux<? extends DataBuffer> fluxBody) {
                        return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                            // 合并所有缓冲区数据
                            DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                            DataBuffer join = dataBufferFactory.join(dataBuffers);
                            byte[] content = new byte[join.readableByteCount()];
                            join.read(content);

                            // 释放缓冲区
                            DataBufferUtils.release(join);

                            // 解析JSON并删除password字段
                            String responseBody = new String(content, StandardCharsets.UTF_8);
                            try {
                                var responseMap = objectMapper.readValue(responseBody, HashMap.class);
                                responseMap.remove("password");
                                responseBody = objectMapper.writeValueAsString(responseMap);
                            } catch (Exception e) {
                                // JSON解析失败，返回原始响应
                                return bufferFactory.wrap(content);
                            }

                            // 返回处理后的响应
                            return bufferFactory.wrap(responseBody.getBytes(StandardCharsets.UTF_8));
                        }));
                    }
                    return super.writeWith(body);
                }
            };

            // 使用装饰后的响应继续处理
            return chain.filter(exchange.mutate().response(decoratedResponse).build());
        };
    }
}
