/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.UserDetails;

import com.nexus.biblioNepo.ENTYTIES.usuario;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.REPOSITORIES.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author luis
 */
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private usuarioRepository userRepo;

    @Autowired
    public CustomerUserDetailsService(usuarioRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        usuario us = userRepo.findByEmailWithRol(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usario no existe en el sistema userdeatrils"));

        return User.builder()
                .username(us.getEmail())
                .password(us.getPassword())
                .roles(us.getRol().getNombre())
                .build();
    }

}
