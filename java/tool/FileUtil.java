package tool;

public class FileUtil {

	public static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	}
}
