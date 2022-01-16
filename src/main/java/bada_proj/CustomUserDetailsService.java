package bada_proj;

import bada_proj.entities.Klienci;
import bada_proj.entities.Pracownicy;
import bada_proj.repositories.KlienciRepository;
import bada_proj.repositories.PracownicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private KlienciRepository klienciRepository;

    @Autowired
    private PracownicyRepository pracownicyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Klienci klient = klienciRepository.findKlienciByNazwaUzytkownika(username);
        if (klient != null) {
            return new CustomUserDetails(klient.getNazwaUzytkownika(), klient.getHaslo(), "KLIENT");
        } else {
            Pracownicy pracownik = pracownicyRepository.findPracownicyByNazwaUzytkownika(username);
            if (pracownik != null) {
                return new CustomUserDetails(pracownik.getNazwaUzytkownika(), pracownik.getHaslo(), "PRACOWNIK");
            }
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    public class CustomUserDetails implements UserDetails {

        private String username;
        private String password;
        private Collection<? extends GrantedAuthority> authorities;

        public CustomUserDetails() {
            super();
        }

        public CustomUserDetails(String username, String password, String role) {
            this.username = username;
            this.password = password;
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
            this.authorities = grantedAuthorities;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}