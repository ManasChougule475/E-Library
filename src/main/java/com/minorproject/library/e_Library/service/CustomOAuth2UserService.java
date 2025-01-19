package com.minorproject.library.e_Library.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        // Fetching user info from GitHub after successful login
        OAuth2User oAuth2User = super.loadUser(userRequest); // oAuth2User will get user info after sign up/sign in , developer need to write additional logic to authenticate user (to create token)

        // Custom attributes fetched from GitHub response
        Map<String, Object> attributes = oAuth2User.getAttributes(); // attributes contains user information returned by the OAuth2 provider (in this case, GitHub).

        // Returning the user with a custom role and attributes
        return new DefaultOAuth2User(
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER")), // Assigning the ROLE_MEMBER to all users
                attributes,
                "login");  // login corresponds to the user's GitHub username.
    }
}
