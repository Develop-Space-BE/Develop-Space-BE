package developspace.com.developspace.question.repository.JpaAndQueryDSLImpl;

import developspace.com.developspace.question.entity.Bookmark;
import developspace.com.developspace.question.entity.BookmarkCompositeKey;
import developspace.com.developspace.question.repository.BookmarkRepository;
import developspace.com.developspace.question.repository.JpaAndQueryDSLImpl.JPA.JPABookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository {
    private final JPABookmarkRepository jpaBookmarkRepository;

    @Override
    public Bookmark save(Bookmark bookmark) {
        return jpaBookmarkRepository.save(bookmark);
    }

    @Override
    public void deleteById(BookmarkCompositeKey compositeKey) {
        jpaBookmarkRepository.deleteById(compositeKey);
    }

    @Override
    public Boolean existsById(BookmarkCompositeKey compositeKey) {
        return jpaBookmarkRepository.existsById(compositeKey);
    }
}
