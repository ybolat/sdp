package blockchain;

public class Transaction {
    private String sourceDestinationName;
    private String sourceName;
    private Long sum;

    public Transaction(String sourceDestinationName, String sourceName, Long sum) {
        this.sourceDestinationName = sourceDestinationName;
        this.sourceName = sourceName;
        this.sum = sum;
    }

    public Transaction() {
    }

    public String getSourceDestinationName() {
        return sourceDestinationName;
    }

    public void setSourceDestinationName(String sourceDestinationName) {
        this.sourceDestinationName = sourceDestinationName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (sourceDestinationName != null ? !sourceDestinationName.equals(that.sourceDestinationName) : that.sourceDestinationName != null)
            return false;
        if (sourceName != null ? !sourceName.equals(that.sourceName) : that.sourceName != null) return false;
        return sum != null ? sum.equals(that.sum) : that.sum == null;
    }

    @Override
    public int hashCode() {
        int result = sourceDestinationName != null ? sourceDestinationName.hashCode() : 0;
        result = 31 * result + (sourceName != null ? sourceName.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        return result;
    }
}
