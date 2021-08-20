package com.back.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.apoteka.model.WorkTime;

public interface WorkTimeRepository extends JpaRepository<WorkTime, Long> {

}
