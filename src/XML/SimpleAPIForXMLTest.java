package XML;

import com.sun.tools.javac.Main;
import org.junit.Test;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * @Author: Rita
 * Java SAX XML解析器代表Simple API for XML(SAX)解析器。 *
 * SAX是一种用于访问XML文档的事件驱动的串行访问机制。 *
 * 此机制经常用于传输和接收XML文档。 *
 * SAX是一种状态独立处理，其中元素的处理不依赖于其他元素。StAX是状态相关处理。 *
 * SAX是一个事件驱动模型。 当使用SAX解析器时，我们提供了回调方法，并且解析器在读取XML数据时调用它们。 *
 * 在SAX中，我们不能回到文档的早期部分，我们只能处理元素逐个元素，从开始到结束。 *
 *
 * 何时使用SAX
 * SAX是快速和高效的，并且它对于状态无关的过滤是有用的。当遇到元素标记和时，SAX解析器调用一个方法当发现文本时调用不同的方法。 *
 * SAX比DOM要求更少的内存，因为SAX不像DOM那样创建XML数据的内部树结构。
 */
public class SimpleAPIForXMLTest {
    @Test
    public void test1() {
        String [] test = {};
        if (test.length == 0) {
            System.out.println("No file to process. Usage is:" + "\njava TrySAX <filename>");
            return;
        }
        File xmlFile = new File(String.valueOf(test));
        Myhandler handler = new Myhandler();
        handler.process(xmlFile);
    }

    public class Myhandler extends DefaultHandler {
        private SAXParser parser = null;

        public void process(File file) {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            spf.setValidating(true);
            System.out.println("Parser will " + (spf.isNamespaceAware() ? "" : "not ")
                    + "be namespace aware");
            System.out.println("Parser will " + (spf.isValidating() ? "" : "not ") + "validate XML");
            try {
                parser = spf.newSAXParser();
                System.out.println("Parser object is: " + parser);
            } catch (SAXException e) {
                e.printStackTrace(System.err);
                System.exit(1);
            } catch (ParserConfigurationException e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
            System.out.println("\nStarting parsing of " + file + "\n");
            try {
                parser.parse(file, this);
            } catch (IOException e) {
                e.printStackTrace(System.err);
            } catch (SAXException e) {
                e.printStackTrace(System.err);
            }
        }

        @Override
        public void startDocument() {
            System.out.println("Start document: ");
        }

        @Override
        public void endDocument() {
            System.out.println("End document: ");
        }

        @Override
        public void startElement(String uri, String localName, String qname, Attributes attr) {
            System.out.println("Start element: local name: " + localName + " qname: " + qname + " uri: "
                    + uri);
        }

        @Override
        public void endElement(String uri, String localName, String qname) {
            System.out.println("End element: local name: " + localName + " qname: " + qname + " uri: "
                    + uri);
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            System.out.println("Characters: " + new String(ch, start, length));
        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) {
            System.out.println("Ignorable whitespace: " + new String(ch, start, length));
        }
    }
    //错误处理程序
    //解析器可以生成三种错误:
    //致命错误
    //错误
    //警告
    //当发生致命错误时，解析器无法继续。
    //对于非致命错误和警告，默认错误处理程序不会生成异常，也不会显示任何消息。
    @Test
    public void test2() throws IOException, SAXException, ParserConfigurationException {
        boolean validate = false;
        validate = true;

        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(validate);

        XMLReader reader = null;
        SAXParser parser = spf.newSAXParser();
        reader = parser.getXMLReader();

        reader.setErrorHandler(new MyErrorHandler());
        InputSource is = new InputSource("test.xml");
        reader.parse(is);
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
            System.out.println("Line " + e.getLineNumber() + " Column " + e.getColumnNumber());
            System.out.println("System ID: " + e.getSystemId());
        }
    }
    //XML模式验证
    //我们可以在使用SAXParser解析期间打开XML模式验证。
    @Test
    public void test3() throws SAXException, ParserConfigurationException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        Schema schema = factory.newSchema(new File("yourSchema"));

        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setSchema(schema);

        SAXParser parser = spf.newSAXParser();

        // parser.parse(...);
    }
    //DefaultHandler
    //以下代码显示了当使用DefaultHandler时我们不需要实现所有的方法，我们只需要提供实现我们关心的方法。
    @Test
    public void test4() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler handler = new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName,
                                     Attributes attributes) throws SAXException {
                System.out.println(qName);
            }

            @Override
            public void characters(char ch[], int start, int length)
                    throws SAXException {
                System.out.println(new String(ch, start, length));
            }
        };
        String [] s = {};
        saxParser.parse(String.valueOf(s), handler);
    }
    //以下代码通过覆盖DefaultHandler中的错误处理程序方法来处理SAX错误
    @Test
    public void test5() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        SAXParser parser = factory.newSAXParser();
        SaxHandler handler = new SaxHandler();
        parser.parse("sample.xml", handler);
    }
    class SaxHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attrs)
                throws SAXException {
            if (qName.equals("order")) {
            }
        }
        @Override
        public void error(SAXParseException ex) throws SAXException {
            System.out.println("ERROR: [at " + ex.getLineNumber() + "] " + ex);
        }
        @Override
        public void fatalError(SAXParseException ex) throws SAXException {
            System.out.println("FATAL_ERROR: [at " + ex.getLineNumber() + "] " + ex);
        }
        @Override
        public void warning(SAXParseException ex) throws SAXException {
            System.out.println("WARNING: [at " + ex.getLineNumber() + "] " + ex);
        }
    }
    //ContentHandler
    //下面的代码选择实现 ContentHandler 接口并提供所有必要方法的实现。
    //它还实现 ErrorHandler 接口。
    @Test
    public void test6() throws ParserConfigurationException, SAXException, IOException {
        String filename = "yourXML.xml";
        // Create a new factory that will create the parser.
        SAXParserFactory spf = SAXParserFactory.newInstance();

        // Create the XMLReader to be used to parse the document.
        SAXParser parser = spf.newSAXParser();
        XMLReader reader = parser.getXMLReader();

        // Specify the error handler and the content handler.
        reader.setErrorHandler(new MyErrorHandler());
        reader.setContentHandler(new MyContentHandler());
        // Use the XMLReader to parse the entire file.
        InputSource is = new InputSource(filename);
        reader.parse(is);
    }
    class MyContentHandler implements ContentHandler {
        private Locator locator;

        /**
         * The name and of the SAX document and the current location within the
         * document.
         */
        @Override
        public void setDocumentLocator(Locator locator) {
            this.locator = locator;
            System.out.println("-" + locator.getLineNumber() + "---Document ID: "
                    + locator.getSystemId());
        }

        /** The parsing of a document has started.. */
        @Override
        public void startDocument() {
            System.out.println("-" + locator.getLineNumber()
                    + "---Document parse started");
        }
        @Override
        /** The parsing of a document has completed.. */
        public void endDocument() {
            System.out.println("-" + locator.getLineNumber()
                    + "---Document parse ended");
        }
        @Override
        /** The start of a namespace scope */
        public void startPrefixMapping(String prefix, String uri) {
            System.out.println("-" + locator.getLineNumber()
                    + "---Namespace scope begins");
            System.out.println("     " + prefix + "=\"" + uri + "\"");
        }
        @Override
        /** The end of a namespace scope */
        public void endPrefixMapping(String prefix) {
            System.out.println("-" + locator.getLineNumber()
                    + "---Namespace scope ends");
            System.out.println("     " + prefix);
        }
        @Override
        /** The opening tag of an element. */
        public void startElement(String namespaceURI, String localName, String qName,
                                 Attributes atts) {
            System.out.println("-" + locator.getLineNumber()
                    + "---Opening tag of an element");
            System.out.println("       Namespace: " + namespaceURI);
            System.out.println("      Local name: " + localName);
            System.out.println("  Qualified name: " + qName);
            for (int i = 0; i < atts.getLength(); i++) {
                System.out.println("       Attribute: " + atts.getQName(i) + "=\""
                        + atts.getValue(i) + "\"");
            }
        }
        @Override
        /** The closing tag of an element. */
        public void endElement(String namespaceURI, String localName, String qName) {
            System.out.println("-" + locator.getLineNumber()
                    + "---Closing tag of an element");
            System.out.println("       Namespace: " + namespaceURI);
            System.out.println("      Local name: " + localName);
            System.out.println("  Qualified name: " + qName);
        }

        /** Character data. */
        @Override
        public void characters(char[] ch, int start, int length) {
            System.out.println("-" + locator.getLineNumber() + "---Character data");
            showCharacters(ch, start, length);
        }
        @Override
        /** Ignorable whitespace character data. */
        public void ignorableWhitespace(char[] ch, int start, int length) {
            System.out.println("-" + locator.getLineNumber() + "---Whitespace");
            showCharacters(ch, start, length);
        }
        @Override
        /** Processing Instruction */
        public void processingInstruction(String target, String data) {
            System.out.println("-" + locator.getLineNumber()
                    + "---Processing Instruction");
            System.out.println("         Target: " + target);
            System.out.println("           Data: " + data);
        }
        @Override
        /** A skipped entity. */
        public void skippedEntity(String name) {
            System.out.println("-" + locator.getLineNumber() + "---Skipped Entity");
            System.out.println("           Name: " + name);
        }

        /**
         * Internal method to format arrays of characters so the special whitespace
         * characters will show.
         */
        public void showCharacters(char[] ch, int start, int length) {
            System.out.print("        \"");
            for (int i = start; i < start + length; i++) {
                switch (ch[i]) {
                    case '\n':
                        System.out.print("\\n");
                        break;
                    case '\r':
                        System.out.print("\\r");
                        break;
                    case '\t':
                        System.out.print("\\t");
                        break;
                    default:
                        System.out.print(ch[i]);
                        break;
                }
            }
            System.out.println("\"");
        }
    }
    //定位器
    //以下代码显示了如何从DefaultHandler访问Locator接口。
    @Test
    public void test7() throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        SAXParser parser = factory.newSAXParser();
        parser.parse("sample.xml", new SampleOfXmlLocator());
    }
    class SampleOfXmlLocator extends DefaultHandler {
        private Locator locator;

        @Override
        public void setDocumentLocator(Locator locator) {
            this.locator = locator;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attrs)
                throws SAXException {
            if (qName.equals("order")) {
                System.out.println("here process element start");
            } else {
                String location = "";
                if (locator != null) {
                    location = locator.getSystemId(); // XML-document name;
                    location += " line " + locator.getLineNumber();
                    location += ", column " + locator.getColumnNumber();
                    location += ": ";
                }
                throw new SAXException(location + "Illegal element");
            }
        }
    }

}
