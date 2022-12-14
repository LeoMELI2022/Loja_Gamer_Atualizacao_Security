package com.generation.loja_games.security;

import java.util.Optional;

import com.generation.loja_games.model.Usuario;
import com.generation.loja_games.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);

        if (usuario.isPresent())
            return new com.generation.loja_games.security.UserDetailsImpl(usuario.get());

        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    }
}