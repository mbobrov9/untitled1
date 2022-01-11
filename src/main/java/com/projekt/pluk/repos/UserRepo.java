package com.projekt.pluk.repos;
import com.projekt.pluk.models.Role;
import com.projekt.pluk.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByType(String type);


}
