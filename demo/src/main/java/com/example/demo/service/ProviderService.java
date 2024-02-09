package com.example.demo.service;
import com.example.demo.modell.Provider;
import com.example.demo.modell.User;
import com.example.demo.repostory.ProviderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProviderService {
    @Autowired
    private ProviderRepository providerRepository;
    private Provider provider;
    public Provider updateProvider(String providerName, String contactInfo, String typeTransportation) {
        Provider provider = findProviderByProviderName(providerName);
        provider.setContactInfo(contactInfo);
        provider.setTypeTransportation(typeTransportation);
        return providerRepository. save(provider);
    }
    public Provider findProviderByProviderName(String providerName) {
        return providerRepository.findByProviderName(providerName)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found by name: " + providerName));
    }
    public void deleteProviderByProviderName(String providerName) {
        Provider provider = findProviderByProviderName(providerName);
        providerRepository. delete(provider);
    }
    public boolean isTransportAvailable(String transportationType) {
        Provider provider = providerRepository. findByTypeTransportationAndAvailable(transportationType, true);
        return provider != null;
    }
    public Provider createProviderAccount( String contactInfo, String providerName, double compensation,
                                           double promotionalDiscount,String TypeTransportation,boolean available) {
        Provider provider = new Provider();
        provider.setContactInfo(contactInfo);
        provider.setProviderName(providerName);
        provider.setCompensation(compensation);
        provider.setPromotionalDiscount(promotionalDiscount);
        provider.setTypeTransportation(TypeTransportation);
        provider.setAvailable(available);
        return providerRepository. save(provider);
    }
    public void deleteAllProviders() {
    }
    public static String chooseTransportation(User user, String TypeTransportation) {
        if ("car". equalsIgnoreCase(TypeTransportation)) {
            return "Car";
        } else if ("train". equalsIgnoreCase(TypeTransportation)) {
            return "Train";
        } else if ("bus". equalsIgnoreCase(TypeTransportation)) {
            return "Bus";
        } else {
            throw new IllegalArgumentException("Unknown type of transport: " + TypeTransportation);
        }
    }








    public Provider createProviderAccount(String username, String contactInfo,
                                          String TypeTransportation,
                                          double compensation,
                                          double promotionalDiscount) {
        Provider provider = new Provider();
        provider.setProviderName(username);
        provider. setContactInfo(contactInfo);
        provider.setTypeTransportation(TypeTransportation);
        provider.setCompensation(compensation);
        provider.setPromotionalDiscount(promotionalDiscount);
        return providerRepository. save(provider);
    }
    public List<Provider> getAllProviders() {
        return providerRepository. findAll();
    }

}