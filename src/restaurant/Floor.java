
package restaurant;

/**
 *
 * @author Shakil Ahmed
 */
public class Floor {
    
    int TableId;
    String Status;

    public Floor() {
    }

    public Floor(int TableId, String Status) {
        this.TableId = TableId;
        this.Status = Status;
    }

    public int getTableId() {
        return TableId;
    }

    public String getStatus() {
        return Status;
    }

    public void setTableId(int TableId) {
        this.TableId = TableId;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Floor{" + "TableId=" + TableId + ", Status=" + Status + '}';
    }
    
    
    
    
}
