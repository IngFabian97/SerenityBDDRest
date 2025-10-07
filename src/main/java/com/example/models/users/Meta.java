package com.example.models.users;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

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

    public String getPoweredBy() {
        return poweredBy;
    }

    public void setPoweredBy(String poweredBy) {
        this.poweredBy = poweredBy;
    }

    public String getUpgradeUrl() {
        return upgradeUrl;
    }

    public void setUpgradeUrl(String upgradeUrl) {
        this.upgradeUrl = upgradeUrl;
    }

    public String getDocsUrl() {
        return docsUrl;
    }

    public void setDocsUrl(String docsUrl) {
        this.docsUrl = docsUrl;
    }

    public String getTemplateGallery() {
        return templateGallery;
    }

    public void setTemplateGallery(String templateGallery) {
        this.templateGallery = templateGallery;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public String getUpgradeCta() {
        return upgradeCta;
    }

    public void setUpgradeCta(String upgradeCta) {
        this.upgradeCta = upgradeCta;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
