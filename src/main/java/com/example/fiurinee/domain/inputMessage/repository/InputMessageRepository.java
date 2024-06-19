package com.example.fiurinee.domain.inputMessage.repository;

import com.example.fiurinee.domain.inputMessage.entity.InputMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputMessageRepository extends JpaRepository<InputMessage,Long> {
}
