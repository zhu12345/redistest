package cn.redis.test.serializable;

import com.alibaba.fastjson.JSON;

import java.io.*;

public class Test {

    public static void main(String[] args) {
        try {
            unserializable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 反序列化
    static void unserializable() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("C:\\Users\\22984\\Downloads\\animal.txt"));
            Animal animal = (Animal) ois.readObject();
            System.out.println(JSON.toJSONString(animal));
        } finally {
            if( null != ois ){
                ois.close();
            }
        }

    }
    // 序列化
    static void serializable() throws FileNotFoundException, IOException{
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\22984\\Downloads\\animal.txt"));
            Animal animal = new Animal();
            animal.setName("Dog");
            animal.setColor("Black");
            animal.setAlias(new String[]{"xiaoHei", "Gou", "GuaiGuai"});
            oos.writeObject(animal);
            oos.flush();
        } finally {
            if(null != oos){
                oos.close();
            }
        }
    }
}
class Animal implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1822818790694831649L;
    private String name;
    private String color;
    private String[] alias;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String[] getAlias() {
        return alias;
    }
    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
