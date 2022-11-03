package com.refactoringflow.refactoringflowbackend.repository;

import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeFileRepository extends JpaRepository<CodeFile, Long> {

}
