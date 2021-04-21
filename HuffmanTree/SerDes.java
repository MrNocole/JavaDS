
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

/**
 * 序列化与反序列化方法接口，包含六个方法：
 * byte[] serBin(T t)	:将对象t序列化为字节数组
 * String serText(T t)	:将对象t序列化为一个字符串（可以先使用serBin序列化为字节数组，再用Base64编码为字符串）
 * T des(byte[] bin)		:将序列化后的字节数组反序列化为一个对象
 * T des(String text)	:将序列化后的字符串反序列化为一个对象
 * serToFile				:将对象序列化并写入磁盘文件
 * desFromFile			:将序列化后的对象从磁盘文件中读出
 * @author
 *
 */
public interface SerDes<T extends Serializable> {
	
	/**
	 * 将对象t序列化为字节数组
	 * @param t
	 * @return 序列化后的字节数组
	 */
	public byte[] serBin(T t) throws IOException;
	
	/**
	 * 将对象t序列化为一个字符串。
	 * 提示：可以先使用serBin方法将对象t序列化为字节数组，
	 * 再将字节数组用Base64编码为字符串
	 * @param t
	 * @return
	 */
	public String serTxt(T t) throws IOException;
	
	/**
	 * 将序列化后的字节数组反序列化为一个对象
	 * @param bin
	 * @return
	 */
	public T des(byte[] bin) throws IOException, ClassNotFoundException;
	
	/**
	 * 将序列化后的字符串反序列化为一个对象，
	 * 字符串应该是使用serText方法序列化得到的
	 * @param text
	 * @return
	 */
	public T des(String text) throws IOException, ClassNotFoundException;
	
	/**
	 * 将对象序列化并写入磁盘文件。
	 * 提示：可以使用serBin将对象t序列化，
	 * 然后将序列化后的字节数组写入文件
	 * @param t
	 * @param path
	 * @param file
	 * @return
	 */
	public boolean serToFile(T t, String path, String file) throws IOException;

	/**
	 * 将序列化后的对象从磁盘文件中读出。
	 * 提示：可以首先从磁盘中读出字节数组，
	 * 然后使用des方法将对象反序列化
	 * @param path
	 * @param file
	 * @return
	 */
	public T desFromFile(String path, String file) throws IOException, ClassNotFoundException;
	
}