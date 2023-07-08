import com.project.foodies.FoodiesApplication;

public class userlogout extends FoodiesApplication
{
        @RestController
        @RequestMapping("/api/auth")

        public class AuthController 
        {
            @PostMapping("/logout")
            public ResponseEntity<?> logoutUser(HttpServletRequest request, HttpServletResponse response) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                if (authentication != null) {
                    new SecurityContextLogoutHandler().logout(request, response, authentication);
                }

                return ResponseEntity.ok(new ApiResponse(true, "User Signed out successfully"));
                
            }
            @Override
            public String toString() {
                return "AuthController []";
            }
        }
}
