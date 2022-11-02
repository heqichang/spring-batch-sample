package com.heqichang.batchquickstart.service;

import com.heqichang.batchquickstart.entity.Admin;
import com.heqichang.batchquickstart.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {


    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminMapper.getByName(username);
        if (null == admin) {
            throw new UsernameNotFoundException("没找到用户");
        }
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
//        return new User("lucy", new BCryptPasswordEncoder().encode("123"), authorities);

        // 如果数据库里存的 pass 数据已加密，这里就不用 new BCryptPasswordEncoder()
        return new User(admin.getName(), new BCryptPasswordEncoder().encode(admin.getPass()), authorities);
    }
}
