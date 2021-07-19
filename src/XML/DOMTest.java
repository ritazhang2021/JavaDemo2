package XML;

import org.junit.Test;
import org.w3c.dom.*;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.*;

/**
 * @Author: Rita
 * DOM是标准的树结构，其中每个节点包含来自XML结构的一个组件。
 * XML文档中两种最常见的节点类型是元素节点和文本节点。
 *
 * 使用Java DOM API，我们可以创建节点，删除节点，更改其内容，并遍历节点层次结构。
 *
 * 何时使用DOM
 * 文档对象模型标准是为XML文档操作而设计的。
 *
 * DOM的用意是语言无关的。Java的DOM解析器没有利用Java的面向对象的特性优势。
 */
public class DOMTest {

    //以下代码显示了如何使用DOM解析器来解析xml文件并获取一个 org.w3c.dom.Document 对象。
    @Test
    public void test1() throws ParserConfigurationException, IOException, SAXException {//或者只用Exception
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("games.xml"));
    }

    //以下代码显示如何执行DOM转储。
    @Test
    public void test2() throws ParserConfigurationException, IOException, SAXException {
        String filename = "input.xml";
        boolean validate = true;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(validate);
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);

        DocumentBuilder builder = dbf.newDocumentBuilder();
        builder.setErrorHandler(new MyErrorHandler());
        InputSource is = new InputSource(filename);
        Document doc = builder.parse(is);
        TreeDumper td = new TreeDumper();
        td.dump(doc);
    }

    //以下代码显示了如何在使用DOM解析器解析XML时处理错误。
    @Test
    public void test3(){
        boolean validate = true;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(validate);
        dbf.setNamespaceAware(true);

        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            builder.setErrorHandler(new MyErrorHandler());
            InputSource is = new InputSource("person.xml");
            Document doc = builder.parse(is);
        } catch (SAXException e) {
            System.out.println(e);
        } catch (ParserConfigurationException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    //以下代码显示了如何递归访问DOM树中的所有节点。
    @Test
    public void test4() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);

        factory.setExpandEntityReferences(false);

        Document doc = factory.newDocumentBuilder().parse(new File("file.xml"));

        visit(doc, 0);
    }

    //下面的代码显示了如何将XML片段转换为DOM片段
    @Test
    public void test5() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);

        Document doc = factory.newDocumentBuilder().parse(new File("infilename.xml"));

        String fragment = "<fragment>aaa</fragment>";

        factory = DocumentBuilderFactory.newInstance();
        Document d = factory.newDocumentBuilder().parse(new InputSource(new StringReader(fragment)));

        Node node = doc.importNode(d.getDocumentElement(), true);

        DocumentFragment docfrag = doc.createDocumentFragment();

        while (node.hasChildNodes()) {
            docfrag.appendChild(node.removeChild(node.getFirstChild()));
        }

        Element element = doc.getDocumentElement();
        element.appendChild(docfrag);

    }

    //下面的代码显示了如何解析XML字符串:使用DOM和StringReader。
    @Test
    public void test6() throws ParserConfigurationException, IOException, SAXException {
        String xmlRecords = "<data><employee><name>A</name>"
                + "<title>Manager</title></employee></data>";

        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xmlRecords));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("employee");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);

            NodeList name = element.getElementsByTagName("name");
            Element line = (Element) name.item(0);
            System.out.println("Name: " + getCharacterDataFromElement(line));

            NodeList title = element.getElementsByTagName("title");
            line = (Element) title.item(0);
            System.out.println("Title: " + getCharacterDataFromElement(line));
        }

    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }


    public static void visit(Node node, int level) {
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node childNode = list.item(i);
            visit(childNode, level + 1);
        }
    }

    //以下代码显示如何向元素添加属性。
    @Test
    public void test7() throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation impl = builder.getDOMImplementation();
        Document doc = impl.createDocument(null, null, null);
        Element e1 = doc.createElement("api");
        doc.appendChild(e1);
        Element e2 = doc.createElement("java");
        e1.appendChild(e2);

        e2.setAttribute("url", "http://www.google.com");

        //transform the DOM for showing the result in console
        DOMSource domSource = new DOMSource(doc);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        transformer.transform(domSource, sr);
        System.out.println(sw.toString());
    }

    //复制属性
    @Test
    public void test8() throws TransformerException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation impl = builder.getDOMImplementation();
        Document doc = impl.createDocument(null, null, null);

        Element root = doc.getDocumentElement();
        Element personOne = (Element)root.getFirstChild();
        Element personTwo = (Element)personOne.getNextSibling();
        Element personThree = (Element)personTwo.getNextSibling();

        Attr deptAttr = personOne.getAttributeNode("dept");
        personOne.removeAttributeNode(deptAttr);
        String deptString = deptAttr.getValue();
        personTwo.setAttribute("dept",deptString);
        personThree.setAttribute("dept",deptString);

        String mailString = personOne.getAttribute("mail");
        personTwo.setAttribute("mail",mailString);

        String titleString = personOne.getAttribute("title");
        personOne.removeAttribute("title");
        personThree.setAttribute("title",titleString);
    }
    //删除两个属性
    @Test
    public void test9() throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation impl = builder.getDOMImplementation();
        Document doc = impl.createDocument(null, null, null);

        Element root = doc.getDocumentElement();
        Element person = (Element)root.getFirstChild();
        person.removeAttribute("extension");
        person.removeAttribute("dept");
    }

    //元素
    @Test
    public void test10() throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation impl = builder.getDOMImplementation();
        Document doc = impl.createDocument(null, null, null);

        Node root = doc.createElement("A");
        doc.appendChild(root);

        Node stanza = doc.createElement("B");
        root.appendChild(stanza);

        Node line = doc.createElement("C");
        stanza.appendChild(line);
        line.appendChild(doc.createTextNode("test"));
        line = doc.createElement("Line");
        stanza.appendChild(line);
        line.appendChild(doc.createTextNode("test"));

        //transform the DOM for showing the result in console
        DOMSource domSource = new DOMSource(doc);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        transformer.transform(domSource, sr);
        System.out.println(sw.toString());
    }
    //以下代码显示如何从父代删除元素。
    @Test
    public void test11() throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation impl = builder.getDOMImplementation();
        Document doc = impl.createDocument(null, null, null);

        Element root = doc.getDocumentElement();
        Element child = (Element)root.getFirstChild();
        root.removeChild(child);
    }
    //以下代码显示如何向元素添加文本节点。
    @Test
    public void test12() throws TransformerException, ParserConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.createElementNS(null, "person"); // Create Root Element
        Element item = doc.createElementNS(null, "name"); // Create element
        item.appendChild(doc.createTextNode("Jeff"));
        root.appendChild(item); // Attach element to Root element
        item = doc.createElementNS(null, "age"); // Create another Element
        item.appendChild(doc.createTextNode("28"));
        root.appendChild(item); // Attach Element to previous element down tree
        item = doc.createElementNS(null, "height");
        item.appendChild(doc.createTextNode("1.80"));
        root.appendChild(item); // Attach another Element - grandaugther
        doc.appendChild(root); // Add Root to Document

        DOMImplementationRegistry registry = DOMImplementationRegistry
                .newInstance();
        DOMImplementationLS domImplLS = (DOMImplementationLS) registry
                .getDOMImplementation("LS");

        LSSerializer ser = domImplLS.createLSSerializer(); // Create a serializer
        // for the DOM
        LSOutput out = domImplLS.createLSOutput();
        StringWriter stringOut = new StringWriter(); // Writer will be a String
        out.setCharacterStream(stringOut);
        ser.write(doc, out); // Serialize the DOM

        System.out.println("STRXML = " + stringOut.toString()); // DOM as a String
    }

    //以下代码显示了如何通过插入和替换编辑文本
    @Test
    public void test13() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        int count;
        int offset;

        Element root = doc.getDocumentElement();
        Element place = (Element)root.getFirstChild();
        Text name = (Text)place.getFirstChild().getFirstChild();
        Text directions = (Text)place.getLastChild().getFirstChild();

        offset = 7;
        name.insertData(offset," black");

        offset = 5;
        count = 4;
        directions.replaceData(offset,count,"right");
    }
    //通过剪切和粘贴修改文本
    @Test
    public void test14() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        int length;
        int count;
        int offset;

        Element root = doc.getDocumentElement();
        Element place = (Element)root.getFirstChild();
        Text name = (Text)place.getFirstChild().getFirstChild();
        Text directions = (Text)place.getLastChild().getFirstChild();

        length = name.getLength();
        count = 4;
        offset = length - 4;
        name.deleteData(offset,count);

        length = directions.getLength();
        count = 6;
        offset = length - count;
        String bridge = directions.substringData(offset,count);

        name.appendData(bridge);

        count = 5;
        offset = 4;
        directions.deleteData(offset,count);
    }
    //通过替换修改文本
    @Test
    public void test15() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.getDocumentElement();
        Element place = (Element)root.getFirstChild();
        Text name = (Text)place.getFirstChild().getFirstChild();
        Text directions = (Text)place.getLastChild().getFirstChild();

        name.setData("AAA");
        directions.setData("BBB");
    }
    //以下代码显示如何为XML创建注释节点。
    @Test
    public void test16() throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);

        factory.setExpandEntityReferences(false);

        Document doc = factory.newDocumentBuilder().parse(new File("filename"));

        Element element = doc.getDocumentElement();
        Comment comment = doc.createComment("A Document Comment");
        element.getParentNode().insertBefore(comment, element);
    }
    //下面的代码显示了如何添加ProcessingInstruction。
    @Test
    public void test17() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.getDocumentElement();
        Element folks = (Element)root.getLastChild();
        ProcessingInstruction pi;
        pi = (ProcessingInstruction)doc.createProcessingInstruction(
                "validate",
                "phone=\"lookup\"");
    }
    //以下代码显示如何添加CDATA到XML文档
    @Test
    public void test18() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.getDocumentElement();
        Element place = (Element)root.getFirstChild();
        Element directions = (Element)place.getLastChild();
        String dirtext =
                ">>>\n" +
                        "<<<\n" +
                        "&&&\n" +
                        "<><><>.";
        CDATASection dirdata = doc.createCDATASection(dirtext);
        directions.replaceChild(dirdata,directions.getFirstChild());
    }
    //克隆
    @Test
    public void test19() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.getDocumentElement();
        Element origPerson = (Element)root.getFirstChild();
        Element newPerson = (Element)origPerson.cloneNode(true);
        root.appendChild(newPerson);
    }
    //数据XML将被转换为HTML表
    @Test
    public void test20() throws TransformerException {
        String [] args = {};
        StreamSource source = new StreamSource(args[0]);
        StreamSource stylesource = new StreamSource(args[1]);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(stylesource);

        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);
    }


}
class MyErrorHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException e) throws SAXException {
        show("Warning", e);
        throw (e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        show("Error", e);
        throw (e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        show("Fatal Error", e);
        throw (e);
    }

    private void show(String type, SAXParseException e) {
        System.out.println(type + ": " + e.getMessage());
        System.out.println("Line " + e.getLineNumber() + " Column "
                + e.getColumnNumber());
        System.out.println("System ID: " + e.getSystemId());
    }


}
