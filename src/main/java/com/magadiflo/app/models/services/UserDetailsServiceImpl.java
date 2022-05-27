package com.magadiflo.app.models.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Recordar que si usamos conexión a BD debemos usar la anotación @Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Aquí se accedería a UserDao para buscar el usuario y obtener su clave y roles

        //En este ejemplo tenemos datos hardcoded
        if ("user".equals(username)) {
            return this.userBuilder(username, new BCryptPasswordEncoder().encode("12345"), "USER");
        } else if ("manager".equals(username)) {
            return this.userBuilder(username, new BCryptPasswordEncoder().encode("12345"), "MANAGER");
        } else if ("admin".equals(username)) {
            return this.userBuilder(username, new BCryptPasswordEncoder().encode("12345"), "ADMIN", "MANAGER", "USER");
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

    private User userBuilder(String username, String password, String... roles) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role)));
        }
        return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
