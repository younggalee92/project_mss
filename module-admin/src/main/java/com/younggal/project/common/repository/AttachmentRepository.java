package com.younggal.project.common.repository;

import com.younggal.project.domain.common.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
