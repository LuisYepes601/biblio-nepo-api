/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.Idiomas;

/**
 *
 * @author luis
 */
public class IdiomaDtoReq {

    private String nativeName;
    
    private String iso639_2;

    public IdiomaDtoReq(String nativeName, String iso639_2) {
        this.nativeName = nativeName;
        this.iso639_2 = iso639_2;
    }

    public IdiomaDtoReq() {
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getIso639_2() {
        return iso639_2;
    }

    public void setIso639_2(String iso639_2) {
        this.iso639_2 = iso639_2;
    }

    
}
