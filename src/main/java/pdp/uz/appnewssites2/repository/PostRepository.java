package pdp.uz.appnewssites2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appnewssites2.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
    boolean existsByTitle(String title);

    boolean existsByUrlAndIdNot(String url, Long id);
}
