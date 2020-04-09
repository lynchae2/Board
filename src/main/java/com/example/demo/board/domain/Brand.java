package com.example.demo.board.domain;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity    //entity 클래스 //db와 연결된 클래스 //반드시 pk가져야함
@Table(name = "BRAND") //이 이름으로 테이블이 생성될것임
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)	//자동으로 증가하는 키
    private int id;

    private String brandName;

    @Size(max = 2147000000)
    private String css;

    public Brand(){

    }
    public Brand(String brandName, String css) {
        this.brandName = brandName;
        this.css = css;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }
}
