import com.opencsv.bean.CsvBindByName;

public class Zipcode {

    @CsvBindByName(column = "id")
    private String id;

    @CsvBindByName(column = "zipcode")
    private String zipcode;

    @CsvBindByName(column = "city")
    private String city;

    @CsvBindByName(column = "street")
    private String street;

    @CsvBindByName(column = "from")
    private String from;

    @CsvBindByName(column = "to")
    private String to;

    @CsvBindByName(column = "state")
    private String state;

    @CsvBindByName(column = "county")
    private String county;

    @Override
    public String toString() {
        return "CSVRecord{" +
                "id='" + id + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", state='" + state + '\'' +
                ", county='" + county + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getState() {
        return state;
    }

    public String getCounty() {
        return county;
    }
}
