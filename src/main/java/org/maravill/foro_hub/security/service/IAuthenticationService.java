package org.maravill.foro_hub.security.service;

import org.maravill.foro_hub.security.dto.RequestAuthentication;
import org.maravill.foro_hub.security.dto.ResponseAuthentication;

public interface IAuthenticationService {
    ResponseAuthentication login(RequestAuthentication requestAuthentication);
}
