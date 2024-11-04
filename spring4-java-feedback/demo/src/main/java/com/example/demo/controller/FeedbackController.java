package com.example.demo.controller;

import com.example.demo.model.Feedback;
import com.example.demo.repository.FeedbackRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;

    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @GetMapping
    public String listFeedback(Model model) {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        model.addAttribute("feedbacks", feedbackList);
        return "feedback/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback/new";
    }

    @PostMapping
    public String createFeedback(@Valid @ModelAttribute Feedback feedback, BindingResult result) {
        if (result.hasErrors()) {
            return "feedback/new";
        }
        feedbackRepository.save(feedback);
        return "redirect:/feedback";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Feedback inválido: " + id));
        model.addAttribute("feedback", feedback);
        return "feedback/edit";
    }

    @PostMapping("/{id}")
    public String updateFeedback(@PathVariable Long id, @Valid @ModelAttribute Feedback feedback, BindingResult result) {
        if (result.hasErrors()) {
            feedback.setId(id);
            return "feedback/edit";
        }
        feedback.setId(id);
        feedbackRepository.save(feedback);
        return "redirect:/feedback";
    }

    @GetMapping("/{id}/delete")
    public String deleteFeedback(@PathVariable Long id) {
        feedbackRepository.deleteById(id);
        return "redirect:/feedback";
    }

}
