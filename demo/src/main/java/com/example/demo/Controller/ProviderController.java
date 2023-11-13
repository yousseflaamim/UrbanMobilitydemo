package com.example.demo.Controller;

import com.example.demo.modell.Provider;
import com.example.demo.modell.User;
import com.example.demo.service.ProviderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProviderController {

    private ProviderService providerService;
    @PostMapping("/createProvider")
    public ResponseEntity<Provider> createProviderAccount(@RequestParam String TypeTransportation,
                                                          @RequestParam String contactInfo,
                                                          @RequestParam String providerName,
                                                          @RequestParam boolean  available,
                                                          @RequestParam double compensation,
                                                          @RequestParam double promotionalDiscount) {
        Provider provider = providerService.createProviderAccount( contactInfo,  providerName, compensation,
                promotionalDiscount, TypeTransportation, available);
        return ResponseEntity.ok(provider);
    }
    @GetMapping("/getAllProviders")
    public ResponseEntity<List<Provider>> getAllProviders() {
        List<Provider> providers = providerService.getAllProviders();
        return ResponseEntity.ok(providers);
    }

    @PutMapping("/updateProvider/{providerName}")
    public ResponseEntity<Provider> updateProvider(@PathVariable String providerName,
                                                   @RequestParam String contactInfo,
                                                   @RequestParam String paymentInfo) {
        Provider updatedProvider = providerService.updateProvider(providerName, contactInfo, paymentInfo);
        return ResponseEntity.ok(updatedProvider);
    }
    @DeleteMapping("/deleteAllProviders")
    public ResponseEntity<Void> deleteAllProviders() {
        providerService.deleteAllProviders();
        return ResponseEntity.noContent().build();
    }


}
