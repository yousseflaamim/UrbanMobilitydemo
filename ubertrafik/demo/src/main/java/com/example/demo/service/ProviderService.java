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


    private  Provider provider;
    public Provider updateProvider(String providerName, String contactInfo, String typeTransportation) {
        Provider provider = findProviderByProviderName(providerName);
        provider.setContactInfo(contactInfo);
        provider.setTypeTransportation(typeTransportation);
        return providerRepository.save(provider);
    }
    public Provider findProviderByProviderName(String providerName) {
        return providerRepository.findByProviderName(providerName)
                .orElseThrow(() -> new EntityNotFoundException("مقدم الخدمة غير موجود بالاسم: " + providerName));
    }
    public void deleteProviderByProviderName(String providerName) {
        Provider provider = findProviderByProviderName(providerName);
        providerRepository.delete(provider);
    }
    public Provider createProviderAccount( String contactInfo,  String providerName, double compensation,
                                           double promotionalDiscount,String TypeTransportation) {
        Provider provider = new Provider();
        provider.setContactInfo(contactInfo);
        provider.setProviderName(providerName);
        provider.setCompensation(compensation);
        provider.setPromotionalDiscount(promotionalDiscount);
        provider.setTypeTransportation(TypeTransportation);
        return providerRepository.save(provider);
    }

    public void deleteAllProviders() {
    }
    public static String chooseTransportation(User user, String TypeTransportation) {
        // هنا يمكنك تحديد وسيلة النقل بناءً على نوع النقل الممرر
        if ("car".equalsIgnoreCase(TypeTransportation)) {
            // إذا كان نوع النقل هو "car"، يمكنك إرجاع سلسلة نصية تمثل السيارة
            return "Car";
        } else if ("train".equalsIgnoreCase(TypeTransportation)) {
            // إذا كان نوع النقل هو "train"، يمكنك إرجاع سلسلة نصية تمثل القطار
            return "Train";
        } else if ("bus".equalsIgnoreCase(TypeTransportation)) {
            // إذا كان نوع النقل هو "bus"، يمكنك إرجاع سلسلة نصية تمثل الحافلة
            return "Bus";
        } else {
            // إذا كان نوع النقل غير معروف، يمكنك إلقاء استثناء أو اتخاذ إجراء آخر حسب احتياجات مشروعك
            throw new IllegalArgumentException("نوع وسيلة النقل غير معروف: " + TypeTransportation);
        }
    }








    public Provider createProviderAccount(String username, String contactInfo,
                                          String TypeTransportation,
                                          double compensation,
                                          double promotionalDiscount) {
        Provider provider = new Provider();
        provider.setProviderName(username);
        provider.setContactInfo(contactInfo);
        provider.setTypeTransportation(TypeTransportation);
        provider.setCompensation(compensation);
        provider.setPromotionalDiscount(promotionalDiscount);
        return providerRepository.save(provider);
    }
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

}
