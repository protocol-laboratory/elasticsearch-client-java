package io.github.protocol.elasticsearch.react;

import io.github.protocol.elasticsearch.common.ElasticsearchException;
import io.github.protocol.elasticsearch.common.MainResp;
import io.github.protocol.elasticsearch.common.JacksonService;
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
