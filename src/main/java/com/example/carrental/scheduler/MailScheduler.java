package com.example.carrental.scheduler;

import com.example.carrental.config.AdminConfig;
import com.example.carrental.domain.Car;
import com.example.carrental.domain.Mail;
import com.example.carrental.domain.Rent;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.RentRepository;
import com.example.carrental.service.MailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class MailScheduler {

    private static final String SUBJECT = "Car Rental Service Subscription: Once a day email";
    private final AdminConfig adminConfig;
    private final MailService mailService;
    private final RentRepository repository;
    private final CarRepository carRepository;


    public MailScheduler(AdminConfig adminConfig, MailService mailService, RentRepository repository, CarRepository carRepository) {
        this.adminConfig = adminConfig;
        this.mailService = mailService;
        this.repository = repository;
        this.carRepository = carRepository;
    }

   // @Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "0 0 00 * * *")
    public void dailyMailStatusCarAccessForOwner() {

        List<Car> carList = carRepository.findAll();


        Iterable<Rent> rentIterable = repository.findAll();
        List<Rent> rentList = new ArrayList<>();
        rentIterable.forEach(rentList::add);

        String message = " User(login): " + rentList.stream()
                .map(u -> u.getUser().getLogin())
                .collect(Collectors.toList()) + "\n" +
                ((rentList.size() > 1L) ? "have: " : "has: ") +
                "rented car" + rentList.stream()
                .map(c -> c.getCar().getModel())
                .collect(Collectors.toList()) + "\n" +
                "Free cars = " + (carList.size() - rentList.size());

        Mail mail = new Mail(adminConfig.getAdminMail(), SUBJECT, message);
        mailService.send(mail);
    }
}



