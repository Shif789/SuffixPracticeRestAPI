package in.suffix.shefat.service.security;

import in.suffix.shefat.model.security.AuthenticationRequest;
import in.suffix.shefat.model.security.AuthenticationResponse;
import in.suffix.shefat.model.security.RegisterRequest;

public interface IAuthenticationService {

    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
