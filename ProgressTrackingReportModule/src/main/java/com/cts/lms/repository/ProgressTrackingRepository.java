
package com.cts.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.lms.model.Progress;

public interface ProgressTrackingRepository extends JpaRepository<Progress, Integer> {

}
