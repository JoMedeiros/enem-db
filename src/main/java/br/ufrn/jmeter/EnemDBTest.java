package br.ufrn.jmeter;

import br.ufrn.DataBase;
import br.ufrn.DataBaseLoader;
import br.ufrn.Result;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.Serializable;

public class EnemDBTest extends AbstractJavaSamplerClient implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Do any initialization required by this client. In this case,
     * initialization consists of getting the value of the AWS region
     * and credentials parameters.
     *
     * @param context
     *            the context to run with. This provides access to
     *            initialization parameters.
     */
    @Override
    public void setupTest(JavaSamplerContext context) {
        // Loading database
        long startTime = System.currentTimeMillis();
        DataBase dataBase = new DataBase();
        Thread loader = new Thread(new DataBaseLoader(dataBase));
        loader.start();
        try {
            loader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Time to load file in milliseconds: " + elapsedTime);
    }
    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        //String var1 = javaSamplerContext.getParameter("var1");
        //String var2 = javaSamplerContext.getParameter("var2");
        SampleResult result = new SampleResult();
        result.sampleStart();
        result.setSampleLabel("Test Sample");
        Result addNumbers = new Result();
        if(addNumbers.getAcertos().get("CN")[0].get() == 0) {
            result.sampleEnd();
            result.setResponseCode("200");
            result.setResponseMessage("OK");
            result.setSuccessful(true);
        } else {
            result.sampleEnd();
            result.setResponseCode("500");
            result.setResponseMessage("NOK");
            result.setSuccessful(false);
        }
        return result;
    }

    //public Arguments getDefaultParameters() {
    //    Arguments defaultParameters = new Arguments();
    //    defaultParameters.addArgument("var1","1");
    //    defaultParameters.addArgument("var2","2");
    //    return defaultParameters;
    //}
}
