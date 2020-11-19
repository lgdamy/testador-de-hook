package com.damytec.testadordehook.repository;

import com.damytec.testadordehook.domain.jpa.Hook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author lgdamy@raiadrogasil.com on 19/11/2020
 */
public interface HookRepository extends JpaRepository<Hook, Long> {

    Hook findFirstByOrderByHora();
}
