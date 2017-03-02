package com.success;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

public class Demo {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Foo.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        unmarshaller.setEventHandler(new ValidationEventHandler() {
            @Override
            public boolean handleEvent(ValidationEvent ve) {
                System.out.println(ve.getMessage());
                return true;
            }
        });
        StringReader xml = new StringReader("<foo><bar>false</bar></foo>");
        Foo foo = (Foo) unmarshaller.unmarshal(xml);

        System.out.println(foo.isBar());
    }

}
