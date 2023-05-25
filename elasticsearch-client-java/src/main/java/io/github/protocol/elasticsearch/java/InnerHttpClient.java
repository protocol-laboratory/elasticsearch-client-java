package io.github.protocol.elasticsearch.java;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class InnerHttpClient {
    private final Configuration conf;

    private final HttpClient client;

    private final String httpPrefix;

    public InnerHttpClient(Configuration conf) {
        this.conf = conf;
        HttpClient.Builder builder = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1);
        if (conf.isUseSsl()) {
            builder = builder
                    .sslContext(SslContextUtil.buildFromJks(conf.keyStorePath, conf.keyStorePassword,
                            conf.trustStorePath, conf.trustStorePassword, conf.disableSslVerify,
                            conf.tlsProtocols, conf.tlsCiphers));
            this.httpPrefix = "https://" + conf.getHost() + ":" + conf.getPort();
        } else {
            this.httpPrefix = "http://" + conf.getHost() + ":" + conf.getPort();
        }
        this.client = builder.build();
    }

    public HttpResponse<String> get(String url)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(getUri(url))
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> post(String url, String body)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(getUri(url))
                .POST(HttpRequest.BodyPublishers.ofString(body == null ? "" : body))
                .setHeader("Content-Type", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> put(String url, String body)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(getUri(url))
                .PUT(HttpRequest.BodyPublishers.ofString(body == null ? "" : body))
                .setHeader("Content-Type", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> delete(String url)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(getUri(url))
                .DELETE()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private URI getUri(String url) {
        return URI.create(this.httpPrefix + url);
    }
}
