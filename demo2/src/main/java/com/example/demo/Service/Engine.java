package com.example.demo.Service;

import java.util.List;
import java.util.Map;

public interface Engine {
    Map<String, String> decide(String argument);
    Map<String,String> decideWithContext(List<String> history, String newArgument);
    String summarize(String combinedText);
}
