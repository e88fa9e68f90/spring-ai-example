package org.springframework.ai.mcp.sample.server.entity;

public class InsurantVo {
    private String customerId;
    private String name;
    private String certType;
    private String certNo;
    private String sexCode;
    private String sexName;
    private String birthday;
    private String telephone;
    private String mobile;
    private String address;
    private String zipCode;
    private String email;
    private String nation;
    private String addressProvince;
    private String addressCity;
    private String addressCountry;
    private String bankCode;
    private String bankName;
    private String bankAccountName;
    private String bankAccountNo;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InsurantVo [customerId=");
        builder.append(customerId);
        builder.append(", name=");
        builder.append(name);
        builder.append(", certType=");
        builder.append(certType);
        builder.append(", certNo=");
        builder.append(certNo);
        builder.append(", sexCode=");
        builder.append(sexCode);
        builder.append(", sexName=");
        builder.append(sexName);
        builder.append(", birthday=");
        builder.append(birthday);
        builder.append(", telephone=");
        builder.append(telephone);
        builder.append(", mobile=");
        builder.append(mobile);
        builder.append(", address=");
        builder.append(address);
        builder.append(", zipCode=");
        builder.append(zipCode);
        builder.append(", email=");
        builder.append(email);
        builder.append(", nation=");
        builder.append(nation);
        builder.append(", addressProvince=");
        builder.append(addressProvince);
        builder.append(", addressCity=");
        builder.append(addressCity);
        builder.append(", addressCountry=");
        builder.append(addressCountry);
        builder.append(", bankCode=");
        builder.append(bankCode);
        builder.append(", bankName=");
        builder.append(bankName);
        builder.append(", bankAccountName=");
        builder.append(bankAccountName);
        builder.append(", bankAccountNo=");
        builder.append(bankAccountNo);
        builder.append("]");
        return builder.toString();
    }
}
