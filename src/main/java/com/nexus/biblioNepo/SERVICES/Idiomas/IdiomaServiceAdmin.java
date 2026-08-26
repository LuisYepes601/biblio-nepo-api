/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Idiomas;

import com.nexus.biblioNepo.ENTYTIES.Idiom;
import com.nexus.biblioNepo.REPOSITORIES.idiomRepository;
import com.nexus.biblioNepo.SERVICES.RestcountriesDev.RestcountriesDev;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class IdiomaServiceAdmin implements IIdiomasAdminService {

    private idiomRepository idiomRepo;
    private RestcountriesDev countriesDev;

    @Autowired
    public IdiomaServiceAdmin(idiomRepository idiomRepo, RestcountriesDev countriesDev) {
        this.idiomRepo = idiomRepo;
        this.countriesDev = countriesDev;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cargarIdiomas() {

        List<Idiom> response = countriesDev.getIdioms()
                .stream()
                .filter((id) -> id.getNativeName() != null)
                .map((id) -> {

                    Idiom idiom = new Idiom();

                    idiom.setNombre(id.getNativeName());

                    if (id.getIso639_2() != null) {
                        idiom.setCodigo(id.getIso639_2());
                    } else {

                        idiom.setCodigo("NO_APLICA");
                    }

                    AuditableUtils.create(idiom, "prueba", "prueba");
                    return idiom;
                }).toList();

        idiomRepo.saveAll(response);

    }

}
