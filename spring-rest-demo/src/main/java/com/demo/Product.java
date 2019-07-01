package com.demo;

import com.demo.error.validator.Author;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Please provide a product name")
    private String name;

    @Author
    @NotEmpty(message = "Please provide a description")
    private String desc;

    @NotNull(message = "Please provide a price")
    @DecimalMin("0.00")
    private BigDecimal price;


    public Product() {
    }

    public Product(Long id, String name, String desc, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.desc = desc;
    }

    public Product(String name, String desc, BigDecimal price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id +
                ", product name='" + name + '\'' +
                ", description='" + desc + '\'' +
                ", price='" + price +
                '}';
    }
}
