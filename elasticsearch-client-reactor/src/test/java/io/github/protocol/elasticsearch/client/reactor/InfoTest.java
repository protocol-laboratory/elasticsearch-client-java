package io.github.protocol.elasticsearch.client.reactor;

import io.github.embedded.elasticsearch.core.EmbeddedElasticsearchServer;
import io.github.protocol.elasticsearch.client.api.MainResp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class InfoTest {
    private static final EmbeddedElasticsearchServer SERVER = new EmbeddedElasticsearchServer();

    private static EsReactClient esReactClient;

    @BeforeAll
    public static void setup() throws Exception {
        SERVER.start();
        Configuration conf = new Configuration();
        conf.setPort(SERVER.getPort());
        esReactClient = new EsReactClient(conf);
    }

    @AfterAll
    public static void teardown() throws Exception {
        SERVER.close();
    }

    @Test
    public void testHelloMethod() {
        MainResp mainResp = esReactClient.infoSync();
        Assertions.assertEquals("embedded-elasticsearch-node", mainResp.getName());
        Assertions.assertEquals("embedded-elasticsearch-cluster", mainResp.getClusterName());
    }
}
