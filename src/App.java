import controller.LoginController;
import controller.RegisterController;
import view.LoginView;
import view.RegisterView;

import java.awt.EventQueue;


public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView view = new LoginView();
                RegisterView registerView = new RegisterView();
                LoginController controller = new LoginController(view, registerView);
                RegisterController registerController = new RegisterController(view,registerView);
                // hiển thị màn hình login
                controller.showLoginView();
            }
        });
    }
}