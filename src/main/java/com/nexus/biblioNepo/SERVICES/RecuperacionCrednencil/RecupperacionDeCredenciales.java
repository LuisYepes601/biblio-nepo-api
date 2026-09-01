/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.RecuperacionCrednencil;

import com.nexus.biblioNepo.ENTYTIES.usuario;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.REPOSITORIES.usuarioRepository;
import com.nexus.biblioNepo.SERVICES.Email.EmailService;
import java.util.ArrayList;
import java.util.List;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class RecupperacionDeCredenciales implements IRecuperacionCredenciales {

    private PasswordGenerator passwordGenerator;
    private usuarioRepository userRepo;
    private PasswordEncoder passwordencoder;
    private EmailService emailServi;

    @Autowired

    public RecupperacionDeCredenciales(PasswordGenerator passwordGenerator, usuarioRepository userRepo, PasswordEncoder passwordencoder, EmailService emailServi) {
        this.passwordGenerator = passwordGenerator;
        this.userRepo = userRepo;
        this.passwordencoder = passwordencoder;
        this.emailServi = emailServi;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void recuperarContraseñaByEmail(String email) {

        usuario us = userRepo.findByEmail(email)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El usuario no tiene cuenta en el sistema"));

        CharacterRule characterRule = new CharacterRule(EnglishCharacterData.LowerCase, 1);
        CharacterRule characterRule1 = new CharacterRule(EnglishCharacterData.Alphabetical, 3);
        CharacterRule characterRule2 = new CharacterRule(EnglishCharacterData.UpperCase, 2);
        CharacterRule characterRule3 = new CharacterRule(EnglishCharacterData.Special, 2);

        List<CharacterRule> rules = new ArrayList<>();

        rules.add(characterRule);
        rules.add(characterRule1);
        rules.add(characterRule2);
        rules.add(characterRule3);

        String passGenerated = passwordGenerator.generatePassword(8, rules);

        String pashEncrip = passwordencoder.encode(passGenerated);

        us.setPassword(pashEncrip);

        userRepo.save(us);

        emailServi.RecuperarcontraseñaEmail(us, passGenerated);

    }

}
