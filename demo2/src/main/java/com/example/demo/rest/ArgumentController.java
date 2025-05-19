package com.example.demo.rest;

import com.example.demo.Service.DecisionService;
import com.example.demo.model.Decision;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ArgumentController {

    private final DecisionService svc;

    public ArgumentController(DecisionService svc) {
        this.svc = svc;
    }

    @PostMapping("/argument")
    public Decision evalOne(@RequestBody ArgumentRequest req) {
        return svc.evaluateArgument(req.getSpeaker(), req.getText());
    }

    @PostMapping("/arguments")
    public List<Decision> evalBatch(@RequestBody List<ArgumentRequest> reqs) {
        return reqs.stream()
                .map(r -> svc.evaluateArgument(r.getSpeaker(), r.getText()))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/arguments/summary")
    public SummaryResponse summaryBatch(@RequestBody List<ArgumentRequest> reqs) {
        List<Decision> evaluated = reqs.stream()
                .map(r -> svc.evaluateArgument(r.getSpeaker(), r.getText()))
                .collect(Collectors.toList());
        return svc.summarizeDecisions(evaluated);
    }
    public static class ArgumentRequest {
        private String speaker;
        private String text;
        public String getSpeaker() { return speaker; }
        public void setSpeaker(String speaker) { this.speaker = speaker; }
        public String getText()    { return text;    }
        public void setText(String text)       { this.text = text; }
    }
}
