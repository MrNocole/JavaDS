import java.io.*;
import java.util.*;

public class Person implements Serializable {
    private static final long serialVersionUID = 114514;
    List<Map.Entry<Character,String>> list;
    HashMap<Character,String> map;
    private String name;
    private int age;
    PriorityQueue<TreeNode> priorityQueue;
    public Person(String string, int age){
        priorityQueue = new PriorityQueue<TreeNode>();
        list = new ArrayList<>();
        this.name = string;
        this.age = age;
    }
    public static Person des(byte[] bin) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bai = new ByteArrayInputStream(bin);
        ObjectInputStream ois = new ObjectInputStream(bai);
        return (Person) ois.readObject();
    }
    public byte[] serBin(Person person){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(person);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
    public boolean serToFile(Person person, String path, String file){
        try {
            byte[] list = serBin(person);
            System.out.println(Arrays.toString(list));
            FileOutputStream fos = new FileOutputStream(path + file);
            fos.write(list);
            return true;
        }catch (IOException e){
            return false;
        }
    }
    public static Person desFromFile(String path, String file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path+file);
        byte[] list = fis.readAllBytes();
        return des(list);
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Person person = new Person("zt",21);
//        System.out.println(person.serToFile(person,"C:\\Users\\19262\\IdeaProjects\\JavaDS\\Sers\\","person.ser"));
        Person person1 = Person.desFromFile("C:\\Users\\19262\\IdeaProjects\\JavaDS\\Sers\\","person.ser");
        System.out.println(person1.list.size());
    }

}
