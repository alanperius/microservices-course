package academy.devdojo.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class AuthApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        System.out.println(new BCryptPasswordEncoder().encode("devdojo"));
    }
    //$2a$10$eIXUk9eYOTQfV6GLd8NfbO/xEgfIRYyvZCiz.FFfBh4FKFP57OUq2
}
