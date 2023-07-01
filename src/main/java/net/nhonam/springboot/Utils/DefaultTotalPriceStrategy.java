package net.nhonam.springboot.Utils;

import java.util.List;

import org.springframework.stereotype.Component;

import net.nhonam.springboot.Entity.PhieuNhapDetail;

@Component
public class DefaultTotalPriceStrategy implements TotalPriceStrategy {
    @Override
    public int calculateTotalPrice(List<PhieuNhapDetail> products) {
        int totalproduct = 0;
        for (PhieuNhapDetail product : products) {
            totalproduct += product.getSoLuong();
        }
        return totalproduct;
    }


}

// // @Component
// // public class DiscountedTotalPriceStrategy implements TotalPriceStrategy {
// //     @Override
// //     public double calculateTotalPrice(List<Product> products) {
// //         // Áp dụng chiến lược tính tổng giá với giảm giá
// //         // Logic xử lý giảm giá và tính toán tổng giá
// //     }
// // }