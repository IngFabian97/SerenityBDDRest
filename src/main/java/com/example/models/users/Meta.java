package com.example.models.users;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meta {
    
    @JsonProperty("powered_by")
    private String poweredBy;
    
    @JsonProperty("upgrade_url")
    private String upgradeUrl;
    
    @JsonProperty("docs_url")
    private String docsUrl;
    
    @JsonProperty("template_gallery")
    private String templateGallery;
    
    private String message;
    
    private List<String> features;
    
    @JsonProperty("upgrade_cta")
    private String upgradeCta;
    
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
