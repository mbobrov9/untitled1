package com.projekt.pluk.repos;
import com.projekt.pluk.models.punishent;
import org.springframework.data.repository.CrudRepository;
public interface punishment_repo extends CrudRepository<punishent, Long>{
    punishent findByScore(int score);
}
