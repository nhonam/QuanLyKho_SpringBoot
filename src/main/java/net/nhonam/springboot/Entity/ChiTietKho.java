package net.nhonam.springboot.Entity;

//import jakarta.persistence.*;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity // Đánh dấu đây là table trong db
@Table(name = "ChiTietKho")
public class ChiTietKho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kho") // // thông qua khóa ngoại id
    private Kho kho;

    @OneToMany(mappedBy = "chiTietKho", cascade = CascadeType.ALL,fetch = FetchType.LAZY) // Quan hệ 1-n với đối tượng PhieuNhapKho (1 NHANVIEN co nhiều phiếu nhập kho)
    // MapopedBy trỏ tới private User user ở trong PhieuNhapKho.
    private Collection<ViTriSP> viTriSPS;

    @Column(name = "Cot", unique = true,  length = 2)
    private String cot;

    @Column(name = "Hang", unique = true, length = 2)
    private String hang;

    @Column(name = "Day", unique = true, length = 2)
    private String day;

    public ChiTietKho(long id, Kho kho, Collection<ViTriSP> viTriSPS, String cot, String hang, String day) {
        this.id = id;
        this.kho = kho;
        this.viTriSPS = viTriSPS;
        this.cot = cot;
        this.hang = hang;
        this.day = day;
    }

    public ChiTietKho() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Kho getKho() {
        return kho;
    }

    public void setKho(Kho kho) {
        this.kho = kho;
    }

    public Collection<ViTriSP> getViTriSPS() {
        return viTriSPS;
    }

    public void setViTriSPS(Collection<ViTriSP> viTriSPS) {
        this.viTriSPS = viTriSPS;
    }

    public String getCot() {
        return cot;
    }

    public void setCot(String cot) {
        this.cot = cot;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
