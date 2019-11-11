package ar.edu.ub.progiii.mvc.dto;

public class BranchDTO {

    public int BranchNumber;
    public String BranchName;
    public String BranchAddress;
    public String BranchPhonenumber;

    /**
     * Constructor con parametros
     * @param branchNumber
     * @param branchName
     * @param branchAddress
     * @param branchPhonenumber
     */
    public BranchDTO(int branchNumber, String branchName, String branchAddress, String branchPhonenumber) {
        BranchNumber = branchNumber;
        BranchName = branchName;
        BranchAddress = branchAddress;
        BranchPhonenumber = branchPhonenumber;
    }

    /**
     * Constructor por defecto
     */
    public BranchDTO() {
    }

    public int getBranchNumber() {
        return BranchNumber;
    }

    public void setBranchNumber(int branchNumber) {
        BranchNumber = branchNumber;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getBranchAddress() {
        return BranchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        BranchAddress = branchAddress;
    }

    public String getBranchPhonenumber() {
        return BranchPhonenumber;
    }

    public void setBranchPhonenumber(String branchPhonenumber) {
        BranchPhonenumber = branchPhonenumber;
    }
}
