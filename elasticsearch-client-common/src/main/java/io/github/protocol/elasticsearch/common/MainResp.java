package io.github.protocol.elasticsearch.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MainResp {
    private String name;

    @JsonProperty("cluster_name")
    private String clusterName;
}
