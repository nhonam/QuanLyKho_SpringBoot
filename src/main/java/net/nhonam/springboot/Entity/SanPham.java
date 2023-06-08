package net.nhonam.springboot.Entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "TenSanPham", unique = true)
    private String tenSanPham;

    @Column(name = "NgaySanXuat")
    private Date ngaySanXuat;


    @Column(name = "HanSuDung")
    private int thang;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ViTriSP> viTriSPs;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL)
    private Collection<PhieuXuatDetail> phieuXuatDetails;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL)
    private Collection<PhieuNhapDetail> phieuNhapDetails;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL)
    private Collection<GiaSanPham> giaSanPhams;

    private SanPham(BuilderPT builder) {
        setId(builder.id);
        setTenSanPham(builder.tenSanPham);
        setNgaySanXuat(builder.ngaySanXuat);
        setThang(builder.thang);
    }

    public static BuilderPT newSanPham() {
        return new BuilderPT();
    }

    // định nghĩa một inner class bên trong class product

    public static final class BuilderPT {
        private long id;
        private String tenSanPham;

        private Date ngaySanXuat;


        private int thang;

        private BuilderPT() {
        }

        public BuilderPT id(long id) {
            this.id = id;
            return this;
        }

        public BuilderPT tenSanPham(String name) {
            this.tenSanPham = name;
            return this;
        }

        public BuilderPT ngaySanXuat(Date ngaySanXuat) {
            this.ngaySanXuat = ngaySanXuat;
            return this;
        }

        public BuilderPT thang(int thang) {
            this.thang = thang;
            return this;
        }
        public SanPham build() {
            return new SanPham(this);
        }

    }

    // Khi muốn tạo một object ta chỉ cần khai báo như sau
//    Product product = Product.newProduct()
//            .id(1l)
//            .description("TV 46'")
//            .value(2000.00)
//            .name("TV 46'")
//            .build();
    // nhìn vào có thể thấy code khá dễ hiểu trong việc mô tả các trường trong trường hợp cần nhiều biến cần truyền vào


}
