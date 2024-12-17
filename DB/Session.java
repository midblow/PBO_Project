package DB;

public class Session {
    public static String loggedInUserEmail = null; 
    public static int loggedInUserId = -1; 
    public static String loggedInProviderEmail = null; 
    public static int loggedInProviderId = -1; 

    public static void clearSession() {
        loggedInUserEmail = null;
        loggedInUserId = -1;
    }

    public static void clearSessionProv() {
        loggedInProviderEmail = null;
        loggedInProviderId = -1;
    }

}
