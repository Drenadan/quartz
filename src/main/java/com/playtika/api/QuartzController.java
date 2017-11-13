package com.playtika.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.playtika.services.JobService;

/**
 * @author Maksym Samsonov.
 * @since 11/12/17.
 */
@Api("Client API")
@RestController
@RequestMapping("/quartz")
@RequiredArgsConstructor
public class QuartzController {

    @Autowired
    JobService jobService;

    @PostMapping
    public ResponseEntity<String> createJob(@RequestParam("Firts start time")long firstStart,
                                            @RequestParam("Repeat interval") int intervalInSec,
                                            @RequestParam("Raffle name ") String raffleType,
                                            @RequestParam("Repeat count") int repeatCount) throws SchedulerException {
        jobService.addNewJob(firstStart, intervalInSec, raffleType, repeatCount);
        return ResponseEntity
            .status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteJob(String id) throws SchedulerException {
        return ResponseEntity
            .status(jobService.deleteJob(id)? HttpStatus.OK: HttpStatus.NOT_FOUND).build();
    }

}
