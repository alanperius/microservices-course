package academy.devdojo.auth.endpoint.controller;


import academy.devdojo.core.model.ApplicationUser;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.security.Principal;

@RestController
@RequestMapping("user")
public class UserInfoController {
    @GetMapping(path = "info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ApplicationUser> getUserInfo(Principal principal){
        ApplicationUser applicationUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        return new ResponseEntity<>(applicationUser, HttpStatus.OK);


    }
}
