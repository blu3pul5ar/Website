package edu.wctc.nap.napmidtermapp.repository;


import edu.wctc.nap.napmidtermapp.model.Studios;
import edu.wctc.nap.napmidtermapp.model.Users;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jlombardo
 */
public interface UserRepository extends JpaRepository<Users, String>, Serializable {
    
}
