package org.example.fundspark.controller;

import jakarta.validation.Valid;
import org.example.fundspark.model.Comment;
import org.example.fundspark.model.Fundraiser;
import org.example.fundspark.service.FundraiserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/fundraisers")
public class FundraiserController {

    private final FundraiserService fundraiserService;

    public FundraiserController(FundraiserService fundraiserService) {
        this.fundraiserService = fundraiserService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Fundraiser> createFundraiser(@Valid @RequestBody Fundraiser fundraiser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fundraiserService.createFundraiser(fundraiser));
    }

    @GetMapping
    public ResponseEntity<List<Fundraiser>> getAllFundraisers() {
        return ResponseEntity.ok(fundraiserService.getAllFundraisers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fundraiser> getFundraiserById(@PathVariable String id) {
        return ResponseEntity.ok(fundraiserService.getFundraiserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fundraiser> updateFundraiser(@PathVariable String id,
            @Valid @RequestBody Fundraiser fundraiser) {
        return ResponseEntity.ok(fundraiserService.updateFundraiser(id, fundraiser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundraiser(@PathVariable String id) {
        fundraiserService.deleteFundraiser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable String id) {
        return ResponseEntity.ok(fundraiserService.getComments(id));
    }

}
