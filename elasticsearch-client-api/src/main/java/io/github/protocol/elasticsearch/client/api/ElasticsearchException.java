package io.github.protocol.elasticsearch.client.api;

public class ElasticsearchException extends Exception {
    private static final int DEFAULT_STATUS_CODE = 500;

    private final int statusCode;

    public ElasticsearchException(Throwable t) {
        super(t);
        statusCode = DEFAULT_STATUS_CODE;
    }

    public ElasticsearchException(String message) {
        this(message, DEFAULT_STATUS_CODE);
    }

    public ElasticsearchException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
