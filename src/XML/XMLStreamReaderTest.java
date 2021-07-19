package XML;

import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Rita
 *Java XML教程 - Java StAX
 * StAX是为了解决SAX和DOM API中的限制而创建的。
 *
 * StAX API允许我们请求下一个事件(拉动事件)，并允许状态以过程方式存储。
 *
 * XML解析有两种编程模型:流和文档对象模型(DOM)。
 *
 * DOM模型涉及创建表示整个文档树的内存对象。DOM树可以自由导航。 成本是一个大的内存占用。这对于小文档来说是可以的，但是当文档变得更大时，内存消耗可能会迅速上升。
 *
 * 流是指其中XML数据被串行解析的编程模型。在文档中，我们只能在一个位置查看XML数据。这意味着我们需要在读取XML文档之前知道XML结构。用于XML处理的流模型在存在内存限制时很有用。

拉式解析vs推式解析
当我们想获取（拉取）XML数据时，我们做流式拉解析。

当解析器发送数据时，无论客户端是否准备好使用它，我们都进行流式推送解析。

StAX拉解析器可以过滤XML文档并忽略元素不必要。

StAX是一个双向API，通过它我们可以读取和写入XML文档。 SAX是只读的。

SAX是一个推送API，而StAX是拉式。

 */
public class XMLStreamReaderTest implements javax.xml.stream.StreamFilter{
    //此程序演示如何使用StAX解析器。它打印XHTML网页的所有超链接链接。
    @Test
    public void test1() throws IOException, XMLStreamException {
        URL url = new URL("");
        InputStream in = url.openStream();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(in);
        while (parser.hasNext()) {
            int event = parser.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (parser.getLocalName().equals("a")) {
                    String href = parser.getAttributeValue(null, "href");
                    if (href != null) {
                        System.out.println(href);
                    }
                }
            }
        }
    }
    //下面的代码显示了如何使用XML流读取器加载XML文档
    @Test
    public void test2() throws FileNotFoundException, XMLStreamException {
        File file = new File("yourXML.xml");
        FileInputStream inputStream = new FileInputStream(file);
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        System.out.println(reader.getVersion());
        System.out.println(reader.isStandalone());
        System.out.println(reader.standaloneSet());
        System.out.println(reader.getEncoding());
        System.out.println(reader.getCharacterEncodingScheme());

        parseRestOfDocument(reader);
    }
    private static void parseRestOfDocument(XMLStreamReader reader)
            throws XMLStreamException {
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    System.out.println(reader.getLocalName());
                    if (reader.getNamespaceURI() != null) {
                        String prefix = reader.getPrefix();
                        if (prefix == null) {
                            prefix = "[None]";
                        }
                        System.out.println(reader.getNamespaceURI());
                        System.out.println("prefix = " + prefix + ", URI = "
                                + reader.getNamespaceURI() );
                    }

                    if (reader.getAttributeCount() > 0) {
                        for (int i = 0; i < reader.getAttributeCount(); i++) {
                            System.out.println("Attribute (name = "
                                    + reader.getAttributeLocalName(i) + ", value = "
                                    + reader.getAttributeValue(i) + ")");
                            String attURI = reader.getAttributeNamespace(i);
                            if (attURI != null) {
                                String attPrefix = reader.getAttributePrefix(i);
                                if (attPrefix == null || attPrefix.equals("")) {
                                    attPrefix = "[None]";
                                }
                                System.out.println("prefix=" + attPrefix + ",URI=" + attURI);
                            }
                        }
                    }

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    System.out.println("XMLStreamConstants.END_ELEMENT");
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if (!reader.isWhiteSpace()) {
                        System.out.println("CD:" + reader.getText());
                    }
                    break;
                case XMLStreamConstants.DTD:
                    System.out.println("DTD:" + reader.getText());
                    break;
                case XMLStreamConstants.SPACE:
                    System.out.println(" ");
                    break;
                case XMLStreamConstants.COMMENT:
                    System.out.println(reader.getText());
                    break;
                default:
                    System.out.println(type);
            }
        }
    }
    //XMLStreamWriter
    //以下代码显示了如何使用 XMLStreamWriter 输出xml。
    @Test
    public void test3() throws XMLStreamException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(System.out);

        writer.writeStartDocument("1.0");

        writer.writeStartElement("catalog");

        writer.writeStartElement("book");

        writer.writeAttribute("id", "1");

        writer.writeStartElement("code");
        writer.writeCharacters("I01");
        writer.writeEndElement();

        writer.writeStartElement("title");
        writer.writeCharacters("This is the title");
        writer.writeEndElement();

        writer.writeStartElement("price");
        writer.writeCharacters("$2.95");
        writer.writeEndElement();

        writer.writeEndDocument();

        writer.flush();
        writer.close();
    }
    //XMLEventReader
    @Test
    public void test4() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        Reader fileReader = new FileReader("Source.xml");
        XMLEventReader reader = factory.createXMLEventReader(fileReader);

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                StartElement element = (StartElement) event;
                System.out.println("Start Element: " + element.getName());

                Iterator iterator = element.getAttributes();
                while (iterator.hasNext()) {
                    Attribute attribute = (Attribute) iterator.next();
                    QName name = attribute.getName();
                    String value = attribute.getValue();
                    System.out.println("Attribute name/value: " + name + "/" + value);
                }
            }
            if (event.isEndElement()) {
                EndElement element = (EndElement) event;
                System.out.println("End element:" + element.getName());
            }
            if (event.isCharacters()) {
                Characters characters = (Characters) event;
                System.out.println("Text: " + characters.getData());
            }
        }
    }
    //XMLEventWriter
    @Test
    public void test5() throws XMLStreamException {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        XMLEventWriter writer = outputFactory.createXMLEventWriter(System.out);

        XMLEventFactory xmlEventFactory = XMLEventFactory.newInstance();

        StartDocument startDocument = xmlEventFactory.createStartDocument("UTF-8", "1.0");
        writer.add(startDocument);

        StartElement startElement = xmlEventFactory.createStartElement("", "", "My-list");
        writer.add(startElement);

        Attribute attribute = xmlEventFactory.createAttribute("version", "1");
        List attributeList = Arrays.asList(attribute);
        List nsList = Arrays.asList();
        StartElement startElement2 = xmlEventFactory.createStartElement("", "", "Item",
                attributeList.iterator(), nsList.iterator());
        writer.add(startElement2);

        StartElement codeSE = xmlEventFactory.createStartElement("", "", "code");
        writer.add(codeSE);
        Characters codeChars = xmlEventFactory.createCharacters("I001");
        writer.add(codeChars);
        EndElement codeEE = xmlEventFactory.createEndElement("", "", "code");
        writer.add(codeEE);

        StartElement nameSE = xmlEventFactory.createStartElement(" ", " ", "name");
        writer.add(nameSE);
        Characters nameChars = xmlEventFactory.createCharacters("a name");
        writer.add(nameChars);
        EndElement nameEE = xmlEventFactory.createEndElement("", "", "name");
        writer.add(nameEE);

        StartElement contactSE = xmlEventFactory.createStartElement("", "", "contact");
        writer.add(contactSE);
        Characters contactChars = xmlEventFactory.createCharacters("another name");
        writer.add(contactChars);
        EndElement contactEE = xmlEventFactory.createEndElement("", "", "contact");
        writer.add(contactEE);

        EndDocument ed = xmlEventFactory.createEndDocument();
        writer.add(ed);

        writer.flush();
        writer.close();
    }
    //StreamFilter
    @Test
    public void test6() throws FileNotFoundException, XMLStreamException {
        String filename = "yourXML.xml";

        XMLInputFactory xmlif = null;

        xmlif = XMLInputFactory.newInstance();
        xmlif.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES,
                Boolean.TRUE);
        xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,
                Boolean.FALSE);
        xmlif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.TRUE);
        xmlif.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);

        System.out.println("FACTORY: " + xmlif);
        System.out.println("filename = " + filename);

        FileInputStream fis = new FileInputStream(filename);

        XMLStreamReader xmlr = xmlif.createFilteredReader(
                //javax.xml.stream.StreamFilter
                xmlif.createXMLStreamReader(fis), new XMLStreamReaderTest());

        int eventType = xmlr.getEventType();
        printEventType(eventType);

        while (xmlr.hasNext()) {
            eventType = xmlr.next();
            printEventType(eventType);
            printName(xmlr, eventType);
            printText(xmlr);

            if (xmlr.isStartElement()) {
                printAttributes(xmlr);
            }
            printPIData(xmlr);
        }
    }
    public static final String getEventTypeString(int eventType) {
        switch (eventType) {
            case XMLEvent.START_ELEMENT:
                return "START_ELEMENT";

            case XMLEvent.END_ELEMENT:
                return "END_ELEMENT";

            case XMLEvent.PROCESSING_INSTRUCTION:
                return "PROCESSING_INSTRUCTION";

            case XMLEvent.CHARACTERS:
                return "CHARACTERS";

            case XMLEvent.COMMENT:
                return "COMMENT";

            case XMLEvent.START_DOCUMENT:
                return "START_DOCUMENT";

            case XMLEvent.END_DOCUMENT:
                return "END_DOCUMENT";

            case XMLEvent.ENTITY_REFERENCE:
                return "ENTITY_REFERENCE";

            case XMLEvent.ATTRIBUTE:
                return "ATTRIBUTE";

            case XMLEvent.DTD:
                return "DTD";

            case XMLEvent.CDATA:
                return "CDATA";
        }

        return "UNKNOWN_EVENT_TYPE";
    }

    private static void printEventType(int eventType) {
        System.out.print("EVENT TYPE(" + eventType + "):");
        System.out.println(getEventTypeString(eventType));
    }

    private static void printName(XMLStreamReader xmlr, int eventType) {
        if (xmlr.hasName()) {
            System.out.println("HAS NAME: " + xmlr.getLocalName());
        } else {
            System.out.println("HAS NO NAME");
        }
    }

    private static void printText(XMLStreamReader xmlr) {
        if (xmlr.hasText()) {
            System.out.println("HAS TEXT: " + xmlr.getText());
        } else {
            System.out.println("HAS NO TEXT");
        }
    }

    private static void printPIData(XMLStreamReader xmlr) {
        if (xmlr.getEventType() == XMLEvent.PROCESSING_INSTRUCTION) {
            System.out.println(" PI target = " + xmlr.getPITarget());
            System.out.println(" PI Data = " + xmlr.getPIData());
        }
    }

    private static void printAttributes(XMLStreamReader xmlr) {
        if (xmlr.getAttributeCount() > 0) {
            System.out.println("\nHAS ATTRIBUTES: ");

            int count = xmlr.getAttributeCount();

            for (int i = 0; i < count; i++) {
                QName name = xmlr.getAttributeName(i);
                String namespace = xmlr.getAttributeNamespace(i);
                String type = xmlr.getAttributeType(i);
                String prefix = xmlr.getAttributePrefix(i);
                String value = xmlr.getAttributeValue(i);

                System.out.println("ATTRIBUTE-PREFIX: " + prefix);
                System.out.println("ATTRIBUTE-NAMESP: " + namespace);
                System.out.println("ATTRIBUTE-NAME:   " + name.toString());
                System.out.println("ATTRIBUTE-VALUE:  " + value);
                System.out.println("ATTRIBUTE-TYPE:  " + type);
            }
        } else {
            System.out.println("HAS NO ATTRIBUTES");
        }
    }

    @Override
    public boolean accept(XMLStreamReader reader) {
        if (!reader.isStartElement() && !reader.isEndElement()) {
            return false;
        } else {
            return true;
        }
    }



}
