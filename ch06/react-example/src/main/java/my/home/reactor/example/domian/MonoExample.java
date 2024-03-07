package my.home.reactor.example.domian;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;


@Configuration
public class MonoExample {
    static private Logger LOG = LoggerFactory.getLogger(MonoExample.class);

    public CommandLineRunner runMonoExample() {
        return args -> {
            MonoProcessor<ToDo> promise = MonoProcessor.create();
            Mono<ToDo> result = promise
                    .doOnSuccess(p -> LOG.info("MONO >> ToDo: {}", p.getDescription()))
                    .doOnTerminate(() -> LOG.info("MONO >> Done"))
                    .doOnError(t -> LOG.error(t.getMessage(), t))
                    .subscribeOn(Schedulers.single());
            promise.onNext(new ToDo("Buy my ticket for Spring One Platform 2018"));
            result.block(Duration.ofMillis(1000));
        };
    }
}
