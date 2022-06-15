package lk.hostelManagement.pos.util;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class NotificationController {

    public static void SuccessfulTableNotification(String option, String option2) {
        Notifications notificationBuilder = Notifications.create()
                .title(option + " Successfully.!")
                .text("Your " + option2 + " Details " + option + " is Successfully to the System.")
                .graphic(new ImageView(new Image("/lk/hostelManagement/pos/view/assets/images/done.png")))
                .hideAfter(Duration.seconds(8))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    public static void UnSuccessfulTableNotification(String option, String option2) {
        Notifications notificationBuilder = Notifications.create()
                .title(option + " UnSuccessful.!")
                .text("Your " + option2 + " Details " + option + " is Unsuccessfully to the System.")
                .graphic(new ImageView(new Image("/lk/hostelManagement/pos/view/assets/images/error.png")))
                .hideAfter(Duration.seconds(8))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }


    public static void LoginSuccessfulNotification(String option) {
        Notifications notificationBuilder = Notifications.create()
                .title(option + " Login Successful.!")
                .text("You have Successfully Login " + option + " to the System.")
                .graphic(new ImageView(new Image("/lk/hostelManagement/pos/view/assets/images/done.png")))
                .hideAfter(Duration.seconds(8))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    public static void LoginUnSuccessfulNotification(String option) {
        Notifications notificationBuilder = Notifications.create()
                .title("Login UnSuccessful.!")
                .text(option + " Not Login, Please enter Correct User Name or Password.Try Again.!")
                .graphic(new ImageView(new Image("/lk/hostelManagement/pos/view/assets/images/error.png")))
                .hideAfter(Duration.seconds(8))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    public static void Warring(String option, String option2) {
        Notifications notificationBuilder = Notifications.create()
                .title(option)
                .text(option2)
                .graphic(new ImageView(new Image("/lk/hostelManagement/pos/view/assets/images/error.png")))
                .hideAfter(Duration.seconds(8))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    public static void WarringError(String option, Object id, String option2) {
        Notifications notificationBuilder = Notifications.create()
                .title(option)
                .text(option2 + id)
                .graphic(new ImageView(new Image("/lk/hostelManagement/pos/view/assets/images/error.png")))
                .hideAfter(Duration.seconds(8))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
}
