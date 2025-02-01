package io.kiwi.food_ordering.gui;

import io.kiwi.food_ordering.query.FoodCartView;
import io.kiwi.food_ordering.coreapi.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class FoodOrderingController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public FoodOrderingController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/create")
    public void handle() {
        commandGateway.send(new CreateFoodCartCommand());
    }

    @GetMapping("/foodcart/{foodCartId}")
    public CompletableFuture<FoodCartView> handle(@PathVariable("foodCartId") String foodCartId) {
        return queryGateway.query(
                new FindFoodCartQuery(UUID.fromString(foodCartId)),
                ResponseTypes.instanceOf(FoodCartView.class)
        );
    }
}
