package model;
import enums.Gender;
import enums.cusState;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cus_id")
    private Integer cusId;
    @Column(name = "cus_phone")
    private String cusPhone;
    @Column(name = "cus_pass")
    private String cusPass;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "cus_state")
    private cusState cusState;
    @Column(name = "cus_mail")
    private String cusMail;
    @Column(name = "cus_name")
    private String cusName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "cus_gender")
    private Gender cusGender;
    @Column(name = "cus_nickname")
    private String cusNickname;

    @Column(name = "cus_pic")
    private byte[] cusPic;

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusPass() {
        return cusPass;
    }

    public void setCusPass(String cusPass) {
        this.cusPass = cusPass;
    }


    public String getCusMail() {
        return cusMail;
    }

    public void setCusMail(String cusMail) {
        this.cusMail = cusMail;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusNickname() {
        return cusNickname;
    }

    public void setCusNickname(String cusNickname) {
        this.cusNickname = cusNickname;
    }

    public byte[] getCusPic() {
        return cusPic;
    }

    public void setCusPic(byte[] cusPic) {
        this.cusPic = cusPic;
    }
}
