package in.suffix.shefat.dao.security;

import in.suffix.shefat.model.security.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserDetailsRepository extends JpaRepository<UserDetails, Integer> {
    Optional<UserDetails> findByUname(String uname);
}
