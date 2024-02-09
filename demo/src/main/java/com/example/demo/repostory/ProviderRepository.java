package com.example.demo.repostory;
import com.example.demo.modell.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findByProviderName(String transportationType);
    Provider findByTypeTransportationAndAvailable(String transportationType, boolean available);
}