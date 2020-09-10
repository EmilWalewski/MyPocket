package com.mypocket.security.userConfiguration;

import com.mypocket.storeManagement.storeUtilities.MySqlStoreUtilities;
import com.mypocket.storeManagement.storeUtilities.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {


    @Autowired
    UserStore userStore;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return new PrincipalDetails(userStore.findUserByUserName(s).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password")));
    }
}
