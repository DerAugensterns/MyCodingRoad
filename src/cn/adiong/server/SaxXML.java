package cn.adiong.server;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: 阿威
 * @Date: 2020/8/29 19:51
 * @Description： 流解析xml文档 （对应dom——document object model）
 */
public class SaxXML {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        /**
         * 1、获取解析工厂
         */
        SAXParserFactory factory = SAXParserFactory.newInstance();
        /**
         * 2、从解析工厂获取解析器
         */
        SAXParser parser = factory.newSAXParser();
        /**
         * 3、编写文档处理器
         */
        /**
         * 4、加载处理器
         */
        PersonHandler personHandler = new PersonHandler();
        /**
         * 5、解析 (注意文件路径——从当前线程类加载器中获取)
         */
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("cn/adiong/server/person.xml"),
                personHandler);

        /**
         * 获取容器中的数据
         */
        List<Person> persons = personHandler.getPersons();
        for (Person p : persons
        ) {
            System.out.println(p.getName() + "---->" + p.getAge());
        }
    }
}

class PersonHandler extends DefaultHandler {

    private String tag;
    private List<Person> persons;
    private Person person;

    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public void startDocument() throws SAXException {
        persons = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName != null) {
            tag = qName;
            if (qName.equals("person")) {
                person = new Person();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("person")) {
            persons.add(person);
        }
        //丢弃原来的标签，防止数据被覆盖
        tag = null;
    }

//    @Override
//    public void endDocument() throws SAXException {
//
//    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contents = new String(ch, start, length).trim();
        if (tag != null) {
            if (contents.length() > 0) {
                if (tag.equals("name")) {
                    person.setName(contents);
                } else if (tag.equals("age")) {
                    person.setAge(Integer.valueOf(contents));
                }
            }
        }
    }
}

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
