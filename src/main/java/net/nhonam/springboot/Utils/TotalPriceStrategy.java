package net.nhonam.springboot.Utils;

import java.util.List;

import net.nhonam.springboot.Entity.PhieuNhapDetail;

public interface TotalPriceStrategy {
    int calculateTotalPrice(List<PhieuNhapDetail> products);
}
