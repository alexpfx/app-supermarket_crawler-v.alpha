package br.com.alexpfx.supermarket.crawler.jaunt;

import com.jaunt.UserAgent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by alexandre on 27/12/2015.
 */
@Component
public class UserAgentFactory {
    public UserAgent createUserAgent() {
        UserAgent u = new UserAgent();
        configure(u);
        return u;
    }

    private void configure(UserAgent u) {
        u.settings.charset = "ISO8859_1";
    }

}
