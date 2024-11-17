package com.rocketseat.gestao_vagas.modules.job.repositories;

import com.rocketseat.gestao_vagas.modules.job.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    List<JobEntity> findAllByCompanyId(UUID companyId);

    @Query(value = " select a.id, a.description, a.\"level\", a.benefits, b.candidate_id  from job a \n" +
                     "left join inscription b on a.id = b.job_id\n" +
                     "and b.candidate_id = :candidateId\n" +
                     "where a.company_id  = :companyId", nativeQuery = true)
    List<Object[]> findAllByCompanyId2(@Param("companyId") UUID companyId, @Param("candidateId")  UUID candidateId);


}