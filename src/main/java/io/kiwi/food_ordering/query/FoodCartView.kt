package io.kiwi.food_ordering.query

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID


@Entity
data class FoodCartView(
    @Id val foodCartId: UUID,
    @ElementCollection val products: Map<UUID, Int>
)

interface FoodCartViewRepository : JpaRepository<FoodCartView, UUID>