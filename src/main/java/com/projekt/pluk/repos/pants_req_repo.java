package com.projekt.pluk.repos;

import com.projekt.pluk.models.User;
import com.projekt.pluk.models.pants_req;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface pants_req_repo extends CrudRepository<pants_req, Long> {

    List<pants_req> findByDecision(String decision) ;
    List<pants_req> findByAuthor(User author) ;

}