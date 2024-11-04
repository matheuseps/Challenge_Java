package com.example.demo.config;


import com.example.demo.model.Feedback;
import com.example.demo.repository.FeedbackRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final FeedbackRepository feedbackRepository;

    public DataInitializer(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        feedbackRepository.deleteAll();

        Feedback feedback1 = new Feedback();
        feedback1.setNomeUsuario("João Silva");
        feedback1.setComentario("Ótimo serviço!");
        feedback1.setAvaliacao(5);

        Feedback feedback2 = new Feedback();
        feedback2.setNomeUsuario("Maria Oliveira");
        feedback2.setComentario("Gostei bastante, mas pode melhorar.");
        feedback2.setAvaliacao(4);

        Feedback feedback3 = new Feedback();
        feedback3.setNomeUsuario("Carlos Pereira");
        feedback3.setComentario("Achei o atendimento lento.");
        feedback3.setAvaliacao(3);

        feedbackRepository.save(feedback1);
        feedbackRepository.save(feedback2);
        feedbackRepository.save(feedback3);

        System.out.println("Feedbacks iniciais foram adicionados ao banco de dados.");
    }
}
