package com.refactoryflow.refactoryflowbackend.Repository;


import com.refactoryflow.refactoryflowbackend.Model.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface SegmentRepository extends JpaRepository<Segment, Long>,
        SegmentRepositoryCustom {

}
