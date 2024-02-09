package com.example.demo.serviceTest;
import com.example.demo.service.ProviderService;
import com.example.demo.modell.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class ProviderServiceTest {
    @Autowired
    private ProviderService providerService;
    @Test
    public void testCreateProvider() {
        String providerName = "sl";
        String contactInfo = "providersl@example.com";
        String typeTransportation = "bus";
         boolean  available =true;
        Provider provider = providerService.createProviderAccount(providerName, contactInfo, typeTransportation, 100.0, 10.0);
        assert provider != null;
        assert provider.getId() != null;
    }
}