package rs.ac.uns.psppaypal.config.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
class SecurityConfiguration(private val authenticationEntryPoint: AuthenticationEntryPoint) :
    WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
            // Sets session management to stateless
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            // Sets unauthorized requests exception handler
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
            // Sets permissions on endpoints
            .authorizeRequests()
            .antMatchers("/payment/**").permitAll()
            .antMatchers("/registration/**").permitAll()
            .anyRequest().authenticated()

        // Enables SSL
        http.requiresChannel().anyRequest().requiresSecure()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
            "/**/*.css", "/**/*.js"
        )
    }
}