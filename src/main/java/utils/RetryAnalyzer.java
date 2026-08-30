package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxTryCoynt = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxTryCoynt) {
            retryCount++;
            return true;
        }
        return false;

    }
}
