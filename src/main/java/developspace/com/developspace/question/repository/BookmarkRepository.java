package developspace.com.developspace.question.repository;

import developspace.com.developspace.question.entity.Bookmark;
import developspace.com.developspace.question.entity.BookmarkCompositeKey;

public interface BookmarkRepository {
    Bookmark save(Bookmark bookmark);
    void deleteById(BookmarkCompositeKey compositeKey);
    Boolean existsById(BookmarkCompositeKey compositeKey);
}
