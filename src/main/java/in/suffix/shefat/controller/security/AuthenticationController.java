package in.suffix.shefat.controller.security;

import in.suffix.shefat.model.security.AuthenticationRequest;
import in.suffix.shefat.model.security.AuthenticationResponse;
import in.suffix.shefat.model.security.RegisterRequest;
import in.suffix.shefat.service.security.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest ){
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);

        return new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest registerRequest){
        AuthenticationResponse authenticationResponse = authenticationService.register(registerRequest);
        return new ResponseEntity<AuthenticationResponse>(authenticationResponse,HttpStatus.OK);
    }
}
