package sequrity.config;

import java.util.*;

public class SecurityConfig {

    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EMPLOYEE = "CLIENT";

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {

        // Configure For "EMPLOYEE" Role.
        List<String> urlPatterns1 = new ArrayList<String>();

//        urlPatterns1.add("/goods");
        urlPatterns1.add("/orders");

        mapConfig.put(ROLE_EMPLOYEE, urlPatterns1);

        // Configure For "MANAGER" Role.
        List<String> urlPatterns2 = new ArrayList<String>();

//        urlPatterns2.add("/goods");
        urlPatterns2.add("/clients");
        urlPatterns2.add("/orders");

        mapConfig.put(ROLE_MANAGER, urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}
