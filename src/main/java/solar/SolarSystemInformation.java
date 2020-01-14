package solar;

public class SolarSystemInformation
{
private String userID;
private String objectType;
private String objectName;

    public SolarSystemInformation(String userID)
    {
        this.userID = userID;
        if(userID.length()==6 && userID.matches("/^[A-Z]{2}/") && userID.matches("\\d{4} $")){
           setObjectType("Valid");
           setObjectName("Valid");
        }
        else
            setObjectName("Not Allowed");
            setObjectType("Not Allowed");


    }
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
}
