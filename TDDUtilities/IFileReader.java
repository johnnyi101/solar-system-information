package TDDUtilities;

public interface IFileReader {
	public String getFileName();
	public void setFileName(String fileName);
	public String readLine();
	public void close();
}
