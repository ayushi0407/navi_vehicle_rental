package branch;
import java.util.HashMap;


public class Branches {
    private HashMap<String,Branch> branches;
    
    public Branches() {
        this.branches = new HashMap<>();
    }

    public void addBranch(Branch branch) {
        this.branches.putIfAbsent(branch.getBranchName(), branch);
    }
    public Branch getBranch(String branchName) {
        return this.branches.get(branchName);
    }
}

