package com.rep.api.widget_type;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rep-api/v1/widget_types")
@RequiredArgsConstructor
public class WidgetTypeController {

    private final WidgetTypeService widgetTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<WidgetType>> findAll() {
        return ResponseEntity.ok(widgetTypeService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody WidgetType widgetType) {
        widgetTypeService.save(widgetType);

        return ResponseEntity.ok("Post created successfully");
    }
}