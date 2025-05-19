package com.example.demo.web;

import com.example.demo.Service.DecisionService;
import com.example.demo.model.Decision;
import com.example.demo.rest.SummaryResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {

    private final DecisionService decisionService;

    public WebController(DecisionService decisionService) {
        this.decisionService = decisionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("history", List.<Decision>of());
        return "index";
    }

    @PostMapping("/submit")
    public String submitArgument(
            @RequestParam String speaker,
            @RequestParam String text,
            Model model)
    {
        Decision last = decisionService.evaluateArgument(speaker, text);

        List<Decision> history = decisionService.getAllDecisions();
        model.addAttribute("lastDecision", last);
        model.addAttribute("history", history);
        return "index";
    }

    @PostMapping("/summary")
    public String showSummary(Model model) {
        List<Decision> history = decisionService.getAllDecisions();

        SummaryResponse resp = decisionService.summarizeDecisions(history);

        model.addAttribute("history", history);
        model.addAttribute("summary", resp);
        return "index";
    }
}
