package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyModel {
    private String name;
    private String motto;
    private String country;
    private String description;
    private String fiscalName;
    private String date;
    private String currency;
    private String id;
    private String registrationNumber;
    private String bank;
    private String iban;
    private String hq;
    private String type;


    //required fields constructor
    public CompanyModel(String name, String motto, String country, String fiscalName, String id, String registrationNumber, String bank, String iban, String hq) {
        this.name = name;
        this.motto = motto;
        this.country = country;
        this.fiscalName = fiscalName;
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.bank = bank;
        this.iban = iban;
        this.hq = hq;
    }
}
