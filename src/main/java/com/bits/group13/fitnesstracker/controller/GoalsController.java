package com.bits.group13.fitnesstracker.controller;

import com.bits.group13.fitnesstracker.database.GoalRecord;
import com.bits.group13.fitnesstracker.model.ApiException;
import com.bits.group13.fitnesstracker.model.goals.Goal;
import com.bits.group13.fitnesstracker.repository.GoalsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GoalsController {
  private static final Logger LOGGER = LoggerFactory.getLogger(GoalsController.class);

  private final JsonMapper jsonMapper;
  private final GoalsRepository goalsRepository;

  public GoalsController(JsonMapper jsonMapper, GoalsRepository goalsRepository) {
    this.jsonMapper = jsonMapper;
    this.goalsRepository = goalsRepository;
  }

  @PostMapping("/goals")
  public ResponseEntity<?> createGoal(@RequestBody Goal goal)
      throws ApiException, JsonProcessingException {
    //    if (StringUtils.isEmpty(goal.type())) {
    //      throw new ApiException.ParamNotSet("type");
    //    }
    GoalRecord savedGoal = goalsRepository.save(goal.toGoalRecord(null, jsonMapper));
    return ResponseEntity.ok().body(savedGoal);
  }

  @PostMapping("/goals/{id}")
  public ResponseEntity<?> updateGoal(@PathVariable("id") String id, @RequestBody Goal goal)
      throws JsonProcessingException {
    Optional<GoalRecord> oldRecord = goalsRepository.findById(id);
    if (oldRecord.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    GoalRecord savedRecord = goalsRepository.save(goal.toGoalRecord(null, jsonMapper));
    return ResponseEntity.ok().body(savedRecord);
  }

  @GetMapping("/goals")
  public ResponseEntity<Iterable<?>> getGoals() {
    Iterable<GoalRecord> records = goalsRepository.findAll();
    return ResponseEntity.ok()
        .body(
            StreamSupport.stream(records.spliterator(), false)
                .map(
                    goalRecord -> {
                      try {
                        return goalRecord.toGoal(jsonMapper);
                      } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                      }
                    })
                .collect(Collectors.toList()));
  }

  @GetMapping("/goals/{id}")
  public ResponseEntity<Goal> getGoal(@PathVariable("id") String goalsId)
      throws JsonProcessingException {
    Optional<GoalRecord> goalRecord = goalsRepository.findById(goalsId);
    if (goalRecord.isPresent()) {
      return ResponseEntity.ok(goalRecord.get().toGoal(jsonMapper));
    }
    return ResponseEntity.notFound().build();
  }
}
