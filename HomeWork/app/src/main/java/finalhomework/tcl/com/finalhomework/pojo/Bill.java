package finalhomework.tcl.com.finalhomework.pojo;

public class Bill {
    private String FOOD;
    private String FRUIT;
    private String SHOPPING;
    //生活用品？
    private String SNACK;
    private String VEGETABLES;
    private String AMESUMENT;//娱乐活动
    private String RENT;//住房
    private String MEDICAL_TREATMENT;
    private String EDUCATION;
    private String CLOTHES;
    private String TRAFFIC;
    private String KIDS;
    private String COSMETOLOGY;//美容
    private String OLD_MAN;
    private String PET;
    private String TRAVEL;
    private String TELEPHONE;

    public Bill(String FOOD, String FRUIT, String SHOPPING, String SNACK, String VEGETABLES, String AMESUMENT, String RENT, String MEDICAL_TREATMENT, String EDUCATION, String CLOTHES, String TRAFFIC, String KIDS, String COSMETOLOGY, String OLD_MAN, String PET, String TRAVEL, String TELEPHONE) {
        this.FOOD = FOOD;
        this.FRUIT = FRUIT;
        this.SHOPPING = SHOPPING;
        this.SNACK = SNACK;
        this.VEGETABLES = VEGETABLES;
        this.AMESUMENT = AMESUMENT;
        this.RENT = RENT;
        this.MEDICAL_TREATMENT = MEDICAL_TREATMENT;
        this.EDUCATION = EDUCATION;
        this.CLOTHES = CLOTHES;
        this.TRAFFIC = TRAFFIC;
        this.KIDS = KIDS;
        this.COSMETOLOGY = COSMETOLOGY;
        this.OLD_MAN = OLD_MAN;
        this.PET = PET;
        this.TRAVEL = TRAVEL;
        this.TELEPHONE = TELEPHONE;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "FOOD='" + FOOD + '\'' +
                ", FRUIT='" + FRUIT + '\'' +
                ", SHOPPING='" + SHOPPING + '\'' +
                ", SNACK='" + SNACK + '\'' +
                ", VEGETABLES='" + VEGETABLES + '\'' +
                ", AMESUMENT='" + AMESUMENT + '\'' +
                ", RENT='" + RENT + '\'' +
                ", MEDICAL_TREATMENT='" + MEDICAL_TREATMENT + '\'' +
                ", EDUCATION='" + EDUCATION + '\'' +
                ", CLOTHES='" + CLOTHES + '\'' +
                ", TRAFFIC='" + TRAFFIC + '\'' +
                ", KIDS='" + KIDS + '\'' +
                ", COSMETOLOGY='" + COSMETOLOGY + '\'' +
                ", OLD_MAN='" + OLD_MAN + '\'' +
                ", PET='" + PET + '\'' +
                ", TRAVEL='" + TRAVEL + '\'' +
                ", TELEPHONE='" + TELEPHONE + '\'' +
                '}';
    }

    public String getFOOD() {
        return FOOD;
    }

    public void setFOOD(String FOOD) {
        this.FOOD = FOOD;
    }

    public String getFRUIT() {
        return FRUIT;
    }

    public void setFRUIT(String FRUIT) {
        this.FRUIT = FRUIT;
    }

    public String getSHOPPING() {
        return SHOPPING;
    }

    public void setSHOPPING(String SHOPPING) {
        this.SHOPPING = SHOPPING;
    }

    public String getSNACK() {
        return SNACK;
    }

    public void setSNACK(String SNACK) {
        this.SNACK = SNACK;
    }

    public String getVEGETABLES() {
        return VEGETABLES;
    }

    public void setVEGETABLES(String VEGETABLES) {
        this.VEGETABLES = VEGETABLES;
    }

    public String getAMESUMENT() {
        return AMESUMENT;
    }

    public void setAMESUMENT(String AMESUMENT) {
        this.AMESUMENT = AMESUMENT;
    }

    public String getRENT() {
        return RENT;
    }

    public void setRENT(String RENT) {
        this.RENT = RENT;
    }

    public String getMEDICAL_TREATMENT() {
        return MEDICAL_TREATMENT;
    }

    public void setMEDICAL_TREATMENT(String MEDICAL_TREATMENT) {
        this.MEDICAL_TREATMENT = MEDICAL_TREATMENT;
    }

    public String getEDUCATION() {
        return EDUCATION;
    }

    public void setEDUCATION(String EDUCATION) {
        this.EDUCATION = EDUCATION;
    }

    public String getCLOTHES() {
        return CLOTHES;
    }

    public void setCLOTHES(String CLOTHES) {
        this.CLOTHES = CLOTHES;
    }

    public String getTRAFFIC() {
        return TRAFFIC;
    }

    public void setTRAFFIC(String TRAFFIC) {
        this.TRAFFIC = TRAFFIC;
    }

    public String getKIDS() {
        return KIDS;
    }

    public void setKIDS(String KIDS) {
        this.KIDS = KIDS;
    }

    public String getCOSMETOLOGY() {
        return COSMETOLOGY;
    }

    public void setCOSMETOLOGY(String COSMETOLOGY) {
        this.COSMETOLOGY = COSMETOLOGY;
    }

    public String getOLD_MAN() {
        return OLD_MAN;
    }

    public void setOLD_MAN(String OLD_MAN) {
        this.OLD_MAN = OLD_MAN;
    }

    public String getPET() {
        return PET;
    }

    public void setPET(String PET) {
        this.PET = PET;
    }

    public String getTRAVEL() {
        return TRAVEL;
    }

    public void setTRAVEL(String TRAVEL) {
        this.TRAVEL = TRAVEL;
    }

    public String getTELEPHONE() {
        return TELEPHONE;
    }

    public void setTELEPHONE(String TELEPHONE) {
        this.TELEPHONE = TELEPHONE;
    }
}
