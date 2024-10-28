package hcmute.uni.phonestore.service;

import hcmute.uni.phonestore.model.Phone;
import hcmute.uni.phonestore.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    public Page<Phone> getAllPhones(String keyword, Pageable pageable) {
        if (keyword != null) {
            return phoneRepository.findByNameContaining(keyword, pageable);
        }
        return phoneRepository.findAll(pageable);
    }

    public Phone getPhoneById(Long id) {
        return phoneRepository.findById(id).orElse(null);
    }

    public void savePhone(Phone phone) {
        phoneRepository.save(phone);
    }

    public void deletePhone(Long id) {
        phoneRepository.deleteById(id);
    }
}
