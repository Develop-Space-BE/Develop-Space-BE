package developspace.com.developspace.question.repository.JpaAndQueryDSLImpl.JPA;

import developspace.com.developspace.question.entity.Bookmark;
import developspace.com.developspace.question.entity.BookmarkCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPABookmarkRepository extends JpaRepository<Bookmark, Long> {
    Bookmark save(Bookmark bookmark);
    void deleteById(BookmarkCompositeKey compositeKey);
    Boolean existsById(BookmarkCompositeKey compositeKey);
}
