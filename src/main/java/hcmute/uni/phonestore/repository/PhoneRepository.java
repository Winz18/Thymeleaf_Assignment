package hcmute.uni.phonestore.repository;

import hcmute.uni.phonestore.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Page<Phone> findByNameContaining(String keyword, Pageable pageable);
}
