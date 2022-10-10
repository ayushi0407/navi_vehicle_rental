package branch;
import java.util.HashMap;


public class Branches {
    private HashMap<Integer,Branch> branches;
    
    public Branches() {
        this.branches = new HashMap<>();
    }

    public void addBranch(Branch branch) {
        this.branches.putIfAbsent(branch.getId(), branch);
    }
    public Branch getBranch(int branchId) {
        return this.branches.get(branchId);
    }
}

