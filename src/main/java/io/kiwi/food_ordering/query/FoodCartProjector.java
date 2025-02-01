package io.kiwi.food_ordering.query;

import io.kiwi.food_ordering.coreapi.*;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class FoodCartProjector {

    private final FoodCartViewRepository repository;

    public FoodCartProjector(FoodCartViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(FoodCartCreatedEvent event) {
        FoodCartView foodCartView = new FoodCartView(event.getFoodCartId(), Collections.emptyMap());
        repository.save(foodCartView);
    }

    @QueryHandler
    public FoodCartView handle(FindFoodCartQuery query) {
        return repository.findById(query.getFoodCartId()).orElse(null);
    }
}
