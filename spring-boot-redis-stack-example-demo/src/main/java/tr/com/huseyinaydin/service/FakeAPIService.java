package tr.com.huseyinaydin.service;

import io.github.dengliming.redismodule.redisjson.RedisJSON;
import io.github.dengliming.redismodule.redisjson.args.GetArgs;
import io.github.dengliming.redismodule.redisjson.args.SetArgs;
import io.github.dengliming.redismodule.redisjson.utils.GsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tr.com.huseyinaydin.data.Address;
import tr.com.huseyinaydin.data.Product;
import tr.com.huseyinaydin.data.User;
import tr.com.huseyinaydin.data.UserListResp;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Slf4j
@RequiredArgsConstructor
@Service
public class FakeAPIService {

    private final RestTemplate restTemplate;
    private final RedisJSON redisJSON;

    public static final String PRODUCT_API_URL = "https://fakestoreapi.com/products/1";
    public static final String PRODUCT_KEY = "PRODUCT";
    public static final String USERS_API_URL = "https://fakestoreapi.com/users";
    public static final String USERS_KEY = "USERS";
    public static final String USER_API_URL = "https://fakestoreapi.com/users/1";
    public static final String USER_KEY = "USER";


    public static HttpEntity<String> httpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(null, httpHeaders);
    }

    public void callingToProductAPI() {
        ResponseEntity<Product> product = restTemplate.exchange(
                PRODUCT_API_URL, HttpMethod.GET, httpEntity(), Product.class);
        log.info("Fetch product {}", product.getBody());
        this.savingProductToRedis(product.getBody());
    }

    public void savingProductToRedis(Product product) {
        redisJSON.set(PRODUCT_KEY, SetArgs.Builder.create(".", GsonUtils.toJson(product)));
    }

    public void callingToUsersAPI() {
        ResponseEntity<User[]> user = restTemplate.exchange(
                USERS_API_URL, HttpMethod.GET, httpEntity(), User[].class);
        List<User> users = Arrays.asList(Objects.requireNonNull(user.getBody()));
        log.info("Fetch users {}", users);

        UserListResp userList = new UserListResp();
        userList.setUsers(users);
        this.savingUsersToRedis(userList);
    }

    public void savingUsersToRedis(UserListResp users) {
        redisJSON.set(USERS_KEY, SetArgs.Builder.create(".", GsonUtils.toJson(users)));
    }

    public void callingToUserAPI() {
        ResponseEntity<User> user = restTemplate.exchange(
                USER_API_URL, HttpMethod.GET, httpEntity(), User.class);
        log.info("Fetch user {}", user.getBody());
        this.savingUserToRedis(user.getBody());
    }

    public void savingUserToRedis(User user) {
        redisJSON.set(USER_KEY, SetArgs.Builder.create(".", GsonUtils.toJson(user)));
    }

    public Address getUser() {
        return redisJSON.get(USER_KEY, Address.class, new GetArgs().path(".address"));
    }

    public List<User> getUsers() {
        UserListResp userListResp = redisJSON.get(USERS_KEY, UserListResp.class, new GetArgs().path("."));
        return userListResp.getUsers();
    }
}
