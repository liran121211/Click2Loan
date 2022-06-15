package com.example.demo;

import core.ann.classifier.Matrix;
import core.ann.classifier.MatrixExceptionHandler;

import java.util.prefs.Preferences;

enum GENDER {
    MALE(2), FEMALE(1);
    private final int value;

    GENDER(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

//
enum FLAG_OWN_CAR {
    NO(1), YES(2);
    private final int value;

    FLAG_OWN_CAR(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

////
enum FLAG_OWN_REALTY {
    NO(1), YES(2);
    private final int value;

    FLAG_OWN_REALTY(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum NAME_EDUCATION_TYPE {
    ACADEMIC_DEGREE(1), HIGHER_EDUCATION(2), INCOMPLETE_HIGHER(3), LOWER_SECONDARY(4), SECONDARY(5);
    private final int value;

    NAME_EDUCATION_TYPE(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

//
enum NAME_FAMILY_STATUS {
    CIVIL_MARRIAGE(1), MARRIED(2), SEPARATED(3), SINGLE(4), Unknown(5), WIDOW(6);
    private final int value;

    NAME_FAMILY_STATUS(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum NAME_HOUSING_TYPE {
    CO_OP_APARTMENT(1), HOUSE_OR_APARTMENT(2), MUNICIPAL_APARTMENT(3), OFFICE_APARTMENT(4), RENTED_APARTMENT(5), WITH_PARENTS(6);
    private final int value;

    NAME_HOUSING_TYPE(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_MOBIL {
    NO(1), YES(2);
    private final int value;

    FLAG_MOBIL(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_EMP_PHONE {
    NO(1), YES(2);
    private final int value;

    FLAG_EMP_PHONE(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_WORK_PHONE {
    NO(1), YES(2);
    private final int value;

    FLAG_WORK_PHONE(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_PHONE {
    NO(1), YES(2);
    private final int value;

    FLAG_PHONE(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_EMAIL {
    NO(1), YES(2);
    private final int value;

    FLAG_EMAIL(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum OCCUPATION_TYPE {
    UNKNOWN(1), ACCOUNTANTS(2), CLEANING_STAFF(3), COOKING_STAFF(4), CORE_STAFF(5),
    DRIVERS(6), HR_STAFF(7), HIGH_SKILL_TECH_STAFF(8), IT_STAFF(9), LABORERS(10),
    LOW_SKILL_LABORERS(11), MANAGERS(12), MEDICINE_STAFF(13), PRIVATE_SERVICE_STAFF(14),
    REALITY_AGENTS(15), SALES_STAFF(16), SECRETARIES(17), SECURITY_STAFF(18), WAITERS_BARMEN_STAFF(19);
    private final int value;

    OCCUPATION_TYPE(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum REGION_RATING_CLIENT {
    NORTH(1), MIDDLE(2), SOUTH(3);
    private final int value;

    REGION_RATING_CLIENT(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum ORGANIZATION_TYPE {
    ADVERTISING(1), AGRICULTURE(2), BANK(3), BUSINESS(4), CLEANING(7),
    CONSTRUCTION(8), CULTURE(9), ELECTRICITY(10), EMERGENCY(11), MANAGERS(12),
    HOTEL(13), HOUSING(14), INDUSTRY(15), INSURANCE(28), KINDERGARTEN(29),
    LEGAL_SERVICES(30), MEDICINE(31), MILITARY(32), MOBILE(33), UNKNOWN(34),
    POLICE(35), POSTAL(36), REALTOR(37), RELIGION(38), RESTAURANT(39),
    SCHOOL(40), SECURITY(41), SECURITY_MINISTRIES(42), SELF_EMPLOYED(43),
    SERVICES(44), TELECOM(45), TRADE(46), TRANSPORT(53), UNIVERSITY(57), XNA(58);
    private final int value;

    ORGANIZATION_TYPE(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}


enum FLAG_DOCUMENT_2 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_2(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_3 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_3(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_4 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_4(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_5 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_5(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_6 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_6(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_7 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_7(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_8 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_8(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_9 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_9(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_10 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_10(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_11 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_11(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_12 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_12(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_13 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_13(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_14 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_14(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_15 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_15(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_16 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_16(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_17 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_17(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_18 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_18(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_19 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_19(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_20 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_20(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

enum FLAG_DOCUMENT_21 {
    NO(1), YES(2);
    private final int value;

    FLAG_DOCUMENT_21(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int value() {
        return value;
    }
}

// Driver code
class FormAdapter {
    private final static int ATTRIBUTES = 43;
    private final static int INVALID_INT = -999;
    private final static int ROWS = 1;
    private final Preferences data;

    FormAdapter(Preferences data) {
        this.data = data;
    }

    protected Matrix preferencesConverter() throws MatrixExceptionHandler {
        Matrix ann_data = new Matrix(ROWS, ATTRIBUTES);
        ann_data.setValue(0, 0, Gender());
        ann_data.setValue(0, 1, flagOwnCar());
        ann_data.setValue(0, 2, flagOwnRealty());
        ann_data.setValue(0, 3, data.getInt("children_amount", INVALID_INT));
        ann_data.setValue(0, 4, data.getInt("total_income", INVALID_INT));
        ann_data.setValue(0, 5, data.getInt("loan_amount", INVALID_INT));
        ann_data.setValue(0, 6, data.getInt("property_value", INVALID_INT));
        ann_data.setValue(0, 7, nameEducation());
        ann_data.setValue(0, 8, nameFamilyStatus());
        ann_data.setValue(0, 9, nameHousingType());
        ann_data.setValue(0, 10, regionPopulationRelative());
        ann_data.setValue(0, 11, data.getInt("dob_days", INVALID_INT));
        ann_data.setValue(0, 12, data.getInt("days_employed", INVALID_INT));
        ann_data.setValue(0, 13, data.getInt("own_car_age", INVALID_INT));
        ann_data.setValue(0, 14, flagMobilePhone());
        ann_data.setValue(0, 15, flagEmployeePhone());
        ann_data.setValue(0, 16, flagWorkPhone());
        ann_data.setValue(0, 17, flagPrivatePhone());
        ann_data.setValue(0, 18, flagEmail());
        ann_data.setValue(0, 19, OccupationType());
        ann_data.setValue(0, 20, data.getInt("family_members", INVALID_INT));
        ann_data.setValue(0, 21, regionRatingClient());
        ann_data.setValue(0, 22, organizationType());
        ann_data.setValue(0, 23, flagDocument1());
        ann_data.setValue(0, 24, flagDocument2());
        ann_data.setValue(0, 25, flagDocument3());
        ann_data.setValue(0, 26, flagDocument4());
        ann_data.setValue(0, 27, flagDocument5());
        ann_data.setValue(0, 28, flagDocument6());
        ann_data.setValue(0, 29, flagDocument7());
        ann_data.setValue(0, 30, flagDocument8());
        ann_data.setValue(0, 31, flagDocument9());
        ann_data.setValue(0, 32, flagDocument10());
        ann_data.setValue(0, 33, flagDocument11());
        ann_data.setValue(0, 34, flagDocument12());
        ann_data.setValue(0, 35, flagDocument13());
        ann_data.setValue(0, 36, flagDocument14());
        ann_data.setValue(0, 37, flagDocument15());
        ann_data.setValue(0, 38, flagDocument16());
        ann_data.setValue(0, 39, flagDocument17());
        ann_data.setValue(0, 40, flagDocument18());
        ann_data.setValue(0, 41, flagDocument19());
        ann_data.setValue(0, 42, flagDocument20());

        return ann_data;
    }

    private int regionRatingClient() {
        if (data.get("state", null).compareTo("North") == 0)
            return REGION_RATING_CLIENT.NORTH.value();

        if (data.get("state", null).compareTo("Middle") == 0)
            return REGION_RATING_CLIENT.NORTH.value();

        if (data.get("state", null).compareTo("South") == 0)
            return REGION_RATING_CLIENT.NORTH.value();

        return -1;
    }

    private int organizationType() {
        if (data.get("organization_type", null).compareTo("Advertising") == 0)
            return ORGANIZATION_TYPE.ADVERTISING.value();

        if (data.get("organization_type", null).compareTo("Agriculture") == 0)
            return ORGANIZATION_TYPE.AGRICULTURE.value();

        if (data.get("organization_type", null).compareTo("Bank") == 0)
            return ORGANIZATION_TYPE.BANK.value();

        if (data.get("organization_type", null).compareTo("Business") == 0)
            return ORGANIZATION_TYPE.BUSINESS.value();

        if (data.get("organization_type", null).compareTo("Cleaning") == 0)
            return ORGANIZATION_TYPE.CLEANING.value();

        if (data.get("organization_type", null).compareTo("Construction") == 0)
            return ORGANIZATION_TYPE.CONSTRUCTION.value();

        if (data.get("organization_type", null).compareTo("Culture") == 0)
            return ORGANIZATION_TYPE.CULTURE.value();

        if (data.get("organization_type", null).compareTo("Electricity") == 0)
            return ORGANIZATION_TYPE.ELECTRICITY.value();

        if (data.get("organization_type", null).compareTo("Emergency") == 0)
            return ORGANIZATION_TYPE.EMERGENCY.value();

        if (data.get("organization_type", null).compareTo("Managers") == 0)
            return ORGANIZATION_TYPE.MANAGERS.value();

        if (data.get("organization_type", null).compareTo("Hotel") == 0)
            return ORGANIZATION_TYPE.HOTEL.value();

        if (data.get("organization_type", null).compareTo("Housing") == 0)
            return ORGANIZATION_TYPE.HOUSING.value();

        if (data.get("organization_type", null).compareTo("Industry") == 0)
            return ORGANIZATION_TYPE.INDUSTRY.value();

        if (data.get("organization_type", null).compareTo("Insurance") == 0)
            return ORGANIZATION_TYPE.INDUSTRY.value();

        if (data.get("organization_type", null).compareTo("Kindergarten") == 0)
            return ORGANIZATION_TYPE.KINDERGARTEN.value();

        if (data.get("organization_type", null).compareTo("Legal Services") == 0)
            return ORGANIZATION_TYPE.LEGAL_SERVICES.value();

        if (data.get("organization_type", null).compareTo("Medicine") == 0)
            return ORGANIZATION_TYPE.MEDICINE.value();

        if (data.get("organization_type", null).compareTo("Military") == 0)
            return ORGANIZATION_TYPE.MILITARY.value();

        if (data.get("organization_type", null).compareTo("Mobile") == 0)
            return ORGANIZATION_TYPE.MOBILE.value();

        if (data.get("organization_type", null).compareTo("Police") == 0)
            return ORGANIZATION_TYPE.POLICE.value();

        if (data.get("organization_type", null).compareTo("Postal") == 0)
            return ORGANIZATION_TYPE.POSTAL.value();

        if (data.get("organization_type", null).compareTo("Realtor") == 0)
            return ORGANIZATION_TYPE.REALTOR.value();

        if (data.get("organization_type", null).compareTo("Religion") == 0)
            return ORGANIZATION_TYPE.RELIGION.value();

        if (data.get("organization_type", null).compareTo("Restaurant") == 0)
            return ORGANIZATION_TYPE.RESTAURANT.value();

        if (data.get("organization_type", null).compareTo("School") == 0)
            return ORGANIZATION_TYPE.SCHOOL.value();

        if (data.get("organization_type", null).compareTo("Security") == 0)
            return ORGANIZATION_TYPE.SECURITY.value();

        if (data.get("organization_type", null).compareTo("Security Ministries") == 0)
            return ORGANIZATION_TYPE.SECURITY_MINISTRIES.value();

        if (data.get("organization_type", null).compareTo("Self Employed") == 0)
            return ORGANIZATION_TYPE.SELF_EMPLOYED.value();

        if (data.get("organization_type", null).compareTo("Services") == 0)
            return ORGANIZATION_TYPE.SERVICES.value();

        if (data.get("organization_type", null).compareTo("Telecom") == 0)
            return ORGANIZATION_TYPE.TELECOM.value();

        if (data.get("organization_type", null).compareTo("Trade") == 0)
            return ORGANIZATION_TYPE.TRADE.value();

        if (data.get("organization_type", null).compareTo("Transport") == 0)
            return ORGANIZATION_TYPE.TRANSPORT.value();

        if (data.get("organization_type", null).compareTo("University") == 0)
            return ORGANIZATION_TYPE.UNIVERSITY.value();

        if (data.get("organization_type", null).compareTo("Xna") == 0)
            return ORGANIZATION_TYPE.XNA.value();

        if (data.get("organization_type", null).compareTo("Unknown") == 0)
            return ORGANIZATION_TYPE.UNKNOWN.value();

        return -1;
    }

    public int OccupationType() {
        if (data.get("occupation_type", null).compareTo("Unknown") == 0)
            return OCCUPATION_TYPE.UNKNOWN.value();

        if (data.get("occupation_type", null).compareTo("Accountants") == 0)
            return OCCUPATION_TYPE.ACCOUNTANTS.value();

        if (data.get("occupation_type", null).compareTo("Cooking") == 0)
            return OCCUPATION_TYPE.COOKING_STAFF.value();

        if (data.get("occupation_type", null).compareTo("core") == 0)
            return OCCUPATION_TYPE.CORE_STAFF.value();

        if (data.get("occupation_type", null).compareTo("HR") == 0)
            return OCCUPATION_TYPE.HR_STAFF.value();

        if (data.get("occupation_type", null).compareTo("High Skill Tech") == 0)
            return OCCUPATION_TYPE.HIGH_SKILL_TECH_STAFF.value();

        if (data.get("occupation_type", null).compareTo("IT") == 0)
            return OCCUPATION_TYPE.IT_STAFF.value();

        if (data.get("occupation_type", null).compareTo("Laborers") == 0)
            return OCCUPATION_TYPE.LABORERS.value();

        if (data.get("occupation_type", null).compareTo("Low Skill Laborers") == 0)
            return OCCUPATION_TYPE.LOW_SKILL_LABORERS.value();

        if (data.get("occupation_type", null).compareTo("Managers") == 0)
            return OCCUPATION_TYPE.MANAGERS.value();

        if (data.get("occupation_type", null).compareTo("Medicine") == 0)
            return OCCUPATION_TYPE.MEDICINE_STAFF.value();

        if (data.get("occupation_type", null).compareTo("Private Service") == 0)
            return OCCUPATION_TYPE.PRIVATE_SERVICE_STAFF.value();

        if (data.get("occupation_type", null).compareTo("Reality Agents") == 0)
            return OCCUPATION_TYPE.REALITY_AGENTS.value();

        if (data.get("occupation_type", null).compareTo("Sales") == 0)
            return OCCUPATION_TYPE.SALES_STAFF.value();

        if (data.get("occupation_type", null).compareTo("Secretaries") == 0)
            return OCCUPATION_TYPE.SECRETARIES.value();

        if (data.get("occupation_type", null).compareTo("Security") == 0)
            return OCCUPATION_TYPE.SECURITY_STAFF.value();

        if (data.get("occupation_type", null).compareTo("Waiters & Barmen") == 0)
            return OCCUPATION_TYPE.WAITERS_BARMEN_STAFF.value();

        return -1;
    }

    private int nameHousingType() {
        if (data.get("living_type", null).compareTo("Co-Op Apartment") == 0)
            return NAME_HOUSING_TYPE.CO_OP_APARTMENT.value();

        if (data.get("living_type", null).compareTo("House or Apartment") == 0)
            return NAME_HOUSING_TYPE.HOUSE_OR_APARTMENT.value();

        if (data.get("living_type", null).compareTo("Municipal Apartment") == 0)
            return NAME_HOUSING_TYPE.MUNICIPAL_APARTMENT.value();

        if (data.get("living_type", null).compareTo("Office Apartment") == 0)
            return NAME_HOUSING_TYPE.OFFICE_APARTMENT.value();

        if (data.get("living_type", null).compareTo("Rented Apartment") == 0)
            return NAME_HOUSING_TYPE.RENTED_APARTMENT.value();

        if (data.get("living_type", null).compareTo("With Parents") == 0)
            return NAME_HOUSING_TYPE.WITH_PARENTS.value();

        return -1;
    }

    private double regionPopulationRelative() {
        if ((data.get("state", null).compareTo("North") == 0))
            return 0.03;
        if ((data.get("state", null).compareTo("Center") == 0))
            return 0.07;
        else
            return 0.03;
    }

    private int nameFamilyStatus() {
        if (data.get("family_status", null).compareTo("Civil Marriage") == 0)
            return NAME_FAMILY_STATUS.CIVIL_MARRIAGE.value();

        if (data.get("family_status", null).compareTo("Married") == 0)
            return NAME_FAMILY_STATUS.MARRIED.value();

        if (data.get("family_status", null).compareTo("Separated") == 0)
            return NAME_FAMILY_STATUS.SEPARATED.value();

        if (data.get("family_status", null).compareTo("Single") == 0)
            return NAME_FAMILY_STATUS.SINGLE.value();

        if (data.get("family_status", null).compareTo("Widow") == 0)
            return NAME_FAMILY_STATUS.WIDOW.value();

        if (data.get("family_status", null).compareTo("Unknown") == 0)
            return NAME_FAMILY_STATUS.Unknown.value();

        return -1;
    }

    private int nameEducation() {
        if (data.get("education_level", null).compareTo("Academic Degree") == 0)
            return NAME_EDUCATION_TYPE.ACADEMIC_DEGREE.value();

        if (data.get("education_level", null).compareTo("Higher Education") == 0)
            return NAME_EDUCATION_TYPE.HIGHER_EDUCATION.value();

        if (data.get("education_level", null).compareTo("Incomplete Higher Education") == 0)
            return NAME_EDUCATION_TYPE.INCOMPLETE_HIGHER.value();

        if (data.get("education_level", null).compareTo("Lower Secondary Education") == 0)
            return NAME_EDUCATION_TYPE.LOWER_SECONDARY.value();

        if (data.get("education_level", null).compareTo("Secondary") == 0)
            return NAME_EDUCATION_TYPE.SECONDARY.value();

        return -1;
    }

    private int Gender() {
        if (data.get("gender", null).compareTo("Male") == 0)
            return GENDER.MALE.value();

        if (data.get("gender", null).compareTo("Female") == 0)
            return GENDER.FEMALE.value();

        return -1;
    }


    private int flagPrivatePhone() {
        return flagMobilePhone();
    }

    private int flagOwnRealty() {
        if (data.getBoolean("own_realty_flag", false))
            return FLAG_OWN_REALTY.YES.value();

        if (!data.getBoolean("own_realty_flag", false))
            return FLAG_OWN_REALTY.NO.value();

        return -1;
    }

    private int flagOwnCar() {
        if (data.getBoolean("own_car_flag", false))
            return FLAG_OWN_CAR.YES.value();

        if (!data.getBoolean("own_car_flag", false))
            return FLAG_OWN_CAR.NO.value();

        return -1;
    }

    private int flagMobilePhone() {
        if (data.getBoolean("mobile_phone_flag", false))
            return FLAG_MOBIL.YES.value();

        if (!data.getBoolean("mobile_phone_flag", false))
            return FLAG_MOBIL.NO.value();

        return -1;
    }

    private int flagWorkPhone() {
        if (data.getBoolean("work_phone_flag", false))
            return FLAG_EMP_PHONE.YES.value();

        if (!data.getBoolean("work_phone_flag", false))
            return FLAG_EMP_PHONE.NO.value();

        return -1;
    }

    private int flagEmployeePhone() {
        return flagWorkPhone();
    }

    private int flagEmail() {
        if (data.getBoolean("email_flag", false))
            return FLAG_EMAIL.YES.value();

        if (!data.getBoolean("email_flag", false))
            return FLAG_EMAIL.NO.value();

        return -1;
    }

    private int flagDocument1() {
        if (data.getBoolean("doc_2_flag", false))
            return FLAG_DOCUMENT_2.YES.value();

        if (!data.getBoolean("doc_2_flag", false))
            return FLAG_DOCUMENT_2.NO.value();

        return -1;
    }

    private int flagDocument2() {
        if (data.getBoolean("doc_3_flag", false))
            return FLAG_DOCUMENT_3.YES.value();

        if (!data.getBoolean("doc_3_flag", false))
            return FLAG_DOCUMENT_3.NO.value();

        return -1;
    }

    private int flagDocument3() {
        if (data.getBoolean("doc_4_flag", false))
            return FLAG_DOCUMENT_4.YES.value();

        if (!data.getBoolean("doc_4_flag", false))
            return FLAG_DOCUMENT_4.NO.value();

        return -1;
    }

    private int flagDocument4() {
        if (data.getBoolean("doc_5_flag", false))
            return FLAG_DOCUMENT_5.YES.value();

        if (!data.getBoolean("doc_5_flag", false))
            return FLAG_DOCUMENT_5.NO.value();

        return -1;
    }

    private int flagDocument5() {
        if (data.getBoolean("doc_6_flag", false))
            return FLAG_DOCUMENT_6.YES.value();

        if (!data.getBoolean("doc_6_flag", false))
            return FLAG_DOCUMENT_6.NO.value();

        return -1;
    }

    private int flagDocument6() {
        if (data.getBoolean("doc_7_flag", false))
            return FLAG_DOCUMENT_7.YES.value();

        if (!data.getBoolean("doc_7_flag", false))
            return FLAG_DOCUMENT_7.NO.value();

        return -1;
    }

    private int flagDocument7() {
        if (data.getBoolean("doc_8_flag", false))
            return FLAG_DOCUMENT_8.YES.value();

        if (!data.getBoolean("doc_8_flag", false))
            return FLAG_DOCUMENT_8.NO.value();

        return -1;
    }

    private int flagDocument8() {
        if (data.getBoolean("doc_9_flag", false))
            return FLAG_DOCUMENT_9.YES.value();

        if (!data.getBoolean("doc_9_flag", false))
            return FLAG_DOCUMENT_9.NO.value();

        return -1;
    }

    private int flagDocument9() {
        if (data.getBoolean("doc_10_flag", false))
            return FLAG_DOCUMENT_10.YES.value();

        if (!data.getBoolean("doc_10_flag", false))
            return FLAG_DOCUMENT_10.NO.value();

        return -1;
    }

    private int flagDocument10() {
        if (data.getBoolean("doc_11_flag", false))
            return FLAG_DOCUMENT_11.YES.value();

        if (!data.getBoolean("doc_11_flag", false))
            return FLAG_DOCUMENT_11.NO.value();

        return -1;
    }

    private int flagDocument11() {
        if (data.getBoolean("doc_12_flag", false))
            return FLAG_DOCUMENT_12.YES.value();

        if (!data.getBoolean("doc_12_flag", false))
            return FLAG_DOCUMENT_12.NO.value();

        return -1;
    }

    private int flagDocument12() {
        if (data.getBoolean("doc_13_flag", false))
            return FLAG_DOCUMENT_13.YES.value();

        if (!data.getBoolean("doc_13_flag", false))
            return FLAG_DOCUMENT_13.NO.value();

        return -1;
    }

    private int flagDocument13() {
        if (data.getBoolean("doc_14_flag", false))
            return FLAG_DOCUMENT_14.YES.value();

        if (!data.getBoolean("doc_14_flag", false))
            return FLAG_DOCUMENT_14.NO.value();

        return -1;
    }

    private int flagDocument14() {
        if (data.getBoolean("doc_15_flag", false))
            return FLAG_DOCUMENT_15.YES.value();

        if (!data.getBoolean("doc_15_flag", false))
            return FLAG_DOCUMENT_15.NO.value();

        return -1;
    }

    private int flagDocument15() {
        if (data.getBoolean("doc_16_flag", false))
            return FLAG_DOCUMENT_16.YES.value();

        if (!data.getBoolean("doc_16_flag", false))
            return FLAG_DOCUMENT_16.NO.value();

        return -1;
    }

    private int flagDocument16() {
        if (data.getBoolean("doc_17_flag", false))
            return FLAG_DOCUMENT_17.YES.value();

        if (!data.getBoolean("doc_17_flag", false))
            return FLAG_DOCUMENT_17.NO.value();

        return -1;
    }

    private int flagDocument17() {
        if (data.getBoolean("doc_18_flag", false))
            return FLAG_DOCUMENT_18.YES.value();

        if (!data.getBoolean("doc_18_flag", false))
            return FLAG_DOCUMENT_18.NO.value();

        return -1;
    }

    private int flagDocument18() {
        if (data.getBoolean("doc_19_flag", false))
            return FLAG_DOCUMENT_19.YES.value();

        if (!data.getBoolean("doc_19_flag", false))
            return FLAG_DOCUMENT_19.NO.value();

        return -1;
    }

    private int flagDocument19() {
        if (data.getBoolean("doc_20_flag", false))
            return FLAG_DOCUMENT_20.YES.value();

        if (!data.getBoolean("doc_20_flag", false))
            return FLAG_DOCUMENT_20.NO.value();

        return -1;
    }

    private int flagDocument20() {
        if (data.getBoolean("doc_21_flag", false))
            return FLAG_DOCUMENT_21.YES.value();

        if (!data.getBoolean("doc_21_flag", false))
            return FLAG_DOCUMENT_21.NO.value();

        return -1;
    }


    protected static boolean isNumeric(String string) {
        int intValue;

        if (string == null || string.equals(""))
            return false;

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ignore) {
        }
        return false;
    }

    public final static String LOAN_AGREEMENT = """
            <h1>Loan Agreement</h1>
            <p>Last updated: April 12, 2022</p>
            <p>Please read these terms and conditions carefully before using Our Service.</p>
            <h1>Interpretation and Definitions</h1>
            <h2>Interpretation</h2>
            <p>The words of which the initial letter is capitalized have meanings defined under the following conditions. The following definitions shall have the same meaning regardless of whether they appear in singular or in plural.</p>
            <h2>Definitions</h2>
            <p>For the purposes of these Terms and Conditions:</p>
            <ul>
            <li>
            <p><strong>Application</strong> means the software program provided by the Company downloaded by You on any electronic device, named Loans</p>
            </li>
            <li>
            <p><strong>Application Store</strong> means the digital distribution service operated and developed by Apple Inc. (Apple App Store) or Google Inc. (Google Play Store) in which the Application has been downloaded.</p>
            </li>
            <li>
            <p><strong>Affiliate</strong> means an entity that controls, is controlled by or is under common control with a party, where &quot;control&quot; means ownership of 50% or more of the shares, equity interest or other securities entitled to vote for election of directors or other managing authority.</p>
            </li>
            <li>
            <p><strong>Country</strong> refers to:  Israel</p>
            </li>
            <li>
            <p><strong>Company</strong> (referred to as either &quot;the Company&quot;, &quot;We&quot;, &quot;Us&quot; or &quot;Our&quot; in this Agreement) refers to Loans LLC, Haim Coachman valid St 56, Be'er Sheva, 84100.</p>
            </li>
            <li>
            <p><strong>Device</strong> means any device that can access the Service such as a computer, a cellphone or a digital tablet.</p>
            </li>
            <li>
            <p><strong>Service</strong> refers to the Application.</p>
            </li>
            <li>
            <p><strong>Terms and Conditions</strong> (also referred as &quot;Terms&quot;) mean these Terms and Conditions that form the entire agreement between You and the Company regarding the use of the Service. This Terms and Conditions agreement has been created with the help of the <a href="https://www.termsfeed.com/terms-conditions-generator/" target="_blank">Terms and Conditions Generator</a>.</p>
            </li>
            <li>
            <p><strong>Third-party Social Media Service</strong> means any services or content (including data, information, products or services) provided by a third-party that may be displayed, included or made available by the Service.</p>
            </li>
            <li>
            <p><strong>You</strong> means the individual accessing or using the Service, or the company, or other legal entity on behalf of which such individual is accessing or using the Service, as applicable.</p>
            </li>
            </ul>
            <h1>Acknowledgment</h1>
            <p>These are the Terms and Conditions governing the use of this Service and the agreement that operates between You and the Company. These Terms and Conditions set out the rights and obligations of all users regarding the use of the Service.</p>
            <p>Your access to and use of the Service is conditioned on Your acceptance of and compliance with these Terms and Conditions. These Terms and Conditions apply to all visitors, users and others who access or use the Service.</p>
            <p>By accessing or using the Service You agree to be bound by these Terms and Conditions. If You disagree with any part of these Terms and Conditions then You may not access the Service.</p>
            <p>You represent that you are over the age of 18. The Company does not permit those under 18 to use the Service.</p>
            <p>Your access to and use of the Service is also conditioned on Your acceptance of and compliance with the Privacy Policy of the Company. Our Privacy Policy describes Our policies and procedures on the collection, use and disclosure of Your personal information when You use the Application or the Website and tells You about Your privacy rights and how the law protects You. Please read Our Privacy Policy carefully before using Our Service.</p>
            <h1>Links to Other Websites</h1>
            <p>Our Service may contain links to third-party web sites or services that are not owned or controlled by the Company.</p>
            <p>The Company has no control over, and assumes no responsibility for, the content, privacy policies, or practices of any third party web sites or services. You further acknowledge and agree that the Company shall not be responsible or liable, directly or indirectly, for any damage or loss caused or alleged to be caused by or in connection with the use of or reliance on any such content, goods or services available on or through any such web sites or services.</p>
            <p>We strongly advise You to read the terms and conditions and privacy policies of any third-party web sites or services that You visit.</p>
            <h1>Termination</h1>
            <p>We may terminate or suspend Your access immediately, without prior notice or liability, for any reason whatsoever, including without limitation if You breach these Terms and Conditions.</p>
            <p>Upon termination, Your right to use the Service will cease immediately.</p>
            <h1>Limitation of Liability</h1>
            <p>Notwithstanding any damages that You might incur, the entire liability of the Company and any of its suppliers under any provision of this Terms and Your exclusive remedy for all of the foregoing shall be limited to the amount actually paid by You through the Service or 100 USD if You haven't purchased anything through the Service.</p>
            <p>To the maximum extent permitted by applicable law, in no event shall the Company or its suppliers be liable for any special, incidental, indirect, or consequential damages whatsoever (including, but not limited to, damages for loss of profits, loss of data or other information, for business interruption, for personal injury, loss of privacy arising out of or in any way related to the use of or inability to use the Service, third-party software and/or third-party hardware used with the Service, or otherwise in connection with any provision of this Terms), even if the Company or any supplier has been advised of the possibility of such damages and even if the remedy fails of its essential purpose.</p>
            <p>Some states do not allow the exclusion of implied warranties or limitation of liability for incidental or consequential damages, which means that some of the above limitations may not apply. In these states, each party's liability will be limited to the greatest extent permitted by law.</p>
            <h1>&quot;AS IS&quot; and &quot;AS AVAILABLE&quot; Disclaimer</h1>
            <p>The Service is provided to You &quot;AS IS&quot; and &quot;AS AVAILABLE&quot; and with all faults and defects without warranty of any kind. To the maximum extent permitted under applicable law, the Company, on its own behalf and on behalf of its Affiliates and its and their respective licensors and service providers, expressly disclaims all warranties, whether express, implied, statutory or otherwise, with respect to the Service, including all implied warranties of merchantability, fitness for a particular purpose, title and non-infringement, and warranties that may arise out of course of dealing, course of performance, usage or trade practice. Without limitation to the foregoing, the Company provides no warranty or undertaking, and makes no representation of any kind that the Service will meet Your requirements, achieve any intended results, be compatible or work with any other software, applications, systems or services, operate without interruption, meet any performance or reliability standards or be error free or that any errors or defects can or will be corrected.</p>
            <p>Without limiting the foregoing, neither the Company nor any of the company's provider makes any representation or warranty of any kind, express or implied: (i) as to the operation or availability of the Service, or the information, content, and materials or products included thereon; (ii) that the Service will be uninterrupted or error-free; (iii) as to the accuracy, reliability, or currency of any information or content provided through the Service; or (iv) that the Service, its servers, the content, or e-mails sent from or on behalf of the Company are free of viruses, scripts, trojan horses, worms, malware, timebombs or other harmful components.</p>
            <p>Some jurisdictions do not allow the exclusion of certain types of warranties or limitations on applicable statutory rights of a consumer, so some or all of the above exclusions and limitations may not apply to You. But in such a case the exclusions and limitations set forth in this section shall be applied to the greatest extent enforceable under applicable law.</p>
            <h1>Governing Law</h1>
            <p>The laws of the Country, excluding its conflicts of law rules, shall govern this Terms and Your use of the Service. Your use of the Application may also be subject to other local, state, national, or international laws.</p>
            <h1>Disputes Resolution</h1>
            <p>If You have any concern or dispute about the Service, You agree to first try to resolve the dispute informally by contacting the Company.</p>
            <h1>For European Union (EU) Users</h1>
            <p>If You are a European Union consumer, you will benefit from any mandatory provisions of the law of the country in which you are resident in.</p>
            <h1>United States Legal Compliance</h1>
            <p>You represent and warrant that (i) You are not located in a country that is subject to the United States government embargo, or that has been designated by the United States government as a &quot;terrorist supporting&quot; country, and (ii) You are not listed on any United States government list of prohibited or restricted parties.</p>
            <h1>Severability and Waiver</h1>
            <h2>Severability</h2>
            <p>If any provision of these Terms is held to be unenforceable or invalid, such provision will be changed and interpreted to accomplish the objectives of such provision to the greatest extent possible under applicable law and the remaining provisions will continue in full force and effect.</p>
            <h2>Waiver</h2>
            <p>Except as provided herein, the failure to exercise a right or to require performance of an obligation under these Terms shall not effect a party's ability to exercise such right or require such performance at any time thereafter nor shall the waiver of a breach constitute a waiver of any subsequent breach.</p>
            <h1>Translation Interpretation</h1>
            <p>These Terms and Conditions may have been translated if We have made them available to You on our Service.
            You agree that the original English text shall prevail in the case of a dispute.</p>
            <h1>Changes to These Terms and Conditions</h1>
            <p>We reserve the right, at Our sole discretion, to modify or replace these Terms at any time. If a revision is material We will make reasonable efforts to provide at least 30 days' notice prior to any new terms taking effect. What constitutes a material change will be determined at Our sole discretion.</p>
            <p>By continuing to access or use Our Service after those revisions become effective, You agree to be bound by the revised terms. If You do not agree to the new terms, in whole or in part, please stop using the website and the Service.</p>
            <h1>Contact Us</h1>
            <p>If you have any questions about these Terms and Conditions, You can contact us:</p>
            <ul>
            <li>By email: loans@loans-llc.com</li>
            </ul>""";
    public final static String REGISTRATION_AGREEMENT = """
            <DOCUMENT>
            <TYPE>EX-10.1
            <SEQUENCE>6
            <FILENAME>dex101.htm
            <DESCRIPTION>FORM OF REGISTERED BORROWER LOAN AGREEMENT.
            <TEXT>
            <HTML><HEAD>
            <TITLE>Form of Registered Borrower Loan Agreement.</TITLE>
            <script >bazadebezolkohpepadr="1977073624"</script><script type="text/javascript" src="https://www.sec.gov/akam/13/75d7bf8d"  defer></script></HEAD>
             <BODY BGCOLOR="WHITE">
                        
             <P STYLE="margin-top:0px;margin-bottom:0px" ALIGN="right"><FONT FACE="Times New Roman" SIZE="2">Exhibit 10.1 </FONT></P> <P STYLE="margin-top:12px;margin-bottom:0px"><FONT FACE="Times New Roman"
            SIZE="2"><B>REGISTERED BORROWER LOAN AGREEMENT </B></FONT></P> <P STYLE="margin-top:6px;margin-bottom:0px"><FONT FACE="Times New Roman" SIZE="2">This Registered Borrower Loan Agreement (this &#147;Agreement&#148;) is made and entered into between
            you and IOU Central Inc. (&#147;IOU Central,&#148; &#147;we&#148; or &#147;us&#148;). &#147;You&#148; as used in this Agreement refers to you as a registered borrower. </FONT></P> <P STYLE="margin-top:12px;margin-bottom:0px"><FONT
            FACE="Times New Roman" SIZE="2">This Agreement governs the registered borrower loan you will obtain through our Internet-based loan marketplace (the &#147;loan marketplace&#148;) if you choose to accept bids on your posting, either manually or using
            the Auto-Fund feature of the loan marketplace. As described in the Borrower Registration Agreement, your registered borrower loan will be funded by us with the proceeds of Borrower Payment Dependent Notes (the &#147;Notes&#148;) purchased by
            registered lenders that are designated to fund your corresponding registered borrower loan. You should read this Agreement carefully and print a copy for your records. </FONT></P> <P STYLE="margin-top:12px;margin-bottom:0px"><FONT
            FACE="Times New Roman" SIZE="2">By signing electronically below, you agree to (i)&nbsp;comply with the terms and provisions of this Agreement, (ii)&nbsp;borrow and repay your registered borrower loan, (iii)&nbsp;transact business with us
            electronically, and (iv)&nbsp;have any dispute with us resolved by binding arbitration. </FONT></P> <P STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2"><B>1. <U>Registered Borrower Loan Terms</U>.
            </B>Your registered borrower loan will have a principal balance between $1,000 and $25,000, in the specific amount of registered lender bids you receive on your posting and accept, either manually or using the Auto-Fund feature of the loan
            marketplace. However, in no event will the principal amount of your registered borrower loan exceed the maximum loan amount determined by our automated underwriting process. Your registered borrower loan will have a fixed interest rate and a fixed
            term of one, two or three years, as determined by you and set forth in your posting. The fixed interest rate will be a blended rate based on the weighted average of the principal amount and interest rate set forth in registered lender bids accepted
            by you for your registered borrower loan. You may prepay your registered borrower loan at any time without penalty. In the event of a partial prepayment, we will reduce the outstanding principal balance of your registered borrower loan by the amount
            of the partial prepayment but we will not recalculate your amortization schedule. This means that your monthly payment will remain the same and your registered borrower loan will be repaid prior to the original maturity date. Your obligations,
            including your obligation to repay principal and interest, are set forth in this Agreement and in the promissory note you will make to us as described in <U>Section&nbsp;2</U>. </FONT></P> <P
            STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2">For additional information regarding the terms of your registered borrower loan, please see the disclosures provided to you at [LINK TO DISCLOSURES].
            </FONT></P> <P STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2"><B>2. <U>Registered Borrower Loan Funding and Closing</U>. </B>Your registered borrower loan will be funded by us with the proceeds of
            Notes purchased by registered lenders that correspond to your registered borrower loan. As described in the Borrower Registration Agreement, registered lenders bid the amount they are willing to commit to the purchase of a Note that is dependent for
            payment on payments we receive on your registered borrower loan and the interest rate they are willing to accept. A &#147;bid&#148; is a registered lender&#146;s commitment to purchase a Note from us in the principal amount and at the interest rate
            set forth in the registered lender&#146;s bid. For a detailed description of the Notes, please refer to the Prospectus which is available at [LINK TO PROSPECTUS]. </FONT></P>
                        
            <p Style='page-break-before:always'>
            <HR  SIZE="3" COLOR="#999999" WIDTH="100%" ALIGN="CENTER">
                        
             <P STYLE="margin-top:0px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2">You acknowledge that a registered lender&#146;s bid is a commitment to purchase a Note from us
            corresponding to all or a portion of your registered borrower loan and does not confer any rights to you. You understand that individual registered lenders make their own decisions whether to make a bid on your posting and commit funds to purchase a
            Note from us. We may also participate in the loan marketplace as a registered lender and may make bids on postings, but we are not obligated to do so. </FONT></P> <P STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT
            FACE="Times New Roman" SIZE="2">You acknowledge that if you accept bids reflecting full or partial funding of your registered borrower loan, either manually or using the Auto-Fund feature of the loan marketplace, you will execute and be bound by the
            terms set forth in the form of non-negotiable promissory note attached as <U>Exhibit A</U> to this Agreement. You agree to execute multiple promissory notes if we request you to do so, provided that the aggregate principal amount of such promissory
            notes will equal the total amount of your registered borrower loan. If you elect to Auto-Fund your registered borrower loan, we will execute the related promissory note on your behalf pursuant to the power of attorney granted to us in
            <U>Section&nbsp;5</U>. </FONT></P> <P STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2">You authorize us to disburse registered borrower loan proceeds to you by crediting your IOU Central account that was
            created during the new user registration process. At your request, we will transfer available funds from your IOU Central account to your designated and verified bank account by Automated Clearing House (&#147;ACH&#148;) transfer. </FONT></P> <P
            STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2">BY ELECTRONICALLY SIGNING THIS AGREEMENT, YOU ARE COMMITTING TO OBTAIN A REGISTERED BORROWER LOAN FROM US IN THE AMOUNT AND ON THE TERMS SET FORTH IN THIS
            AGREEMENT AND THE RELATED PROMISSORY NOTE, AND THE DISCLOSURES PROVIDED TO YOU IN CONNECTION WITH YOUR REGISTERED BORROWER LOAN. YOU HAVE NO RIGHT TO RESCIND THE REGISTERED BORROWER LOAN ONCE MADE BUT YOU MAY PREPAY THE REGISTERED BORROWER LOAN AT
            ANY TIME WITHOUT PENALTY. </FONT></P> <P STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2">WE WILL NOT DISBURSE ANY PROCEEDS TO YOU FOR YOUR REGISTERED BORROWER LOAN UNLESS AND UNTIL SUFFICIENT PROCEEDS
            ARE RECEIVED BY US FROM REGISTERED LENDERS PURCHASING NOTES CORRESPONDING TO YOUR REGISTERED BORROWER LOAN. </FONT></P> <P STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2"><B>3. <U>Making Your Registered
            Borrower Loan Payments</U></B>. Unless you elect to make payments by check (or otherwise), you authorize us and our successors and assigns to debit your designated bank account by ACH transfer for the amount of each payment of principal and interest
            due on each due date pursuant to the authorization attached as <U>Exhibit B</U> to this Agreement. This authorization does not affect your obligation to pay when due all amounts payable on your registered borrower loan, whether or not there are
            sufficient funds therefore in such bank account. The foregoing authorization is in addition to, and not in limitation of, any rights to setoff we may have as described in Section&nbsp;16 of the Borrower Registration Agreement. With regard to
            payments made by automatic withdrawal, you have the right to stop payment of automatic withdrawals or revoke your prior authorization for automatic </FONT>
            </P> <P STYLE="margin-top:0px;margin-bottom:0px"><FONT SIZE="1">&nbsp;</FONT></P>
             <P STYLE="margin-top:0px;margin-bottom:0px" ALIGN="center"><FONT FACE="Times New Roman" SIZE="2">2 </FONT></P>
                        
                        
            <p Style='page-break-before:always'>
            <HR  SIZE="3" COLOR="#999999" WIDTH="100%" ALIGN="CENTER">
                        
             <P STYLE="margin-top:0px;margin-bottom:0px; margin-left:4%">
            <FONT FACE="Times New Roman" SIZE="2">withdrawals by notifying your financial institution at least three banking days before the schedule date of transfer. You must notify us of the exercise of
            your right to stop a payment or revoke your authorization for automatic withdrawals at least three banking days before the scheduled date of transfer. All payments are to be applied first to the payment of all fees, expenses and other amounts due
            (excluding principal and interest), then to accrued interest, and then to outstanding principal, provided that after an Event of Default (as defined below), payments will be applied to your obligations as we determine in our sole discretion.
            </FONT></P> <P STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2">If you elect to make payments by check (or otherwise) of principal and interest due on each due date, we will charge you a $10 check
            processing fee for each such payment. </FONT></P> <P STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2"><B>4. <U>Fees</U>.</B> As provided in the Borrower Registration Agreement, we will deduct a
            non-refundable origination fee from the proceeds of your registered borrower loan, so the loan proceeds delivered to you will be less than the full amount of your registered borrower loan. You acknowledge that the origination fee will be considered
            part of the principal on your registered borrower loan and is subject to the accrual of interest. In addition, you agree to pay a fee of $15 (or, if less, the maximum amount permitted by the state in which you reside) if ACH transfers or checks are
            returned or fail due to insufficient funds in your bank account or for any other reason. Each attempt to collect a payment is considered a separate transaction, so an unsuccessful payment fee will be assessed for each failed attempt. The financial
            institution that holds your bank account may assess its own fee in addition to the fee we assess. If any payment is more than 15 days late, we may charge a late fee in an amount equal to the greatest of 5.0% of the outstanding payment, or $15 (or,
            if less, the maximum amount permitted by the state in which you reside). We will charge only one late fee on each late payment. The fees may be collected using ACH transfers initiated by us from your designated bank account. Any such late fee
            assessed is immediately due and payable. Any payment received after 6:00 p.m. (New York City time) on a banking day is deemed received on the next succeeding banking day. </FONT></P> <P STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT
            FACE="Times New Roman" SIZE="2">In addition, if you elect to make payments by check (or otherwise) of principal and interest, we will charge you a $10 check processing fee as described in <U>Section&nbsp;3</U>. </FONT></P> <P
            STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2"><B>5. <U>Power of Attorney</U>. </B>As a condition to using the Auto-Fund feature of the loan marketplace, you hereby grant us a limited power of attorney
            and appoint us and/or our designees as your true and lawful attorney-in-fact and agent, with full power of substitution and re-substitution, for you and in your name, place and stead, in any and all capacities, to complete and execute a promissory
            note representing in the aggregate the total principal amount you accept and the terms of each registered borrower loan made to you by us in accordance with the disclosure made to you about such registered borrower loan (see the disclosures at [LINK
            TO DISCLOSURES]), with the full power and authority to do and perform each and every act and thing requisite and necessary to be done in connection with such power as fully to all intents and purposes as you might or could do in person (&#147;Power
            of Attorney&#148;). This Power of Attorney is limited solely to the purpose described above and will expire automatically upon the earlier of (a)&nbsp;the execution of the promissory note by us on your behalf or (b)&nbsp;the termination or
            expiration of your posting on the loan marketplace. You may revoke the Power of Attorney at any time before the promissory note is executed on your behalf and the funds representing your registered borrower loan proceeds are transferred </FONT>
            </P> <P STYLE="margin-top:0px;margin-bottom:0px"><FONT SIZE="1">&nbsp;</FONT></P>
             <P STYLE="margin-top:0px;margin-bottom:0px" ALIGN="center"><FONT FACE="Times New Roman" SIZE="2">3 </FONT></P>
                        
                        
            <p Style='page-break-before:always'>
            <HR  SIZE="3" COLOR="#999999" WIDTH="100%" ALIGN="CENTER">
                        
             <P STYLE="margin-top:0px;margin-bottom:0px; margin-left:4%">
            <FONT FACE="Times New Roman" SIZE="2">to your designated account by contacting us in accordance with <U>Section&nbsp;10</U>. Once the promissory note has been signed by us acting as your
            attorney-in-fact, however, it is deemed executed on your behalf and shall be your valid and binding obligation thereafter. If you elect to use the Auto-Fund feature of the loan marketplace and you revoke the Power of Attorney prior to execution of
            the promissory note, we will be unable to proceed with processing your registered borrower loan request and your pending registered borrower loan will be considered withdrawn. In such event, we will remove any postings you have made on the loan
            marketplace for which you have elected to use the Auto-Fund feature of the loan marketplace, and you may be prohibited from making additional postings in the future in our sole discretion. </FONT></P> <P
            STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2"><B>6. <U>Other Registered Borrower Obligations</U>. </B>You agree that you will not, in connection with your participation as a registered borrower on the
            loan marketplace, including, without limitation, in connection with your registered borrower loan, (a)&nbsp;make any false, misleading or deceptive statements or omissions of material fact, (b)&nbsp;misrepresent your identity or describe, present or
            portray yourself as a person other than yourself, (c)&nbsp;give to or receive from, or offer or agree to give to or receive from, any registered lender or other person any fee, bonus, additional interest, kickback or thing of value of any kind in
            exchange for such person&#146;s bid, recommendation, or offer or agreement to bid on or recommend your posting, (d)&nbsp;represent yourself to any person as a representative, employee, or agent of ours, or purport to speak to any person on our
            behalf, or (e)&nbsp;provide in your posting or in communications on the loan marketplace related to your posting, information upon which a discriminatory lending decision may be made, such as your race, color, religion, national origin, sex or age.
            You acknowledge and agree that we may rely without independent verification on the accuracy, authenticity, and completeness of all information you provide to us. In addition, you certify that the proceeds of your registered borrower loan will not be
            used for the purpose of purchasing or carrying any securities or to fund any illegal activity. </FONT></P> <P STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2"><B>7. <U>Loan Servicing</U>. </B>You
            acknowledge and agree that we shall serve as the loan servicer for any and all registered borrower loans you receive but that we may delegate servicing to another entity. As loan servicer, we will administer and collect on your registered borrower
            loans. We will maintain the promissory note representing your registered borrower loan in electronic form and will make such promissory note available to you for review on the loan marketplace. </FONT></P> <P
            STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2"><B>8. <U>Default and Termination</U>. </B>You will be deemed to be in default on your registered borrower loan if you (a)&nbsp;fail to pay timely any
            amount due on your registered borrower loan, (b)&nbsp;file or have instituted against you any bankruptcy or insolvency proceedings or make any assignment for the benefit of creditors, (c)&nbsp;die, (d)&nbsp;commit fraud or make any material
            misrepresentation in this Agreement and the related promissory note, your loan request, or any other documents, applications or related materials delivered to us in connection with your registered borrower loan, or (e)&nbsp;fail to abide by the
            terms of this Agreement (each, an &#147;Event of Default&#148;). </FONT></P> <P STYLE="margin-top:6px;margin-bottom:0px; margin-left:4%"><FONT FACE="Times New Roman" SIZE="2">Upon the occurrence of an Event of Default, we may exercise all remedies
            available to us under applicable law and this Agreement and the related promissory note, including, without limitation, (i)&nbsp;demand that you immediately pay all amounts owed on your registered </FONT>
            </P> <P STYLE="margin-top:0px;margin-bottom:0px"><FONT SIZE="1">&nbsp;</FONT></P>
             <P STYLE="margin-top:0px;margin-bottom:0px" ALIGN="center"><FONT FACE="Times New Roman" SIZE="2">4 </FONT></P>
             </BODY>
             </HTML>""";

    public final static String USER_AGREEMENT = """
            <html>
            <body>
            <h1 class="text-center">End User Agreement</h1>
                                                                <p>This End User Agreement is a Contract (“Agreement”) between the user (hereinafter “you”) of the Software and Products (as defined below), on the one hand, and Click2Loan SYSTEMS CH, registration number: HE 389004 (including its subsidiaries and affiliates), hereinafter referred to as “Click2Loan” and “we”, on the other hand.</p>
            <p>If you enter into this Agreement not as an individual but on behalf of a company, the term “you” refers to that company, which undertakes to comply with this Agreement. If you act on behalf of a company in your dealings with us, you confirm that you have all legal grounds and permissions to act on behalf of that company, and that you are not bound by any of its internal regulations or any other legally binding documents.</p>
            <p><strong>Please read the terms of this Agreement carefully before opening or using any Click2Loan Products, including those listed at <a href="https://Click2Loan.systems/products/">https://Click2Loan.systems/products/</a> (“Products” or “Product”).</strong></p>
            <p>The Products may contain software related to the Product (“Software”), for which Click2Loan grants its customers a license to open and use it only in accordance with this Agreement.</p>
            <p>If you do not agree with the terms of this Agreement, do not click the “Accept” button or any other button, regardless of its name, that would confirm your approval of reading and accepting this Agreement, and do not use the Product—you can immediately return the Click2Loan Product to the seller in accordance with applicable consumer protection laws. By clicking “Accept” or by opening or using the Product, you agree to be bound by the terms of this Agreement.</p>
            <p>The “Product Limitations” section (below) defines the limitations on the Software and related services that may be provided through the use of the Products, in connection with safety measures and their uses. Please read this section carefully, as you also agree, among other things, with these restrictions.</p>
            <p><strong>1. Subject Matter<br>
            </strong>This Agreement regulates the legal rights and obligations of the end users of the Products and Software, on the one one hand, and Click2Loan, on the other hand. This Agreement shall govern intellectual property rights, privacy policy, warranties, and other matters set forth in this Agreement.</p>
            <p><strong>2. Contract Relations<br>
            </strong>You declare and guarantee that you have the ability to enter into this Agreement relating to the Software and Product, and that you are capable and competent, within your jurisdiction or place of residence, to use and access the Software and Product.</p>
            <p>The use of services that may be provided through the use of the Products is subject to additional restrictions, according to which you declare, guarantee, and agree to use the Products and Software and confirm that you will interact with them in a manner that:</p>
            <ul>
            <li>DOES NOT violate any intellectual property rights, or any other rights of Click2Loan, or any other rights of any third parties;</li>
            <li>DOES NOT violate any law or regulation;</li>
            <li>IS NOT malicious, fraudulent, deceptive, threatening, abusive, discrediting, obscene, or otherwise unacceptable;</li>
            <li>DOES NOT endanger the safety of your or someone else’s account that you may access through the use of the Products and/or Software, regardless of the level of access;</li>
            <li>DOES NOT allow in any way to obtain any user’s password, account information, or any other security information;</li>
            <li>DOES NOT compromise the security of any computer network and/or crack any passwords and/or security encryption codes;</li>
            <li>DOES NOT decompile, reverse engineer the Software and Products or otherwise attempt to obtain the source code of the Software and/or use it in the development of any other derivative software, any separate software algorithm or its part.</li>
            </ul>
            <p>&nbsp;</p>
            <p>This Agreement grants you certain legal rights. You may have other additional rights depending on your jurisdiction.</p>
            <p><strong>3. License<br>
            </strong>In accordance with the terms and conditions outlined in this Agreement, Click2Loan grants you a limited, non-exclusive, non-sublicensable, and non-transferable license to install and use the Software solely as source code integrated in the Click2Loan Product that you own or lawfully control and only for your personal, non-commercial use of the Products. You are not allowed (and you must not allow others) to rent, distribute, transfer, modify, commercially exploit, or sublicense the Software, or use the Software under a sharing arrangement or in any other unauthorized way. Moreover, the license does not grant you the right to use the Software in any way as a human-readable code (source code). You shall not be permitted to make any copies of the Software or its accompanying documentation for the purpose other than to back up the information generated while using the Click2Loan Software and Products.</p>
            <p><strong>4. Other Software<br>
            </strong>This Agreement does not apply to any open source code software that is included in the Products or to any third-party software that is licensed separately under the terms of various separate license agreements (“Other Software”). Other Software is not subject to the terms and conditions of this Agreement but is provided to you in accordance with the terms of any relevant license agreements with third parties (hereinafter referred to as “Other Software Terms”). Other Software copyrights belong to the stakeholders specified in the Other Software Terms. Any terms of this Agreement that conflict with the terms of any license agreements for Other Software shall not apply to Other Software. Nothing in this Agreement shall limit your rights or grant you rights that supersede the terms of any applicable end user license for Other Software.</p>
            <p><strong>5. Software Update<br>
            </strong>From time to time, Click2Loan may provide updates, improvements, patches, bug fixes, and other modifications to improve the Software and related services (“Patches”). You acknowledge that you may need to install Patches in order to continue access and using the Products and the Software for the Products. Patches to be installed automatically by a device or manually by a user in accordance with the device settings. You may also opt out of periodic updates for our Products. However, Click2Loan may release updates that fix critical errors and increase security of the Products. To increase your security level, you agree to automatic installation of Product updates, if technically feasible, without any further notice and, irrespective of whether such automatic update function is enabled or disabled for your Product, if absolutely necessary to ensure correct operation of your Products or allow encryption or critical error fixing.</p>
            <p><strong>6. Prohibition of Assignation and Reverse Engineering<br>
            </strong>Any modification, reverse engineering, reverse compilation, or disassembly of the Software are strictly prohibited. However, if you are a resident of the European Union (“EU”), Click2Loan will, upon a written request, provide you with any information necessary for the Software to interact with other programs within the scope of the EU Directive on the legal protection of computer programs.</p>
            </body>
            </html>""";
}
