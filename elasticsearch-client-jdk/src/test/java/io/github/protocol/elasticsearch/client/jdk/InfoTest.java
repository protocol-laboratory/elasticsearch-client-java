package io.github.protocol.elasticsearch.client.jdk;

import io.github.embedded.elasticsearch.core.EmbeddedElasticsearchServer;
import io.github.protocol.elasticsearch.client.api.MainResp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class InfoTest {
    private static final EmbeddedElasticsearchServer SERVER = new EmbeddedElasticsearchServer();

    private static EsJavaClient esJavaClient;

    @BeforeAll
    public static void setup() throws Exception {
        SERVER.start();
        Configuration conf = new Configuration();
        conf.setPort(SERVER.getPort());
        esJavaClient = new EsJavaClient(conf);
    }

    @AfterAll
    public static void teardown() throws Exception {
        SERVER.close();
    }

    @Test
    public void testHelloMethod() throws Exception {
        MainResp mainResp = esJavaClient.infoSync();
        Assertions.assertEquals("embedded-elasticsearch-node", mainResp.getName());
        Assertions.assertEquals("embedded-elasticsearch-cluster", mainResp.getClusterName());
    }
}
