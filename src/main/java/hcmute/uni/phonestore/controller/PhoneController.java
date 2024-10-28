package hcmute.uni.phonestore.controller;

import hcmute.uni.phonestore.model.Phone;
import hcmute.uni.phonestore.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/phones")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping
    public String listPhones(@RequestParam(value = "keyword", required = false) String keyword,
                             @RequestParam(defaultValue = "0") int page,
                             Model model) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Phone> phones = phoneService.getAllPhones(keyword, pageable);
        model.addAttribute("phones", phones);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "phone-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("phone", new Phone());
        return "phone-form";
    }

    @PostMapping("/save")
    public String savePhone(@ModelAttribute("phone") Phone phone) {
        phoneService.savePhone(phone);
        return "redirect:/phones";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Phone phone = phoneService.getPhoneById(id);
        model.addAttribute("phone", phone);
        return "phone-form";
    }

    @GetMapping("/delete/{id}")
    public String deletePhone(@PathVariable("id") Long id) {
        phoneService.deletePhone(id);
        return "redirect:/phones";
    }
}
