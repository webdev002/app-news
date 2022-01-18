package pdp.uz.appnewssites2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appnewssites2.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    boolean existsByTextAndIdNot(String text, Long id);
}
