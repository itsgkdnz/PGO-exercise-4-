import java.util.Objects;

public class Policy {
    private String policyNumber;
    private String clientName;
    private double basePremium;
    private int riskLevel;
    private double vehicleValue;
    private boolean hasAlarm;
    private boolean claimFreeClient;

    private static int createdPolicyCount = 0;
    private static final double ADMINISTRATIVE_FEE = 75.0;

    public Policy(String policyNumber, String clientName, double basePremium, int riskLevel,
                  double vehicleValue, boolean hasAlarm, boolean claimFreeClient) {
        this.policyNumber = policyNumber;
        this.clientName = clientName;
        this.basePremium = basePremium;
        this.riskLevel = riskLevel;
        this.vehicleValue = vehicleValue;
        this.hasAlarm = hasAlarm;
        this.claimFreeClient = claimFreeClient;
        createdPolicyCount++;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public double getBasePremium() {
        return basePremium;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public double getVehicleValue() {
        return vehicleValue;
    }

    public boolean isHasAlarm() {
        return hasAlarm;
    }

    public boolean isClaimFreeClient() {
        return claimFreeClient;
    }

    public double calculateFinalPremium() {
        double premium = basePremium + ADMINISTRATIVE_FEE;

        premium += riskLevel * 120;

        if (vehicleValue > 60000) {
            premium += 200;
        }

        if (hasAlarm) {
            premium -= 100;
        }

        if (claimFreeClient) {
            premium *= 0.90;
        }

        if (premium < basePremium) {
            premium = basePremium;
        }

        return Math.round(premium * 100.0) / 100.0;
    }

    public double calculateRenewalPremium() {
        double currentPremium = calculateFinalPremium();
        double renewalPremium = currentPremium;

        if (riskLevel == 4) {
            renewalPremium *= 1.10;
        } else if (riskLevel >= 5) {
            renewalPremium *= 1.20;
        }

        if (vehicleValue > 60000) {
            renewalPremium += 150;
        }

        if (claimFreeClient) {
            renewalPremium *= 0.92;
        }

        if (hasAlarm) {
            renewalPremium *= 0.95;
        }

        double minAllowed = currentPremium * 0.90;
        double maxAllowed = currentPremium * 1.25;

        if (renewalPremium < minAllowed) {
            renewalPremium = minAllowed;
        }

        if (renewalPremium > maxAllowed) {
            renewalPremium = maxAllowed;
        }

        return Math.round(renewalPremium * 100.0) / 100.0;
    }

    public String getRiskSummary() {
        if (riskLevel <= 2) {
            return policyNumber + " - Low risk";
        } else if (riskLevel == 3) {
            return policyNumber + " - Medium risk";
        } else if (riskLevel == 4) {
            return policyNumber + " - High risk";
        } else {
            return policyNumber + " - Very high risk";
        }
    }

    public static int getCreatedPolicyCount() {
        return createdPolicyCount;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "policyNumber='" + policyNumber + '\'' +
                ", clientName='" + clientName + '\'' +
                ", finalPremium=" + calculateFinalPremium() +
                ", renewalPremium=" + calculateRenewalPremium() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Policy)) return false;
        Policy policy = (Policy) o;
        return Objects.equals(policyNumber, policy.policyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber);
    }
}