package solar;

public class FakeWSTrue implements IAstroService {
    @Override
    public boolean authenticate(String userID, String password) {
        return true;
    }

    @Override
    public String getStatusInfo(String astronomicalObjectClassificationCode) {
        if(astronomicalObjectClassificationCode == "SSun27TL") {
            return "SSun27TL,Earth,23,23,23,23";
        }
        else{
            return "SSun27TL,Earth,23,23,23,a";
        }
    }
}
