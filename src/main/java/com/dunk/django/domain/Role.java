package com.dunk.django.domain;

import lombok.*;
@Getter
@RequiredArgsConstructor
public enum Role {
        ADMIN("ROLE_ADMIN", "관리자"),
        USER("ROLE_USER", "회원");

        private final String key;
        private final String title;
}