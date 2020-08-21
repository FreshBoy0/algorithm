import java.io.*;
import java.lang.reflect.Constructor;

/**
 * @author 31855
 */
public class InstanceObject implements Cloneable, Serializable {

    private Integer id;
    private String name;
    private String sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    protected InstanceObject clone() throws CloneNotSupportedException {
        return (InstanceObject) super.clone();
    }



    @Override
    public String toString() {
        return "InstanceObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
    public InstanceObject(Integer id,String name,String sex){
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
    public InstanceObject(){
    }

    public static void main(String[] args) throws Exception {
        //使用new关键字创建对象
        System.out.println("使用new关键字创建对象");
        InstanceObject stu1 = new InstanceObject(1,"张三","男");
        System.out.println(stu1);
        System.out.println("************************");
        System.out.println();
        System.out.println();
        //反射创建对象
        System.out.println("使用Constructor类的newInstance方法创建对象");
        Constructor<InstanceObject> constructor = InstanceObject.class.getConstructor(Integer.class,String.class,String.class);
        InstanceObject stu2 = constructor.newInstance(1,"李四","男");
        System.out.println(stu2);
        System.out.println("************************");
        System.out.println();
        System.out.println();
        System.out.println("使用Class类的newInstance方法创建对象");
        InstanceObject stu3 = InstanceObject.class.newInstance();
        stu3.setId(3);
        stu3.setName("王五");
        stu3.setSex("男");
        System.out.println(stu3);
        System.out.println("************************");
        System.out.println();
        System.out.println();
        //序列化创建对象
        System.out.println("序列化创建对象");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("student.bin"));
        outputStream.writeObject(stu1);
        outputStream.close();
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(
                "student.bin"));
        InstanceObject stu4 = (InstanceObject) input.readObject();
        System.out.println(stu4);
        System.out.println("************************");




    }













}
