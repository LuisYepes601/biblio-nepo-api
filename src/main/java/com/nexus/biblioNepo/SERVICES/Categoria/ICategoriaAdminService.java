/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Categoria;

import com.nexus.biblioNepo.DTOS.request.Categoria.CategoriaDtoReq;
import com.nexus.biblioNepo.ENTYTIES.categoryBoock;

/**
 *
 * @author luis
 */
public interface ICategoriaAdminService {
    
    public categoryBoock create(CategoriaDtoReq categoriaDtoReq);
    
    public categoryBoock updateById(Integer id, CategoriaDtoReq categoriaDtoReq);
    
    public categoryBoock deleteByID(Integer id);
}
