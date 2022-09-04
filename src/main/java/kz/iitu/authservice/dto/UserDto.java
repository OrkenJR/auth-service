package kz.iitu.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


/**
 *
 * DTO для юзера, в будущем надо доработать и вынести в либу
 *
 * Пока dummy версия
 *
 * @author Orken
 * @version 1.0.0
 *
 * **/
@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
}
