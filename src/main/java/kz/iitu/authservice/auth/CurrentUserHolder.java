package kz.iitu.authservice.auth;

import kz.iitu.authservice.dto.Token;

// todo Orken чтобы получить текущего пользователя, будешь сюда обращаться
public class CurrentUserHolder {

    // todo Orken Вместо Token используешь свой класс
    public static final ThreadLocal<Token> currentToken = new ThreadLocal<>();

}
