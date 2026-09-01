/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Categoria;

import com.nexus.biblioNepo.DTOS.request.Categoria.CategoriaAsignarDto;
import com.nexus.biblioNepo.DTOS.response.Categoria.CategoriaDtoresp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface ICategoriaService {

    public PageResponse<CategoriaDtoresp> getAll(String nombre, Pageable pageable);

    public void asignarCategoriaLibro(Long id_libro, List<CategoriaAsignarDto> categorias);

    public void EliminarCategoriaDeLibro(Long id_libro, Integer id_cat,Integer id_cat_lib);
}
