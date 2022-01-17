package com.egorbahar.service.impl;

import com.egorbahar.entity.Engineer;
import com.egorbahar.entity.Recruiter;
import com.egorbahar.entity.Schedule;
import com.egorbahar.repository.EngineerRepository;
import com.egorbahar.repository.RecruiterRepository;
import com.egorbahar.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class EmailService {
    private final JavaMailSender mailSender;
    private final ScheduleRepository scheduleRepository;
    private final RecruiterRepository recruiterRepository;
    private final EngineerRepository engineerRepository;

    //    @Scheduled(cron = "0 0 7 * * ?")
    @Scheduled(cron = "0 * * * * ?")
    public void sendEmailToRecruitersCronJob() {
        recruiterRepository
                .findAll()
                .parallelStream()
                .forEach(r -> sendEmailToRecruiter(r,
                        scheduleRepository.findSchedulesByRecruiter_IdAndStartTimeBetween(r.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(1))));
    }

    public void sendEmailToRecruiter(Recruiter recruiter, List<Schedule> schedules) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recruiter.getEmail());
        message.setSubject("Ваши собеседования на сегодня");
        message.setText("Text");
        mailSender.send(message);
    }

   // @Scheduled(cron = "0 * * * * ?")
    public void sendEmailToEngineersCronJob() {
        engineerRepository
                .findAll()
                .parallelStream()
                .forEach(e -> sendEmailToEngineer(e,
                        scheduleRepository.findSchedulesByEngineer_IdAndStartTimeBetween(e.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(1))));
    }

    public void sendEmailToEngineer(Engineer engineer, List<Schedule> schedules) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(engineer.getEmail());
        message.setSubject("Ваше собесе");
        message.setText(schedules.toString());
        mailSender.send(message);

    }
}
