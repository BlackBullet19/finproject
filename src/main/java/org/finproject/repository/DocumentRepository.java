package org.finproject.repository;

import org.finproject.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    List<Document> findAllByUploadedBy_UserId(long userId);

    Document findByIdAndUploadedBy_UserId(@Param("id")long documentId,@Param("user_id") long userId);
}
