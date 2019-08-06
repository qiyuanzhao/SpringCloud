package com.commanod;

import com.entity.User;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observables.AsyncOnSubscribe;
import rx.observables.SyncOnSubscribe;

public class UserObservableCommand extends HystrixObservableCommand<User> {

    private RestTemplate restTemplate;
    private Long id;



    public UserObservableCommand(Setter setter, RestTemplate restTemplate, Long id){
        super(setter);
        this.id = id;
        this.restTemplate = restTemplate;

    }


    @Override
    protected Observable<User> construct() {



        return Observable.create(new SyncOnSubscribe<Object,User>() {
            @Override
            protected Object generateState() {
                return null;
            }

            @Override
            protected Object next(Object o, Observer<? super User> observer) {
                User user = restTemplate.getForObject("http://HELLOSERVICE/user/{id}", User.class, 1L);

                observer.onNext(user);
                observer.onCompleted();

                return null;
            }
        });
    }
}
