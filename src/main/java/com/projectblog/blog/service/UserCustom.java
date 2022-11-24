/*Au cas où je veux me login en utilisant des users déjà présents en DB*/

package com.projectblog.blog.service;

import com.projectblog.blog.entities.UserEntity;
import com.projectblog.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserCustom implements UserDetailsService {
@Autowired UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByPseudo(pseudo);
        if(user == null)  {
            throw new UsernameNotFoundException("L'utilisateur n'a pas été trouvé");
        }
        return User.builder().username(user.getPseudo()).password(user.getPassword()).build();
    }
}
