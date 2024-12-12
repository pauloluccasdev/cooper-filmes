package com.cooper.filme.main.dto.base;

import com.cooper.filme.main.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

        private Long id;

        private String name;

        private String email;

        private User.Role role;

        private LocalDateTime createdAt;
}
