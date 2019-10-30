package academy.devdojo.security.token.filter;

import academy.devdojo.core.property.JwtConfiguration;
import academy.devdojo.security.token.TokenConverter.TokenConverter;
import academy.devdojo.security.util.SecurityContextUtil;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {
    protected final JwtConfiguration jwtConfiguration;
    protected final TokenConverter tokenConverter;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(jwtConfiguration.getHeader().getName());

        if(header == null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = header.replace(jwtConfiguration.getHeader().getPrefix(), "").trim();

        SecurityContextUtil.setSecurityContext(StringUtils.equalsIgnoreCase("signed", jwtConfiguration.getType()) ? validate(token) : decryptValidating(token));
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @SneakyThrows
    private SignedJWT decryptValidating(String encryptedToken){
        String signedToken = tokenConverter.decryptToken(encryptedToken);
        tokenConverter.validateTokenSignature(signedToken);

        return SignedJWT.parse(signedToken);
    }

    @SneakyThrows
    private SignedJWT validate(String signedToken) {
        tokenConverter.validateTokenSignature(signedToken);

        return SignedJWT.parse(signedToken);
    }
}
