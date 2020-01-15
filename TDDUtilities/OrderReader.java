package TDDUtilities;

public class OrderReader {
    private final IFileReader fileReader;

    public OrderReader(IFileReader fileReader)
    {
        this.fileReader = fileReader;
    }

    public String readFirstOrder()
    {
    	fileReader.setFileName("record.csv");
        String line = fileReader.readLine();
        fileReader.close();

        String[] details = line.split(",");
        int id = Integer.parseInt(details[0]);
        String cust = details[1];
        Double price = Double.parseDouble(details[2]);

        return String.format("%d %s £%.2f", id, cust, price);
    }
}
