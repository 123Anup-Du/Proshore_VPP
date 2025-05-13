package com.vpp.powerplant.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vpp.powerplant.entity.Battery;

@Repository
public interface PowerPlantRepo extends JpaRepository<Battery, Long> {
	List<Battery> findByPostalCodeBetween(String minPostCode, String maxPostCode);

}
