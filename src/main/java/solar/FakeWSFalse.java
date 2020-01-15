package solar;

public class FakeWSFalse implements IAstroService {
    @Override
    public boolean authenticate(String userID, String password) {
        return false;
    }

    @Override
    public String getStatusInfo(String astronomicalObjectClassificationCode) {
        return null;
    }
}

