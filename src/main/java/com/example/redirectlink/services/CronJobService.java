package com.example.redirectlink.services;

import com.example.redirectlink.database.enities.LinkEnity;
import com.example.redirectlink.database.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CronJobService {

    @Autowired
    private LinkRepository linkRepository;
    @Scheduled(cron = "0 * * * * *\n")
    public void runEvey5Minutes() {

        System.out.println("RUN CRON");

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.plusDays(4);
        List<LinkEnity> allLinks = linkRepository.findByInitDateBefore(yesterday);

        for (LinkEnity link: allLinks) {
            linkRepository.deleteByLinkId(link.getLinkId());

        }

        var b = linkRepository.findByInitDateBefore(yesterday);
    }
}