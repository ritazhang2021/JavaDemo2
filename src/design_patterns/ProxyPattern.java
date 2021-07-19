package design_patterns;

/**
 * @Author: Rita
 * @Date:5/26/2021 11:09 PM
 */
public class ProxyPattern {
    public static void main(String[] args) {
        Printer image = new ProxyPrinter("test");
        image.print();
    }
}

class ConsolePrinter implements Printer {
    private String fileName;

    public ConsolePrinter(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void print() {
        System.out.println("Displaying " + fileName);
    }
}
class ProxyPrinter implements Printer{
    private ConsolePrinter consolePrinter;
    private String fileName;

    public ProxyPrinter(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void print() {
        if(consolePrinter == null){
            consolePrinter = new ConsolePrinter(fileName);
        }
        consolePrinter.print();
    }
}

