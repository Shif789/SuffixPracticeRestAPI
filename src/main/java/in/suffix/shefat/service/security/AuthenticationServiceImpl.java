package in.suffix.shefat.service.security;

import in.suffix.shefat.dao.security.IUserDetailsRepository;
import in.suffix.shefat.model.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private IUserDetailsRepository userDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        UserDetails userDetails = UserDetails.builder()
                .uname(registerRequest.getUname())
                .pwd(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(Role.USER)
                .build();

        UserDetails savedUserDetails = userDetailsRepository.save(userDetails);
        if (savedUserDetails != null) {
            String jwtToken = jwtService.generateToken(savedUserDetails);
            //return new AuthenticationResponse(jwtToken);// both are same
            return AuthenticationResponse.builder().jwtToken(jwtToken).build();
        } else
            throw new UsernameNotFoundException("user not registered with uname: " + userDetails.getUname());
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUname(), authenticationRequest.getPassword()));

        UserDetails userDetails = userDetailsRepository
                .findByUname(authenticationRequest.getUname())
                .orElseThrow(() -> new UsernameNotFoundException("user not found with uname: " + authenticationRequest.getUname()));
        String jwtToken = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder().jwtToken(jwtToken).build();
    }
}
