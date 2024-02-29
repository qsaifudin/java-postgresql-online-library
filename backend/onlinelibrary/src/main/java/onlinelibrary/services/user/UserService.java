package onlinelibrary.services.user;

import onlinelibrary.payloads.requests.LoginUserRequest;
import onlinelibrary.payloads.requests.RegistUserRequest;
import onlinelibrary.payloads.responses.Response;

public interface UserService {
    Response registUser(RegistUserRequest request);

    Response loginUser(LoginUserRequest request);

    Response getusers();

    Response getuserById(String uuid);
}
