package com.refactoringflow.refactoringflowbackend.Repository;

import com.refactoringflow.refactoringflowbackend.Model.CodeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeFileRepository extends JpaRepository<CodeFile, Long> {

}
