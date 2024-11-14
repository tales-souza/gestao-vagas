package com.rocketseat.gestao_vagas.modules.inscription.entities;

import com.rocketseat.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.rocketseat.gestao_vagas.modules.inscription.enums.StatusEnum;
import com.rocketseat.gestao_vagas.modules.job.entities.JobEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="inscription")
@IdClass(InscriptionId.class)
public class InscriptionEntity {
        @ManyToOne()
        @JoinColumn(name = "job_id",insertable = false, updatable = false)
        private JobEntity jobEntity;

        @Id
        @Column( name = "job_id")
        private UUID jobId;

        @ManyToOne()
        @JoinColumn(name = "candidate_id",insertable = false, updatable = false)
        private CandidateEntity CandidateEntity;

        @Id
        @Column( name = "candidate_id")
        private UUID candidateId;

        private StatusEnum status;

        @CreationTimestamp
        private LocalDateTime createdAt;


    }

