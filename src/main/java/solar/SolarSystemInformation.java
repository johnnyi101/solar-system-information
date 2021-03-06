package solar;
/////////////////////////////////////////////////////////////////////////////////////////////////////
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
//////////////////////////////////////////////////////////////////////////////////////////////////////
public class SolarSystemInformation {
    private String userID;
    private String password;
    private String objectType;
    private String objectName;
    private String astronomicalObjectClassificationCode;
    private Boolean exists = false;
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
    String getAOC() { return AOC; }
    String getType() { return type; }
    String getName() { return name; }
    String getOrbital() { return orbital; }
    String getRad() { return rad; }
    String getSma() { return sma; }
    String getMassS() { return massS; }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    SolarSystemInformation(String userID, String password, IAstroService mockAstroService)
    {
        this.mockAstroService = mockAstroService;
        this.userID = userID;
        this.password = password;
        if (!userID.matches("[A-Z]{2}[0-9]{4}") || userID.endsWith("0000") || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z[0-9]@$!%*?&]{10,}$"))
        {
            setObjectName("Not Allowed");
            setObjectType("Not Allowed");
            setExists(false);
            setMass(new BigDecimal(0));
            setSemiMajorAxis(new BigDecimal(0));
            setRadius(new BigDecimal(0));
            setOrbitalPeriod(0);
        }
        else
            {
                setObjectName("Valid");
                setObjectType("Valid");
        }
    }
    @Override
    public String toString() {
        return getObjectType() +", "+getObjectName()+" ["+getAstronomicalObjectClassificationCode()+"] "+getSemiMajorAxis()+" km, "+getMass()+" kg";
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
            if(returned.equals("No such astronomical object classification code"))
            {
                setAstronomicalObjectClassificationCode(returned);
                setObjectName(null);
                setObjectType(null);
                setMass(new BigDecimal(0));
                setSemiMajorAxis(new BigDecimal(0));
                setRadius(new BigDecimal(0));
                setOrbitalPeriod(0);
            }
            else if (!returned.matches("^[S,P,M,D,A,C][0-9]{0,8}[A-Z][a-z]{2}[0-9]{1,3}[T,M,B,L]{1,2}[,][A-Z][a-z ]{1,20}[,][0-9 ]*[ A-Z][a-z]{1,9}[,][0-9]*[,][0-9]*[,][0-9]*[,][0-9]*$"))
            {
                setExists(false);
                throw new InvalidFormatException("No such classification or SMA code");
            }
            else
            {
                setExists(true);
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
                setRadius(new BigDecimal(rad));
                setSemiMajorAxis(new BigDecimal(sma));
                setMass(new BigDecimal(massS));
            }
        }
    }
//////////////////////////////////////////////////////////Getters and Setters///////////////////////////////////////////
    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }
    String getObjectType() { return objectType; }
    private void setObjectType(String objectType) { this.objectType = objectType; }
    String getObjectName() { return objectName; }
    private void setObjectName(String objectName) { this.objectName = objectName; }
    public String getPassword() { return password; }
    private void setPassword(String password) { this.password = password; }
    String getAstronomicalObjectClassificationCode() { return astronomicalObjectClassificationCode; }
    private void setAstronomicalObjectClassificationCode(String astronomicalObjectClassificationCode){ this.astronomicalObjectClassificationCode = astronomicalObjectClassificationCode;}
    Boolean getExists() { return exists; }
    private void setExists (Boolean exists){ this.exists = exists; }
    int getOrbitalPeriod() { return orbitalPeriod; }
    private void setOrbitalPeriod ( int orbitalPeriod){ this.orbitalPeriod = orbitalPeriod; }
    BigDecimal getRadius() { return radius; }
    private void setRadius (BigDecimal radius){ this.radius = radius; }
    BigDecimal getSemiMajorAxis() { return semiMajorAxis; }
    private void setSemiMajorAxis (BigDecimal semiMajorAxis)
    {
        NumberFormat formatter = new DecimalFormat("0.0E0");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        formatter.setMinimumFractionDigits(2);
        this.semiMajorAxis = new BigDecimal(formatter.format(semiMajorAxis));
    }
    BigDecimal getMass() { return mass; }
    private void setMass (BigDecimal mass)
    {
        NumberFormat formatter = new DecimalFormat("0.0E0");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        formatter.setMinimumFractionDigits(2);
        this.mass = new BigDecimal(formatter.format(mass));
    }
}

