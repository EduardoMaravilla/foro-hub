package org.maravill.foro_hub.security.config.filters;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.security.models.Operation;
import org.maravill.foro_hub.security.service.IOperationService;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final IOperationService operationService;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestContext) {

        HttpServletRequest request = requestContext.getRequest();
        String url = extractUrlFromRequest(request);
        String httpMethod = request.getMethod();
        if (isPublicEndpoint(url, httpMethod)) {
            return new AuthorizationDecision(true);
        }else{
            return new AuthorizationDecision(isGrantedPermission(authentication.get(), url, httpMethod));
        }
    }

    private boolean isGrantedPermission(Authentication authentication, String url, String httpMethod) {
        return authentication.getAuthorities().stream().anyMatch(grantedAuthority -> {
            String permission = grantedAuthority.getAuthority();
            return isAuthorize(permission,url,httpMethod);
        });
    }

    private boolean isAuthorize(String permission, String url, String httpMethod) {
        if (!permission.contains(":")) return false;
        int sep = permission.indexOf(":");
        String method = permission.substring(0, sep);
        String pathPattern = permission.substring(sep + 1);
        Pattern pattern = Pattern.compile(pathPattern);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches() && method.equals(httpMethod);
    }

    private boolean isPublicEndpoint(String url, String httpMethod) {
        List<Operation> publicOperations = operationService.getPublicOperations();
        return publicOperations.stream().anyMatch(getOperationPredicate(url, httpMethod));
    }

    private Predicate<? super Operation> getOperationPredicate(String url, String httpMethod) {
        return operation -> {
            String basePath = operation.getModule().getBasePath();
            String path = operation.getPath();
            if (basePath == null) basePath = "";
            if (path == null) path = "";
            String urlPath = basePath.concat(path);
            Pattern pattern = Pattern.compile(urlPath);
            Matcher matcher = pattern.matcher(url);
            return matcher.matches() && operation.getHttpMethod().name().equals(httpMethod.toUpperCase());
        };
    }

    private String extractUrlFromRequest(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        return requestURI.replace(contextPath,"");
    }
}
