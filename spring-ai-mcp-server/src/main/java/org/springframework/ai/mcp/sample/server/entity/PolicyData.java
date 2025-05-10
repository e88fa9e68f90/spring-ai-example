package org.springframework.ai.mcp.sample.server.entity;

import java.util.List;

public class PolicyData {
    private String policyNo;
    private String policyType;
    private String thirdPersonalFlag;
    private String signCode;
    private String waitPayCode;
    private String riskName;
    private String policyStatusCode;
    private String policyStatusName;
    private ApplicantVo applicantVo;
    private String cvaliDate;
    private List<InsurantVo> insurantVo;
    private String systemCode;
    private String payName;
    private String grpContNo;
    private String cardContNo;
    private boolean healthEntrustFlag;
    private String signDate;
    private String isCardPolicy;
    private String policyExpireDate;
    private String payToDate;
    private String payMoney;
    private String goodsName;
    private String goodsCode;
    private String accountType;

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getThirdPersonalFlag() {
        return thirdPersonalFlag;
    }

    public void setThirdPersonalFlag(String thirdPersonalFlag) {
        this.thirdPersonalFlag = thirdPersonalFlag;
    }

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }

    public String getWaitPayCode() {
        return waitPayCode;
    }

    public void setWaitPayCode(String waitPayCode) {
        this.waitPayCode = waitPayCode;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getPolicyStatusCode() {
        return policyStatusCode;
    }

    public void setPolicyStatusCode(String policyStatusCode) {
        this.policyStatusCode = policyStatusCode;
    }

    public String getPolicyStatusName() {
        return policyStatusName;
    }

    public void setPolicyStatusName(String policyStatusName) {
        this.policyStatusName = policyStatusName;
    }

    public ApplicantVo getApplicantVo() {
        return applicantVo;
    }

    public void setApplicantVo(ApplicantVo applicantVo) {
        this.applicantVo = applicantVo;
    }

    public String getCvaliDate() {
        return cvaliDate;
    }

    public void setCvaliDate(String cvaliDate) {
        this.cvaliDate = cvaliDate;
    }

    public List<InsurantVo> getInsurantVo() {
        return insurantVo;
    }

    public void setInsurantVo(List<InsurantVo> insurantVo) {
        this.insurantVo = insurantVo;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getGrpContNo() {
        return grpContNo;
    }

    public void setGrpContNo(String grpContNo) {
        this.grpContNo = grpContNo;
    }

    public String getCardContNo() {
        return cardContNo;
    }

    public void setCardContNo(String cardContNo) {
        this.cardContNo = cardContNo;
    }

    public boolean isHealthEntrustFlag() {
        return healthEntrustFlag;
    }

    public void setHealthEntrustFlag(boolean healthEntrustFlag) {
        this.healthEntrustFlag = healthEntrustFlag;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getIsCardPolicy() {
        return isCardPolicy;
    }

    public void setIsCardPolicy(String isCardPolicy) {
        this.isCardPolicy = isCardPolicy;
    }

    public String getPolicyExpireDate() {
        return policyExpireDate;
    }

    public void setPolicyExpireDate(String policyExpireDate) {
        this.policyExpireDate = policyExpireDate;
    }

    public String getPayToDate() {
        return payToDate;
    }

    public void setPayToDate(String payToDate) {
        this.payToDate = payToDate;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PolicyData [policyNo=");
        builder.append(policyNo);
        builder.append(", policyType=");
        builder.append(policyType);
        builder.append(", thirdPersonalFlag=");
        builder.append(thirdPersonalFlag);
        builder.append(", signCode=");
        builder.append(signCode);
        builder.append(", waitPayCode=");
        builder.append(waitPayCode);
        builder.append(", riskName=");
        builder.append(riskName);
        builder.append(", policyStatusCode=");
        builder.append(policyStatusCode);
        builder.append(", policyStatusName=");
        builder.append(policyStatusName);
        builder.append(", applicantVo=");
        builder.append(applicantVo);
        builder.append(", cvaliDate=");
        builder.append(cvaliDate);
        builder.append(", insurantVo=");
        builder.append(insurantVo);
        builder.append(", systemCode=");
        builder.append(systemCode);
        builder.append(", payName=");
        builder.append(payName);
        builder.append(", grpContNo=");
        builder.append(grpContNo);
        builder.append(", cardContNo=");
        builder.append(cardContNo);
        builder.append(", healthEntrustFlag=");
        builder.append(healthEntrustFlag);
        builder.append(", signDate=");
        builder.append(signDate);
        builder.append(", isCardPolicy=");
        builder.append(isCardPolicy);
        builder.append(", policyExpireDate=");
        builder.append(policyExpireDate);
        builder.append(", payToDate=");
        builder.append(payToDate);
        builder.append(", payMoney=");
        builder.append(payMoney);
        builder.append(", goodsName=");
        builder.append(goodsName);
        builder.append(", goodsCode=");
        builder.append(goodsCode);
        builder.append(", accountType=");
        builder.append(accountType);
        builder.append("]");
        return builder.toString();
    }
}
