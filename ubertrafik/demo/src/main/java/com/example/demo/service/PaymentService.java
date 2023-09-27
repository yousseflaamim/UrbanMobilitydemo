package com.example.demo.service;

import com.example.demo.modell.User;
import com.example.demo.modell.Booking;
import com.example.demo.modell.Payment;
import com.example.demo.repostory.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    // يمكنك حقن واجهة المستودع (repository) هنا لتخزين معلومات الدفع في قاعدة البيانات
    @Autowired
    private  PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentService() {
    }

    public boolean processPayment(User user, Booking booking) {
        // قم بتنفيذ عملية الدفع هنا
        // يمكنك استخدام خدمات الدفع الخارجية مثل Stripe أو PayPal أو غيرها

        // في هذا المثال، سنفترض أن عملية الدفع ناجحة دائمًا
        // يمكنك تغيير هذا وفقًا للتكامل مع خدمة الدفع الفعلية
        boolean paymentSuccess = true;

        if (paymentSuccess) {
            // إذا كانت عملية الدفع ناجحة، قم بتحديث حالة الحجز إلى مدفوع
            booking.setPaid(true);

            // يمكنك أيضًا حفظ تفاصيل عملية الدفع في قاعدة البيانات إذا كنت بحاجة إلى ذلك
            Payment payment = new Payment();
            payment.setBooking(booking);
            payment.setAccount(user);;
            payment.setAmount(booking.getFare());

            // حفظ تفاصيل الدفع في قاعدة البيانات
            paymentRepository.save(payment);
        }

        return paymentSuccess;
    }
}
