import Controller.SocialMediaController;
import io.javalin.Javalin;


//manually run and test

public class Main {
    public static void main(String[] args) {
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);
    }
}
