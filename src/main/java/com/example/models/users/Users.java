package com.example.models.users;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class Users {
    private Integer page;
    
    @JsonProperty("per_page")
    private Integer perPage;
    
    private Integer total;
    
    @JsonProperty("total_pages")
    private Integer totalPages;
    
    private List<Datum> data;
    
    private Support support;
    
    @JsonProperty("_meta")
    private Meta meta;
    
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public Integer getPage() {
    return page;
    }

    public void setPage(Integer page) {
    this.page = page;
    }

    public Integer getPerPage() {
    return perPage;
    }

    public void setPerPage(Integer perPage) {
    this.perPage = perPage;
    }

    public Integer getTotal() {
    return total;
    }

    public void setTotal(Integer total) {
    this.total = total;
    }

    public Integer getTotalPages() {
    return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
    }

    public List<Datum> getData() {
    return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
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
