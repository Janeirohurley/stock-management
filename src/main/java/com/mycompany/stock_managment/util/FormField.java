/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stock_managment.util;

import java.util.List;

import com.mycompany.stock_managment.model.Product;

/**
 *
 * @author HP
 */
public class FormField {
    private  String label;
    private  String type;
    private  String placeholder;
    private  String name;
    private  boolean required;
    private List<Product> options; 

    public FormField(String label, String type, String placeholder, String name, boolean required) {
        this.label = label;
        this.type = type;
        this.placeholder = placeholder;
        this.name = name;
        this.required = required;
    }
    public FormField(String label, String name, boolean required, List<Product> options) {
        this.label = label;
        this.type = "select"; // Type par défaut : select
        this.name = name;
        this.required = required;
        this.options = options;
    }
    

    public String getLabel() { return label; }
    public String getType() { return type; }
    public String getPlaceholder() { return placeholder; }
    public String getName() { return name; }
    public boolean isRequired() { return required; }
    public List<Product> getOptions() { return options; }
}
