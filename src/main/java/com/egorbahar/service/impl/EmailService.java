package com.egorbahar.service.impl;

import com.egorbahar.entity.Candidate;
import com.egorbahar.entity.Engineer;
import com.egorbahar.entity.Recruiter;
import com.egorbahar.entity.Schedule;
import com.egorbahar.repository.CandidateVacancyRepository;
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
import java.util.StringJoiner;

@Service
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class EmailService {
    private final JavaMailSender mailSender;
    private final ScheduleRepository scheduleRepository;
    private final RecruiterRepository recruiterRepository;
    private final EngineerRepository engineerRepository;
    private final CandidateVacancyRepository candidateVacancyRepository;

    //    @Scheduled(cron = "0 0 7 * * ?")
   // @Scheduled(cron = "0 * * * * ?")
    public void sendEmailToRecruitersCronJob() {
        recruiterRepository
                .findAll()
                .parallelStream()
                .forEach(r -> sendEmailToRecruiter(r,
                        scheduleRepository.findSchedulesByRecruiter_IdAndStartTimeBetween(r.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(1))));
    }
    @Scheduled(cron = "0 * * * * ?")
    public void sendEmailToCandidatesCronJob() {
        scheduleRepository.findAll().parallelStream()
                .filter(s->s.getStartTime().isAfter(LocalDateTime.now())&&s.getStartTime().isBefore(LocalDateTime.now().plusDays(1)))
                .forEach(schedule -> sendEmailToCandidate(schedule.getCandidateVacancy().getCandidate(),schedule));
    }

    public void sendEmailToCandidate(Candidate candidate, Schedule schedule) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(candidate.getEmail());
        StringJoiner joiner = new StringJoiner("");
            joiner.add("Вакансия: ");
            joiner.add(schedule.getCandidateVacancy().getVacancy().getName() + "\n");
            joiner.add("Начало: ");
            joiner.add(schedule.getStartTime().toString().replace("T", " ") + "\n");
            message.setSubject("Собеседование");
            message.setText("Здравствуйте," + candidate.getName() +
                    "!\n" + "Напоминаем Вам о предстоящем собеседованиии: " +
                    joiner + "\n" + "С уважением," + "\n" + "Система оповещения");
        mailSender.send(message);
    }

    public void sendEmailToRecruiter(Recruiter recruiter, List<Schedule> schedules) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recruiter.getEmail());
        message.setSubject("Ваши собеседования на сегодня");
        message.setText("Здравствуйте," + recruiter.getName() +
                "!\n" + "Напоминаем Вам о предстоящих собеседованиях: " +
                createScheduleMessage(schedules) + "\n" + "С уважением," + "\n" + "Система оповещения");
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
        SimpleMailMessage engineerMessage = new SimpleMailMessage();
        engineerMessage.setTo(engineer.getEmail());
        engineerMessage.setSubject("Ваши собеседования на сегодня");
        engineerMessage.setText("Здравствуйте," + engineer.getName() +
                "!\n" + "Напоминаем Вам о предстоящих собеседованиях: "
                + createScheduleMessage(schedules) + "\n" + "С уважением," + "\n" + "Система оповещения");

        mailSender.send(engineerMessage);
    }

    public StringJoiner createScheduleMessage(List<Schedule> schedules) {
        StringJoiner joiner = new StringJoiner("");
        for (Schedule schedule : schedules) {
            joiner.add("Вакансия: ");
            joiner.add(schedule.getCandidateVacancy().getVacancy().getName() + "\n");
            joiner.add("Категория интервью: ");
            joiner.add(schedule.getCategory().getName() + "\n");
            joiner.add("Начало: ");
            joiner.add(schedule.getStartTime().toString().replace("T", " ") + "\n");
            joiner.add("Кандидат: ");
            joiner.add(schedule.getCandidateVacancy().getCandidate().getName() + " " + schedule.getCandidateVacancy().getCandidate().getSurname() + "\n\n");
        }
        return joiner;
    }
}
