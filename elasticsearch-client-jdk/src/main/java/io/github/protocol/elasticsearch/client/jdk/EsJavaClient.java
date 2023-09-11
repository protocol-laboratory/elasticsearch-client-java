package io.github.protocol.elasticsearch.client.jdk;

import io.github.protocol.elasticsearch.client.common.ElasticsearchException;
import io.github.protocol.elasticsearch.client.common.JacksonService;
import io.github.protocol.elasticsearch.client.common.MainResp;

import java.io.IOException;
import java.net.http.HttpResponse;

public class EsJavaClient {
    private final InnerHttpClient innerHttpClient;

    public EsJavaClient(Configuration conf) {
        this.innerHttpClient = new InnerHttpClient(conf);
    }

    public MainResp infoSync() throws ElasticsearchException {
        try {
            HttpResponse<String> response = innerHttpClient.get("/");
            if (response.statusCode() != 200) {
                throw new ElasticsearchException(String.format("Unexpected status code %d body %s",
                        response.statusCode(), response.body()));
            }
            return JacksonService.toObject(response.body(), MainResp.class);
        } catch (IOException | InterruptedException e) {
            throw new ElasticsearchException(e);
        }
    }
}
