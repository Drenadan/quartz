package com.playtika.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Maksym Samsonov.
 * @since 11/12/17.
 */
@Service
@Slf4j
public class QuartzService {

    public void processData() {
        System.out.println("First job processed");
    }

    public void secondJobProcessData() {
        System.out.println("Second job processed");
    }
}
