public class Decorator {
    public static void main(String[] args) {
        DataSource source = new FileDataSource();
        source.writeData("ciao");
        System.out.println(source.readData());

        source = new CompressionDecorator(source);
        source.writeData("ciao123456");
        System.out.println(source.readData());

        // source->compress->encrypt->data quando scrive
        // data->decrypt->decompress->source quando legge
        source = new EncryptionDecorator(source);
        source.writeData("ciao123");
        System.out.println(source.readData());
    }
}

// le sue operazioni possono essere alterate dai decorators
interface DataSource {
    public void writeData(String data);

    public String readData();
}

class FileDataSource implements DataSource {
    private String data;

    FileDataSource() {
        this.data = "niente";
    }

    public void writeData(String data) {
        this.data = data;
    }

    public String readData() {
        return data;
    }
}

// base decorator
class DataSourceDecorator implements DataSource {
    private DataSource wrappee;

    DataSourceDecorator(DataSource source) {
        wrappee = source;
    }

    public void writeData(String data) {
        wrappee.writeData(data);
    }

    public String readData() {
        return wrappee.readData();
    }
}

class EncryptionDecorator extends DataSourceDecorator {
    EncryptionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        data = data + 'e';
        super.writeData(data);
    }

    public String readData() {
        return super.readData().substring(0, super.readData().length() - 1);
    }

}

class CompressionDecorator extends DataSourceDecorator {
    CompressionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        data = data + 'c';
        super.writeData(data);
    }

    public String readData() {
        return super.readData().substring(0, super.readData().length() - 1);
    }

}