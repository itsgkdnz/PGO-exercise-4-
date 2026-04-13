import java.util.ArrayList;

public class InsuranceOffice {
    private String name;
    private ArrayList<Policy> policies;

    public InsuranceOffice(String name) {
        this.name = name;
        this.policies = new ArrayList<>();
    }

    public void addPolicy(Policy policy) {
        if (policy != null && !policies.contains(policy)) {
            policies.add(policy);
        }
    }

    public void printReport() {
        System.out.println("Insurance Office: " + name);
        System.out.println("----- POLICY REPORT -----");

        for (Policy policy : policies) {
            System.out.println("Policy Number: " + policy.getPolicyNumber());
            System.out.println("Client: " + policy.getClientName());
            System.out.println("Final Premium: " + policy.calculateFinalPremium());
            System.out.println("Renewal Premium: " + policy.calculateRenewalPremium());
            System.out.println("Risk: " + policy.getRiskSummary());
            System.out.println();
        }
    }

    public double calculateTotalPremium() {
        double total = 0;

        for (Policy policy : policies) {
            total += policy.calculateFinalPremium();
        }

        return Math.round(total * 100.0) / 100.0;
    }

    public double calculateTotalRenewalForecast() {
        double total = 0;

        for (Policy policy : policies) {
            total += policy.calculateRenewalPremium();
        }

        return Math.round(total * 100.0) / 100.0;
    }

    public int countHighRiskPolicies() {
        int count = 0;

        for (Policy policy : policies) {
            if (policy.getRiskLevel() >= 4) {
                count++;
            }
        }

        return count;
    }

    public Policy findByNumber(String policyNumber) {
        for (Policy policy : policies) {
            if (policy.getPolicyNumber().equals(policyNumber)) {
                return policy;
            }
        }

        return null;
    }

    public void printCheaperThan(double threshold) {
        System.out.println("Policies cheaper than " + threshold + ":");

        for (Policy policy : policies) {
            if (policy.calculateFinalPremium() < threshold) {
                System.out.println(policy);
            }
        }
    }
}