import com.project.foodies.FoodiesApplication;

import com.project.foodies.FoodiesApplication;
public class userlogin extends FoodiesApplication{

    //create a User model with the necessary attributes
    @Entity
@Table(name = "users")
public class users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

// constructors, getters, and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    private String username;

    public users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    
}
    @Override
    public String toString() {
        return "userlogin []";
    }
}

//create a UserRepository interface
@SpringBootApplication
public interface SpringBootApplication extends SpringBootApplication<User, Long> 
{
    User findByUsername(String username);
    User findByEmail(String email);
}

// create a UserService that handles the authentication logic
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}

 // create a SignInController to handle the sign-in request

@RestController
@RequestMapping("/api/auth")
public class SignInController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public SignInController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) throws AuthenticationException {
        authenticate(signInRequest.getUsername(), signInRequest.getPassword());

        final UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new SignInResponse(token));
    }

    private void authenticate(String username, String password) throws AuthenticationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User disabled", e);
            
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Invalid credentials", e);
        }
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public JwtTokenUtil getJwtTokenUtil() {
        return jwtTokenUtil;
    }

    public UserService getUserService() {
        return userService;
    }
}

