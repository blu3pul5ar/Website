package edu.wctc.nap.napmidtermapp.repository;

import edu.wctc.nap.napmidtermapp.model.Animes;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nick Piette
 */
public interface AnimeRepository extends JpaRepository<Animes, Integer>, Serializable {
    @Query("SELECT a FROM Animes a where a.productStudio.studioId = :id")
    public List<Animes> findAllByStudio(@Param("id") Integer id);
}
