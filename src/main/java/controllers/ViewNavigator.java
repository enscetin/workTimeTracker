package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * ViewNavigator, JavaFX uygulamasında sahne (Scene) yönetimini merkezi bir şekilde yapmayı sağlar.
 * Scene tek bir kez oluşturulur, geçişlerde sadece kök düğüm (root node) değiştirilir.
 * Ayrıca controller nesnesine erişim sağlayarak sayfalar arası veri aktarımını da kolaylaştırır.
 */
public class ViewNavigator {
    private static Scene mainScene;

    /**
     * Uygulamanın ana sahnesini ayarlar. Genellikle Application.start() içinde bir kere çağrılır.
     * @param scene JavaFX sahnesi
     */
    public static void setScene(Scene scene) {
        mainScene = scene;
    }

    /**
     * Belirtilen FXML dosyasını yükler ve kök düğümünü sahneye yerleştirir.
     * @param fxmlPath Yüklenecek FXML dosyasının yolu
     */
    public static void switchTo(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(ViewNavigator.class.getResource(fxmlPath));
            mainScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Belirtilen FXML dosyasını yükler, controller nesnesine erişim sağlar ve root'u sahneye yerleştirir.
     * Bu yöntem sayesinde sayfalar arası veri aktarımı mümkündür.
     *
     * @param fxmlPath Yüklenecek FXML dosyasının yolu
     * @param controllerConsumer Controller nesnesini alan fonksiyonel arayüz (lambda fonksiyonu vb.)
     * @param <T> Controller türü (FXML'e bağlı olan controller sınıfı)
     */
    public static <T> void switchToWithController(String fxmlPath, Consumer<T> controllerConsumer) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewNavigator.class.getResource(fxmlPath));
            Parent root = loader.load();
            T controller = loader.getController();
            controllerConsumer.accept(controller); // Data transfer over Controller
            mainScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
