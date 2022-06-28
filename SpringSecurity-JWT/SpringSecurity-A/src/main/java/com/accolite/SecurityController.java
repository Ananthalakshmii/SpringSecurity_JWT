package com.accolite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@GetMapping("/hello")
	public String Hello() {
		return "Hello, Welcome";
	}
	
	@PostMapping("/authenticate") //submitting data and getting token as response
	public ResponseEntity<?> createJWTToken(@RequestBody JwtRequest jreq) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jreq.getUserName(), jreq.getPassword()));
		}catch (BadCredentialsException e) {
			throw new Exception("Incorrect username and password",e);
		}
		
		final UserDetails userDetails=userDetailsService.loadUserByUsername(jreq.getUserName());
		final String jwt=jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(jwt));
	}

}
