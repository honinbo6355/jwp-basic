package next.handler;

import next.controller.*;
import org.h2.command.dml.Update;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static final Map<String, Controller> controllerMap = new HashMap<>();

    static {
        controllerMap.put("/", new HomeController());
        controllerMap.put("/users/form", new ForwardController("/user/form.jsp"));
        controllerMap.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        controllerMap.put("/users/create", new CreateUserController());
        controllerMap.put("/users", new ListUserController());
        controllerMap.put("/users/login", new LoginController());
        controllerMap.put("/users/logout", new LogoutController());
        controllerMap.put("/users/profile", new ProfileController());
        controllerMap.put("/users/update", new UpdateUserController());
    }

    public static Controller getController(String path) {
        return controllerMap.getOrDefault(path, null);
    }
}
