
package restaurant;

/**
 *
 * @author Shakil Ahmed
 */
public class Country {
   
    public int Id;
    public String Country_Code;
    public String Country_Name;

    public Country(int Id, String Country_Code, String Country_Name) {
        this.Id = Id;
        this.Country_Code = Country_Code;
        this.Country_Name = Country_Name;
    }

    public int getId() {
        return Id;
    }

    public String getCountry_Code() {
        return Country_Code;
    }

    public String getCountry_Name() {
        return Country_Name;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setCountry_Code(String Country_Code) {
        this.Country_Code = Country_Code;
    }

    public void setCountry_Name(String Country_Name) {
        this.Country_Name = Country_Name;
    }

    @Override
    public String toString() {
        return "Country{" + "Id=" + Id + ", Country_Code=" + Country_Code + ", Country_Name=" + Country_Name + '}';
    }
    
}
