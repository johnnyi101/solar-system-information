package solar;

public class FakeWSTrue implements IAstroService {
    @Override
    public boolean authenticate(String userID, String password) {
        return true;
    }

    @Override
    public String getStatusInfo(String astronomicalObjectClassificationCode) {
        return null;
    }
}
