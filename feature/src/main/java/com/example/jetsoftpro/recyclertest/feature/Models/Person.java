package com.example.jetsoftpro.recyclertest.feature.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fname",
        "lname"
})
public class Person {

    @JsonProperty("fname")
    private String firstName;

    @JsonProperty("lname")
    private String lastName;

    @JsonProperty("city")
    private String city;

    @JsonProperty("fname")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("fname")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lname")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lname")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }
}
