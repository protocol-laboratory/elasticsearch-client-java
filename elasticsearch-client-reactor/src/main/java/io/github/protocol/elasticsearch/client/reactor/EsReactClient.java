package io.github.protocol.elasticsearch.client.reactor;

import io.github.protocol.elasticsearch.client.common.ElasticsearchException;
import io.github.protocol.elasticsearch.client.common.MainResp;
import io.github.protocol.elasticsearch.client.common.JacksonService;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class EsReactClient {
    private final ReactHttpClient reactHttpClient;

    public EsReactClient(Configuration conf) {
        this.reactHttpClient = new ReactHttpClient(conf);
    }

    public Mono<MainResp> info() {
        return reactHttpClient.get("/")
                .flatMap(jsonString -> Mono.fromCallable(() -> JacksonService.toObject(jsonString, MainResp.class))
                        .subscribeOn(Schedulers.boundedElastic())
                        .onErrorMap(ElasticsearchException::new));
    }

    public MainResp infoSync() {
        return info().block();
    }
}
