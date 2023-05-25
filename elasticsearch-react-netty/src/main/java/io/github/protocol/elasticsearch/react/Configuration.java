package io.github.protocol.elasticsearch.react;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class Configuration {
    private String host = "localhost";

    private int port;

    public boolean useSsl = false;

    public String keyStorePath;

    @ToString.Exclude
    public String keyStorePassword;

    public String trustStorePath;

    @ToString.Exclude
    public String trustStorePassword;

    public boolean disableSslVerify;

    public boolean disableHostnameVerification;

    public String[] tlsProtocols;

    public String[] tlsCiphers;

    public Configuration() {
    }
}
