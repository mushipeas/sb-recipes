package com.mushipeas.springbootrecipes.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RECIPE")
public class Recipe {
    
    @Id
    @GeneratedValue()
    @Column(name="ID")
    private Integer id;
    private String url;
    private String title;
    private Integer total_time_minutes;
    private String yields;
    private String ingredients;
    private String instructions;
    private String img_url;
    private String desc;

    public Recipe(){
        super();
    }

    public Recipe(Integer id, String url, String title, Integer total_time_minutes, String yields, String ingredients, String instructions, String img_url, String desc) {     
        super();
        this.id = id;
        this.url = url;
        this.title = title;
        this.total_time_minutes = total_time_minutes;
        this.yields = yields;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.img_url = img_url;
        this.desc = desc;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal_time_minutes() {
        return this.total_time_minutes;
    }

    public void setTotal_time_minutes(int total_time_minutes) {
        this.total_time_minutes = total_time_minutes;
    }

    public String getYields() {
        return this.yields;
    }

    public void setYields(String yields) {
        this.yields = yields;
    }

    public String getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImg_url() {
        return this.img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", url='" + getUrl() + "'" +
            ", title='" + getTitle() + "'" +
            ", total_time_minutes='" + getTotal_time_minutes() + "'" +
            ", yields='" + getYields() + "'" +
            ", ingredients='" + getIngredients() + "'" +
            ", instructions='" + getInstructions() + "'" +
            ", img_url='" + getImg_url() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
    
}