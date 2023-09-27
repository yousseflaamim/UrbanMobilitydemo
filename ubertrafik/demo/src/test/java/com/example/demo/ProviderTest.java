package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.service.ProviderService;
import org.junit.jupiter.api.Test;

public class ProviderTest {

    @Test
    public void testChooseTransportation() {
        // إعداد مزيف للخدمة
        ProviderService providerService = new ProviderService();

        // اختبار اختيار وسيلة النقل
        String transportationType = providerService.chooseTransportation(null, "car");

        // التحقق من نوع وسيلة النقل المختار
        assertNotNull(transportationType);
        assertEquals("Car", transportationType);
    }
}
