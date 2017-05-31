package tfm.marshaller;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import tfm.marshaller.data.TestFile;

public class TextFileMarshallerTest {
	
	TextFileMarshaller marshaller;

    @Test
    public void marshallTestFile() {
        String fileContent = readTestFile("testFiles/testFile.txt");
        Assert.assertTrue(fileContent != null);
        
        marshaller = new TextFileMarshaller("///", "\r\n", "H");
        
        Set<TestFile> result = (Set<TestFile>) marshaller.marshall(fileContent, TestFile.class);
        
        Assert.assertTrue(result != null);
    }

    private String readTestFile(String fileLocation) {

    	InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileLocation);
    	
    	String testFileContent = null;
    	try {
			testFileContent = IOUtils.toString(is, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return testFileContent;

    }
}
