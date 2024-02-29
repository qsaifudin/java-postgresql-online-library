package onlinelibrary.services.user;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import onlinelibrary.models.User;
import onlinelibrary.payloads.requests.LoginUserRequest;
import onlinelibrary.payloads.requests.RegistUserRequest;
import onlinelibrary.payloads.responses.Response;
import onlinelibrary.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Response registUser(RegistUserRequest request) {
        try {
            // Convert email to lowercase
            String email = request.getEmail().toLowerCase();

            // Check if email is in valid format and domain
            String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            if (!Pattern.matches(emailPattern, email)) {
                return new Response(HttpStatus.OK.value(), "Invalid email format or domain", null,false);
            }

            // Check if password meets criteria
            String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
            if (!Pattern.matches(passwordPattern, request.getPassword())) {
                return new Response(HttpStatus.OK.value(),
                        "Password must be 8 characters alphanumeric with at least one uppercase letter", null,false);
            }

            // Check if email is already registered
            if (userRepository.existsByEmail(email)) {
                return new Response(HttpStatus.OK.value(), "Email already registered", null,false);
            }

            // Create a new User entity
            User user = new User();
            user.setNama(request.getNama());
            user.setEmail(email); // Set lowercase email
            user.setPassword(request.getPassword());

            // Save the user entity using the repository
            user = userRepository.save(user);

            // Create and return a success response
            Response response = new Response();
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("User registered successfully!");
            response.setData(user);
            response.setSuccess(true);
            return response;
        } catch (Exception e) {
            // If an error occurs, return an error response
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to register user", null,false);
        }
    }

    @Override
    public Response loginUser(LoginUserRequest request) {
        // Retrieve the user by email from the repository
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        // Check if the user exists
        if (user == null) {
            // If user is not found, return response indicating email or password is
            // incorrect
            return new Response(HttpStatus.OK.value(), "Email not found", null,false);
        }

        // Check if the password matches
        if (!user.getPassword().equals(request.getPassword())) {
            return new Response(HttpStatus.OK.value(), "Incorrect password", null,false);
        }

        // Determine user role based on email
        String role = "user";
        if (user.getEmail().equals("admin@gmail.com")) {
            role = "admin";
        }

        // Create and return a success response with user details and role
        Response response = new Response();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Login successful");
        response.setData(user);
        response.setSuccess(true);
        response.addAdditionalData("role", role);
        return response;
    }

    @Override
    public Response getusers() {
        // Retrieve all users from the repository
        List<User> users = userRepository.findAll();

        // Create a response with the list of users
        Response response = new Response(HttpStatus.OK.value(), "success", users,true);
        return response;
    }

    @Override
    public Response getuserById(String uuid) {
        // Retrieve a user by ID from the repository
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        // Create a response with the found user
        Response response = new Response(HttpStatus.OK.value(), "success", user,true);
        return response;
    }
}
