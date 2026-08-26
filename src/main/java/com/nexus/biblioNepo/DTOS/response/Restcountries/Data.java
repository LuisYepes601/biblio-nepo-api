/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Restcountries;

import java.util.List;

/**
 *
 * @author luis
 */
public class Data {

    private List<Objects> objects;

    public Data(List<Objects> objects) {
        this.objects = objects;
    }

    public Data() {
    }

    public List<Objects> getObjects() {
        return objects;
    }

    public void setObjects(List<Objects> objects) {
        this.objects = objects;
    }
    
    
}
