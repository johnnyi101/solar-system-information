package solar;

import java.math.BigDecimal;

public class SolarSystemInformation {
    private String userID;
    private String password;
    private String objectType;
    private String objectName;
    private String astronomicalObjectClassificationCode;
    private Boolean exists;
    private int orbitalPeriod;
    private BigDecimal radius;
    private BigDecimal semiMajorAxis;
    private BigDecimal mass;
    private IAstroService mockAstroService;
    private String AOC;
    private String type;
    private String name;
    private String orbital;
    private String rad;
    private String sma;
    private String massS;
    public String getAOC() { return AOC; }
    public String getType() { return type; }
    public String getName() { return name; }
    public String getOrbital() { return orbital; }
    public String getRad() { return rad; }
    public String getSma() { return sma; }
    public String getMassS() { return massS; }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SolarSystemInformation(String userID, String password, IAstroService mockAstroService)
    {
        this.mockAstroService = mockAstroService;
        this.userID = userID;
        this.password = password;
        if (userID.matches("[A-Z]{2}[0-9]{4}") && !userID.endsWith("0000") && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z[0-9]@$!%*?&]{10,}$"))
        {
            setObjectName("Valid");
            setObjectType("Valid");
        }
        else
            {
            setObjectName("Not Allowed");
            setObjectType("Not Allowed");
        }
    }
    void initialiseAOCDetails(String astronomicalObjectClassificationCode) throws InvalidFormatException
    {
        if (!astronomicalObjectClassificationCode.matches("[S,P,M,D,A,C][0-9]{0,8}[A-Z][a-z]{2}[0-9]{1,3}[T,M,B,L]{1,2}"))
        {
            throw new InvalidFormatException("Invalid Format");
        }
        else
            {
            setAstronomicalObjectClassificationCode(astronomicalObjectClassificationCode);

            String returned = mockAstroService.getStatusInfo(astronomicalObjectClassificationCode);
            if (!returned.matches("^[S,P,M,D,A,C][0-9]{0,8}[A-Z][a-z]{2}[0-9]{1,3}[T,M,B,L]{1,2}[,][A-Z][a-z]{1,8}[,][0-9]*[A-Z][a-z]{1,9}[,][0-9]*[,][0-9]*[,][0-9]*[,][0-9]*$"))
            {
                throw new InvalidFormatException("No such classification or SMA code");
            }
            else
            {
                String [] returnedStringArray = returned.split(",");
                AOC = returnedStringArray[0];
                type = returnedStringArray[1];
                name = returnedStringArray[2];
                orbital = returnedStringArray[3];
                rad = returnedStringArray[4];
                sma = returnedStringArray[5];
                massS = returnedStringArray[6];
                setObjectType(type);
                setObjectName(name);
                setOrbitalPeriod(Integer.parseInt(orbital));
            }
        }
    }
//////////////////////////////////////////////////////////Getters and Setters///////////////////////////////////////////
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getObjectType() {
        return objectType;
    }
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
    public String getObjectName() {
        return objectName;
    }
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAstronomicalObjectClassificationCode() { return astronomicalObjectClassificationCode;
    }
    private void setAstronomicalObjectClassificationCode(String astronomicalObjectClassificationCode){
            this.astronomicalObjectClassificationCode = astronomicalObjectClassificationCode;
    }
    public Boolean getExists () {
        return exists;
    }
    private void setExists (Boolean exists){
            this.exists = exists;
        }public int getOrbitalPeriod () { return orbitalPeriod;
    }
    private void setOrbitalPeriod ( int orbitalPeriod){
            this.orbitalPeriod = orbitalPeriod;
    }
    public BigDecimal getRadius () {
        return radius;
    }
    private void setRadius (BigDecimal radius){
        this.radius = radius;
    }
    public BigDecimal getSemiMajorAxis () {
        return semiMajorAxis;
    }
    private void setSemiMajorAxis (BigDecimal semiMajorAxis){
        this.semiMajorAxis = semiMajorAxis;
    }
    public BigDecimal getMass () {
        return mass;
    }
    private void setMass (BigDecimal mass){
        this.mass = mass;
    }
}

