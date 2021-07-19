package XML;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * @Author: Rita
 * XPath寻址
 * XML文档是树结构的节点集合。
 *
 * XPath使用路径符号在XML中寻址节点。
 *
 * 正斜杠/是路径分隔符。
 * 文档根目录的绝对路径以/开头。
 * 相对路径可以从任何其他开始。
 * 双重周期..表示父节点。
 * 单个期间.表示当前节点。
 * 在XPath /h1/h2 中选择位于h1元素下的所有h2元素。
 *
 * 要选择一个特定的h2元素，我们使用方括号 [] 来索引。
 *
 * 例如， /h1[4]/h2 [5] 将选择第五个 h2 在第四个 h1 元素下。
 *
 * 要引用属性，请在属性名称前加上@符号。例如， @type 是指 type 属性。
 *
 * h1/@type选择 h1 元素的 type 属性。
 */
public class XMLTransformTest {
    //以下代码显示了如何使用Stax解析器转换xml。
    @Test
    public void test21() throws Exception {
        String [] args = {};
        SchemaFactory sf = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        System.out.println("schema factory instance obtained is " + sf);

        Schema schema = sf.newSchema(new File(args[0]));
        System.out.println("schema obtained is = " + schema);
        Validator validator = schema.newValidator();

        String fileName = args[1].toString();
        String fileName2 = args[2].toString();
        javax.xml.transform.Result xmlResult = new javax.xml.transform.stax.StAXResult(
                XMLOutputFactory.newInstance().createXMLStreamWriter(
                        new FileWriter(fileName2)));
        javax.xml.transform.Source xmlSource = new javax.xml.transform.stax.StAXSource(
                getXMLEventReader(fileName));
        validator.validate(new StreamSource(args[1]));
        validator.validate(xmlSource, xmlResult);
    }
    private static XMLEventReader getXMLEventReader(String filename)
            throws Exception {
        XMLInputFactory xmlif = null;
        XMLEventReader xmlr = null;
        xmlif = XMLInputFactory.newInstance();
        xmlif.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES,
                Boolean.TRUE);
        xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,
                Boolean.FALSE);
        xmlif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.TRUE);
        xmlif.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);

        FileInputStream fis = new FileInputStream(filename);
        xmlr = xmlif.createXMLEventReader(filename, fis);

        return xmlr;
    }
    //以下代码使用 DOMSource 作为变换输入。
    @Test
    public void test22() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new InputStreamReader(new FileInputStream(
                "inputFile.xml"))));

        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.setOutputProperty(OutputKeys.METHOD, "xml");
        xformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xformer.setOutputProperty("http://xml.apache.org/xslt;indent-amount", "4");
        xformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("result.xml"));
        xformer.transform(source, result);
    }
    //以下代码显示了如何使用XPath更改特定元素
    @Test
    public void test23() throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
                new InputSource("data.xml"));

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList) xpath.evaluate("//employee/name[text()='old']", doc,
                XPathConstants.NODESET);

        for (int idx = 0; idx < nodes.getLength(); idx++) {
            nodes.item(idx).setTextContent("new value");
        }
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(new DOMSource(doc), new StreamResult(new File("data_new.xml")));
    }
}



class TreeDumper {
    public void dump(Document doc) {
        dumpLoop((Node)doc,"");
    }
    private void dumpLoop(Node node,String indent) {
        switch(node.getNodeType()) {
            case Node.CDATA_SECTION_NODE:
                System.out.println(indent + "CDATA_SECTION_NODE");
                break;
            case Node.COMMENT_NODE:
                System.out.println(indent + "COMMENT_NODE");
                break;
            case Node.DOCUMENT_FRAGMENT_NODE:
                System.out.println(indent + "DOCUMENT_FRAGMENT_NODE");
                break;
            case Node.DOCUMENT_NODE:
                System.out.println(indent + "DOCUMENT_NODE");
                break;
            case Node.DOCUMENT_TYPE_NODE:
                System.out.println(indent + "DOCUMENT_TYPE_NODE");
                break;
            case Node.ELEMENT_NODE:
                System.out.println(indent + "ELEMENT_NODE");
                break;
            case Node.ENTITY_NODE:
                System.out.println(indent + "ENTITY_NODE");
                break;
            case Node.ENTITY_REFERENCE_NODE:
                System.out.println(indent + "ENTITY_REFERENCE_NODE");
                break;
            case Node.NOTATION_NODE:
                System.out.println(indent + "NOTATION_NODE");
                break;
            case Node.PROCESSING_INSTRUCTION_NODE:
                System.out.println(indent + "PROCESSING_INSTRUCTION_NODE");
                break;
            case Node.TEXT_NODE:
                System.out.println(indent + "TEXT_NODE");
                break;
            default:
                System.out.println(indent + "Unknown node");
                break;
        }

        NodeList list = node.getChildNodes();
        for(int i=0; i<list.getLength(); i++) {
            dumpLoop(list.item(i),indent + "   ");
        }

    }
}
