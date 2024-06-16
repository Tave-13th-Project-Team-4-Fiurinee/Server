package com.example.fiurinee.domain.flower.repository;

import com.example.fiurinee.domain.flower.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {

}
