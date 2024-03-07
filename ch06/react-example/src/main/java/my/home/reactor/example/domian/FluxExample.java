package my.home.reactor.example.domian;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Configuration
public class FluxExample {
    static private Logger LOG = LoggerFactory.getLogger(MonoExample.class);

    @Bean
    public CommandLineRunner runFluxExample(){
        return args -> {
            EmitterProcessor<ToDo> stream = EmitterProcessor.create();

            Flux<List<ToDo>> promise = stream
                    .filter(ToDo::isCompleted)
                    .doOnNext(s -> LOG.info("FLUX >>> ToDo: {}", s.getDescription()))
                    .collectList().flux()
                    .subscribeOn(Schedulers.single());


            stream.onNext(new ToDo("Read a book", true));
            stream.onNext(new ToDo("Listen classical music a book", true));
            stream.onComplete();
            promise.blockFirst();

        };
    }

}
