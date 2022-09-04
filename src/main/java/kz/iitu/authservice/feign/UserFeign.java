package kz.iitu.authservice.feign;

import kz.iitu.authservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * Feign через который можно делать запросы в user-management сервис
 *
 * Надо вынести в отдельную либу вместе с ДТО шками
 *
 * @author Orken
 * @version 1.0.0
 *
 * **/

@FeignClient(value = "user-management", path = "/userApi")
@Service
public interface UserFeign {

    @GetMapping(value = "/byUsername")
    ResponseEntity<UserDto> byUsername(@RequestParam String username);
}
