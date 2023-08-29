# spring-boot-security-3.0

Not : Spring Boot 3.1.x sürümünü kullanıyorsanız lütfen aşağıdaki kod değişikliğini yapın.

```
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/product-service/welcome", "/product-service/addNewUser").permitAll()
                                .requestMatchers("/product-service/**")
                                .authenticated()
                )
                .httpBasic(Customizer.withDefaults()).build();
    }
```