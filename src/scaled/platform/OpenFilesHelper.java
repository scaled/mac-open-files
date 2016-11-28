package scaled.platform;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.apple.eawt.AppEvent;
import com.apple.eawt.Application;
import com.apple.eawt.OpenFilesHandler;

public class OpenFilesHelper {

  public interface Listener {
    void openFiles (List<Path> files);
  }

  public static final boolean IS_MACOS = System.getProperty("os.name").contains("OS X");

  public static List<Path> launchFiles = new ArrayList<>();

  public static void init () {
    if (IS_MACOS) {
      Application macApp = Application.getApplication();
      macApp.setOpenFileHandler(new OpenFilesHandler() {
        public void openFiles (AppEvent.OpenFilesEvent event) {
          if (listener != null) listener.openFiles(toPaths(event.getFiles()));
          else launchFiles = toPaths(event.getFiles());
        }
      });
    }
  }

  public static void setListener (Listener newListener) {
    listener = newListener;
  }

  private static List<Path> toPaths (List<File> files) {
    ArrayList<Path> paths = new ArrayList<>();
    for (File file : files) paths.add(file.toPath());
    return paths;
  }

  private static Listener listener = null;
}
