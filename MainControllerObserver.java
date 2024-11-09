package application;

public interface MainControllerObserver {
	void onButtonPressed(String buttonId);
    void onUserLoggedIn(User user);
}
