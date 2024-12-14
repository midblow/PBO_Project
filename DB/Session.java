package DB;

public class Session {
    public static String loggedInUserEmail = null; // Untuk menyimpan email pengguna yang login
    public static int loggedInUserId = -1; // Untuk menyimpan ID pengguna (jika diperlukan)
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
